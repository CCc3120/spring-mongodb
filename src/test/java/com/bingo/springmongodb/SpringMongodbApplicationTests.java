package com.bingo.springmongodb;

import com.bingo.springmongodb.model.Student;
import com.bingo.springmongodb.model.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class SpringMongodbApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        Student student = new Student();
        student.setFdId(getID());
        student.setFdName("张三");
        student.setFdAge(18);
        student.setFdClass("幼稚园大班");
        student.setFdBirthday(new Date());
        studentService.insert(student);
    }


    public String getID(){
       return UUID.randomUUID().toString().replaceAll("-","");
    }
}
