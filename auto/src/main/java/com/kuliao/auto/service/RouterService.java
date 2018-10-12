package com.kuliao.auto.service;

import com.kuliao.auto.db.mapper.PathMapper;
import com.kuliao.auto.db.mapper.PathRoleMapper;
import com.kuliao.auto.domain.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年10月11日
 */
@Service
public class RouterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouterService.class);
    @Autowired
    private PathMapper pathMapper;
    @Autowired
    private PathRoleMapper pathRoleMapper;

    public Mono getRouter(String role) {
        //TODO 权限未加
        Map<Integer, Router> router = new ConcurrentHashMap();
        pathMapper.selectAll().forEach(r -> {
            Router item = new Router();
            BeanUtils.copyProperties(r, item);
            item.getMeta().put("title",r.getTitle());
            router.put(r.getId(), item);
        });
        return Mono.just(router.values());
    }
}
