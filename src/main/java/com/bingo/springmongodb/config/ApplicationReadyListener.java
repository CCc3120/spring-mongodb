package com.bingo.springmongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

/**
 * 此类若不加，那么插入的一行会默认添加一个_class字段来存储实体类类型 如（com.bingo.springmongodb.model.Student）
 * <p>
 * { "_id" : "fd180ba65f034ed58770c5311f74c72d", "fdName" : "张三", "fdClass" : "幼稚园大班", "fdAge" : 18, "fdBirthday" : ISODate("2022-07-25T08:57:57.020Z"),
 * "_class" : "com.bingo.springmongodb.model.Student" }
 */
@Configuration
public class ApplicationReadyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MongoTemplate oneMongoTemplate;

    private static final String TYPEKEY = "_class";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MongoConverter converter = oneMongoTemplate.getConverter();
        if (converter.getTypeMapper().isTypeKey(TYPEKEY)) {
            ((MappingMongoConverter) converter).setTypeMapper(new DefaultMongoTypeMapper(null));
        }
    }
}
