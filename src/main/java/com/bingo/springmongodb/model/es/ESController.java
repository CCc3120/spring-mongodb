package com.bingo.springmongodb.model.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @RequestMapping("/test1")
    public String test1() {
        // StudentForEs student = new StudentForEs();
        // student.setFdId(getID());
        // student.setFdName("张三");
        // student.setFdAge(18);
        // student.setFdClass("幼稚园大班");
        // student.setFdBirthday(new Date());
        // studentForEsRepository.save(student);
        String id = UUID.randomUUID().toString().replaceAll("-", "");

        Product product = new Product(id, "小米手机", "手机", "小米", 2899.00, "http://www.baidu.com");

        String jsonString = JSON.toJSONString(product);

        IndexRequest indexRequest = new IndexRequest("bntang", "product");

        indexRequest.source(jsonString, XContentType.JSON);

        try {
            // 5.调用方法进行数据通信
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            // 6.解析输出结果
            System.out.println("结果：" + JSON.toJSONString(indexResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @RequestMapping("/test2")
    public String test2() {

        return "ok";
    }

    @RequestMapping("/test3")
    public String test3() {

        return "ok";
    }

    @RequestMapping("/test4")
    public String test4() {

        return "ok";
    }

    public String getID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
