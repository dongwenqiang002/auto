package com.kuliao.auto.service;

import com.kuliao.auto.db.mapper.MenuMapper;
import com.kuliao.auto.db.mapper.PathMapper;
import com.kuliao.auto.db.model.Menu;
import com.kuliao.auto.domain.NavMenu;
import com.kuliao.auto.db.model.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年10月11日
 * <p>
 * 控制菜单栏的获取，由角色决定
 */
@Service
public class MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PathMapper pathMapper;

    /**
     * 获取菜单
     */
    public Mono getMenu(String role) {
        //TODO 权限未加
        Map<Integer, NavMenu> menu = new ConcurrentHashMap();
        Map<Integer, Integer> subMenuIds = new ConcurrentHashMap();
        Map<Integer, Path> paths = new ConcurrentHashMap();
        pathMapper.selectAll().forEach(p -> paths.put(p.getId(), p));

        AtomicInteger index = new AtomicInteger(1);
        menuMapper.selectAll().forEach(m -> {
            if (m.getParentId() != null) {
                subMenuIds.put(m.getId(), m.getParentId());
            }
            NavMenu item = new NavMenu();
            BeanUtils.copyProperties(m, item);
            if (m.getPathId() != null) {
                item.setIndex(paths.get(m.getPathId()).getPath());
            } else {
                item.setIndex("menu-" + index.addAndGet(1));
            }
            menu.put(m.getId(), item);
        });
        subMenuIds.forEach((id, patentId) -> menu.get(patentId).getSubs(true).add(menu.get(id)));
        subMenuIds.forEach((id, patentId) -> menu.remove(id));
        ArrayList<NavMenu> resultNavMenus = new ArrayList<>();
        resultNavMenus.addAll(menu.values());
        resultNavMenus.sort(Comparator.comparingInt(NavMenu::getSort));
        //对有子菜单的菜单项进行排序
        resultNavMenus.forEach(new Consumer<NavMenu>() {
            public void accept(NavMenu navMenu) {
                if (navMenu.getSubs() != null) {
                    navMenu.getSubs().sort(Comparator.comparingInt(NavMenu::getSort));
                    navMenu.getSubs().forEach(this);
                }
            }
        });
        return Mono.just(resultNavMenus);
    }

    /**
     * 调整菜单
     */
    @Transactional
    public Mono updateMenu(List<NavMenu> navMenus) {
        Map<String, Path> paths = new HashMap();
        //获取页面路径信息
        pathMapper.selectAll().forEach(path -> paths.put(path.getPath(), path));
        //由前端菜单数据结构根据路径信息转换为后端结构并保存
        this.trnMenu(navMenus, paths).forEach(v -> menuMapper.updateByPrimaryKey(v));
        return Mono.just("菜单保存OK");
    }

    //将前端的菜单格式转换成后端存储的菜单
    private List<Menu> trnMenu(List<NavMenu> navMenus, Map<String, Path> paths) {
        AtomicInteger sort = new AtomicInteger(1);
        List resultMenu = new Vector<Menu>();
        navMenus.forEach(navMenu -> {
            Menu tempNavMenu = new Menu();
            BeanUtils.copyProperties(navMenu, tempNavMenu);
            tempNavMenu.setPathId(paths.getOrDefault(navMenu.getIndex(), new Path()).getId());
            tempNavMenu.setSort(sort.getAndIncrement());
            resultMenu.add(tempNavMenu);
            if (navMenu.getSubs() != null) {
                List<Menu> subNavMenus = this.trnMenu(navMenu.getSubs(), paths);
                subNavMenus.forEach(v -> {
                    if (v.getParentId() == null) v.setParentId(navMenu.getId());
                });
                resultMenu.addAll(subNavMenus);
            }
        });
        return resultMenu;
    }

    public Mono<Menu> getMenuInfo(Integer id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        return Mono.just(menu);
    }

    public Mono updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKey(menu);
        return Mono.just("OK");
    }
}
