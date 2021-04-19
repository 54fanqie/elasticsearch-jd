package com.es.service;

import com.alibaba.fastjson.JSON;
import com.es.model.Content;
import com.es.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fanqie
 * @ClassName ContentService
 * @date 2021/4/19 下午3:51
 **/
@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public boolean pareseContent(String keyword) throws IOException {
        ArrayList<Content> arrayList = HtmlParseUtil.parseHtml(keyword);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i = 0; i < arrayList.size() ; i++) {
            Content content = arrayList.get(i);
            System.out.println(JSON.toJSONString(content));
            bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(content),
                    XContentType.JSON)
            );
        }
        BulkResponse bulkItemResponses =  restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkItemResponses.hasFailures();
    }

    public List searchPage(String keyword,int page,int pageSize){

        try {
            SearchRequest searchResult = new SearchRequest("jd_goods");
            SearchSourceBuilder searchSourceBuilder  = new SearchSourceBuilder();
            searchSourceBuilder.from(page);
            searchSourceBuilder.size(pageSize);

            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword);
            searchSourceBuilder.query(termQueryBuilder);
            searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

            searchResult.source(searchSourceBuilder);

            SearchResponse response = restHighLevelClient.search(searchResult,RequestOptions.DEFAULT);

            List list = new ArrayList();
            for (SearchHit documentFiled:response.getHits().getHits()
                 ) {
                list.add(documentFiled.getSourceAsMap());
            }
            return list;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }


    }
}
