package com.es.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * @author fanqie
 * @ClassName HtmlParseUtil
 * @date 2021/4/16 下午4:07
 **/

public class HtmlParseUtil {
    String url = "https://search.jd.com/Search";

    public void parseHtml() throws IOException {
        //解析网页 （jsoup 返回的Document  就是浏览器Document的对象）
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有在js中的可以使用的方法，这里都能使用
        Element element = document.getElementById("J_goodsList");
        //获取所有li元素
        Elements elements = element.getElementsByTag("li");
        //获取元素中的内容
        for (Element el : elements){
//            String img = el.getElementsByTag()
        }

    }
}
