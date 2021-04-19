package com.es.utils;

import com.es.model.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * @author fanqie
 * @ClassName HtmlParseUtil
 * @date 2021/4/16 下午4:07
 **/

public class HtmlParseUtil {
    private static final String url = "https://search.jd.com/Search?keyword=";

    public static void main(String[] args) throws IOException {
        parseHtml("java").forEach(System.out::println);
    }

    public  static ArrayList<Content> parseHtml(String keyword) throws IOException {
        //解析网页 （jsoup 返回的Document  就是浏览器Document的对象）
        Document document = Jsoup.parse(new URL(url+keyword), 30000);
        //所有在js中的可以使用的方法，这里都能使用
        Element element = document.getElementById("J_goodsList");
        //获取所有li元素标签
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodlist = new ArrayList<>();
        //获取元素中的内容
        for (Element element1 : elements) {
            Content content = new Content();
            Elements elements2 = element1.getElementsByAttributeValueStarting("class","tab-content-item");
            System.out.println(elements2.size());
            if (elements2.size()==0){
                elements2 =  element1.getElementsByClass("gl-i-wrap");
            }
            for (Element el : elements2
            ) {
                getChildElement(el,content);
                String shopname = el.getElementsByClass("p-shopnum").text();
                content.setShopName(shopname);
                System.out.println("shopname = " + shopname );
                System.out.println("\n");
            }


            goodlist.add(content);
        }
        return goodlist;
    }

    private static void getChildElement(Element el,Content content){
        //获取图片的img 标签byTag
        String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
        content.setImage(img);

        String price = el.getElementsByClass("p-price").text();
        System.out.println("price = " + price );
        content.setPrice(price);

        String name = el.getElementsByTag("em").text();
        System.out.println("name = " + name);
        content.setName(name);

        String goodType = el.getElementsByClass("goods-icons J-picon-tips J-picon-fix").text();
        System.out.println("goodType = " + goodType);
        content.setGoodType(goodType);
//                String commit = el.getElementsByClass("p-commit").text();
//                System.out.println("commit = " + commit + "\n");


        String title = el.getElementsByTag("a").eq(0).attr("title");
        System.out.println("title = " + title);
        content.setTitle(title);

        String bookdetails = el.getElementsByClass("p-bookdetails").text();
        System.out.println("bookdetails = " + bookdetails);
        content.setBookdetails(bookdetails);
    }
}
