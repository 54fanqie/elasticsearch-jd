package com.es.controller;

import com.es.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fanqie
 * @ClassName ContentController
 * @date 2021/4/19 下午4:03
 **/
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/parse/{keyword}")
    public boolean parse(@PathVariable("keyword") String keyword){
        try {
            return contentService.pareseContent(keyword);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }


    @RequestMapping(value = "/search/{keyword}/{page}/{pageSize}")
    public List search(@PathVariable(value = "page",required = false) int page,
                       @PathVariable(value = "pageSize",required = false) int pageSize,
                       @PathVariable("keyword") String keyword){
        try {
            return contentService.searchPageHighLigth(keyword,page,pageSize);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
