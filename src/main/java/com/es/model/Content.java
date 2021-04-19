package com.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fanqie
 * @ClassName Content
 * @date 2021/4/19 上午11:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String image;
    private String name;
    private String price;
    private String shopName;
//    private String commitNum;
    private String title;
    //自营  非自营
    private String goodType;
    private String bookdetails;
}
