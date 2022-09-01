package com.bingo.springmongodb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StudentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public int insert(Student student) {
        mongoTemplate.insert(student);
        return 1;
    }

    public int update(Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(student.getFdId()));
        Update update = new Update();
        update.set("fdAge", student.getFdAge());
        update.set("fdClass", student.getFdClass());
        update.set("fdName", student.getFdName());
        update.set("fdBirthday", student.getFdBirthday());
        mongoTemplate.updateFirst(query, update, Student.class);

        // 修改 新增
        mongoTemplate.save(student);
        return 1;
    }

    public int del(Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(student.getFdId()));
        mongoTemplate.remove(query, Student.class);
        return 1;
    }

    public List<Student> find() {
        return mongoTemplate.findAll(Student.class);
    }

    public List<Student> findPage() {
        String like = "张三";

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

        List<Student> studentList = mongoTemplate.find(query, Student.class);
        return studentList;
    }
}
