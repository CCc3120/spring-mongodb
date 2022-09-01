package com.bingo.springmongodb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/stu/mongo")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        Student student = new Student();
        student.setFdId(getID());
        student.setFdName("张三");
        student.setFdAge(18);
        student.setFdClass("幼稚园大班");
        student.setFdBirthday(new Date());
        studentService.insert(student);
        return "ok";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find() {
//        List<Student> students = studentService.find();
        List<Student> students = studentService.findPage();
        students.forEach(System.out::println);
        return "ok";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update() {
        Student student = new Student();
        student.setFdId("3f892988e8ec4ed0b682d6b05c25c15b");
        student.setFdName("张三2222");
        student.setFdAge(30);
        student.setFdClass("幼稚园大班2222");
        student.setFdBirthday(new Date());
        studentService.update(student);
        return "ok";
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String del() {
        Student student = new Student();
        student.setFdId("94e0fbd4039f4461860a7108e67a5645");
        studentService.del(student);
        return "ok";
    }

    public String getID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
