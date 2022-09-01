package com.bingo.springmongodb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/tea/mongo")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        Teacher teacher = new Teacher();
        teacher.setFdId(getID());
        teacher.setFdName("李老师");
        teacher.setFdAge(18);
        teacher.setFdClass("幼稚园大班");
        teacher.setFdBirthday(new Date());
        teacherService.insert(teacher);
        return "ok";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find() {
//        List<Student> students = studentService.find();
        List<Teacher> teachers = teacherService.findPage();
        teachers.forEach(System.out::println);
        return "ok";
    }

    public String getID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
