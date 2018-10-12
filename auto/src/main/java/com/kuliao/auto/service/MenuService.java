package com.kuliao.auto.service;

import com.kuliao.auto.db.mapper.MenuMapper;
import com.kuliao.auto.db.mapper.MenuRoleMapper;
import com.kuliao.auto.db.mapper.PathMapper;
import com.kuliao.auto.domain.Menu;
import com.kuliao.auto.db.model.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年10月11日
 */
@Service
public class MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Autowired
    private PathMapper pathMapper;

    /**
     * 获取菜单
     */
    public Mono getMenu(String role) {
        //TODO 权限未加
        Map<Integer, Menu> menu = new ConcurrentHashMap();
        Map<Integer,Integer> subMenuIds = new ConcurrentHashMap();
        Map<Integer, Path> paths = new ConcurrentHashMap();
        pathMapper.selectAll().forEach(p -> paths.put(p.getId(), p));

        AtomicInteger index = new AtomicInteger(1);
        menuMapper.selectAll().forEach(m -> {
            if (m.getParentId() != null) {
                subMenuIds.put(m.getId(),m.getParentId());
            }
            Menu item = new Menu();
            item.setIcon(m.getIcon());
            item.setTitle(m.getTitle());
            if (m.getPathId() != null) {
                item.setIndex(paths.get(m.getPathId()).getPath());
            }else{
                item.setIndex("menu-"+index.addAndGet(1));
            }
            item.setSort(m.getSort());
            menu.put(m.getId(),item);
        });
        subMenuIds.forEach((id,patentId) -> menu.get(patentId).getSubs(true).add(menu.get(id)));
        subMenuIds.forEach((id,patentId) -> menu.remove(id));
        ArrayList<Menu> resultMenu = new ArrayList<>();
        resultMenu.addAll(menu.values());
        resultMenu.sort(Comparator.comparingInt(Menu::getSort));
        return Mono.just(resultMenu);
    }
}
