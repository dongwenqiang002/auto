package com.kuliao.auto.controller;

import com.kuliao.auto.db.model.Menu;
import com.kuliao.auto.domain.NavMenu;
import com.kuliao.auto.domain.Objs;
import com.kuliao.auto.service.MenuService;
import com.kuliao.auto.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年10月10日
 */
@RestController
@RequestMapping("/web")
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    @Autowired
    private MenuService menuService;
    @Autowired
    private RouterService routerService;


    @GetMapping("/menu")
    public Mono menu(String role) {
        if (role == null || role.isEmpty()) return Mono.error(new Exception("角色无"));
        return menuService.getMenu(role);
    }

    /**
     * 改变菜单顺序和结构，菜单数据存储在menu的subs中
     */
    @PostMapping("/menu")
    public Mono updateMenus(@RequestBody Objs<NavMenu> menu) {
        LOGGER.info(Arrays.toString(menu.getData().toArray()));
        return  menuService.updateMenu(menu.getData());
    }

    @PostMapping
    public Mono updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }
    @GetMapping()
    public Mono getMenu(@RequestParam("id") Integer id){
        return menuService.getMenuInfo(id);
    }

    @GetMapping("/router")
    public Mono router(String role) {
        if (role == null || role.isEmpty()) return Mono.error(new Exception("角色无"));
        return routerService.getRouter(role).delayElement(Duration.ofSeconds(2));
    }


    @ExceptionHandler(Exception.class)
    public Mono handle(Exception ex, ServerHttpResponse response) {
        ex.printStackTrace();
        LOGGER.info("异常拦截过滤器");
        LOGGER.error(ex.toString());
        response.setStatusCode(HttpStatus.valueOf(400));
        return Mono.just(ex.getMessage());
    }
}
