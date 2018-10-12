package com.kuliao.auto.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年09月27日
 */
@RestController
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/index")
    public Flux<String> index() {

        return Flux.interval(Duration.ofMillis(50)).map(l -> l+"");
    }
}
