package com.es.controller;

/**
 * @author fanqie
 * @ClassName IndexController
 * @date 2021/4/16 下午3:40
 **/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

}
