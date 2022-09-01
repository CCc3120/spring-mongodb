package com.bingo.springmongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document("student")
public class Student {

    @MongoId
    private String fdId;

    private String fdName;

    private String fdClass;

    private Integer fdAge;

    private Date fdBirthday;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId;
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    public String getFdClass() {
        return fdClass;
    }

    public void setFdClass(String fdClass) {
        this.fdClass = fdClass;
    }

    public Integer getFdAge() {
        return fdAge;
    }

    public void setFdAge(Integer fdAge) {
        this.fdAge = fdAge;
    }

    public Date getFdBirthday() {
        return fdBirthday;
    }

    public void setFdBirthday(Date fdBirthday) {
        this.fdBirthday = fdBirthday;
    }
}
