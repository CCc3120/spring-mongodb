package com.bingo.springmongodb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class TeacherService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public int insert(Teacher teacher) {
        mongoTemplate.insert(teacher);
        return 1;
    }
    public List<Teacher> findPage() {
        String like = "李";

        Query query = new Query();

        // 正则查询
        Pattern pattern = Pattern.compile("^.*" + like + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("fdName").regex(pattern));

        // 排序
        // query.with(Sort.by(Sort.Direction.DESC,"fdName"));
        // query.with(new Sort(Sort.Direction.DESC, "timer"));
        // 分页 起始页从0开始
        query.with(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "fdName")));

        // 返回指定的字段
        query.fields().include("_id","fdName");

        List<Teacher> teacherList = mongoTemplate.find(query, Teacher.class);
        return teacherList;
    }


}
