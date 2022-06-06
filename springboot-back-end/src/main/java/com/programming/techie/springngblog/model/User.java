package com.programming.techie.springngblog.model;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stid;
    @Column
    private String stuId;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Column
    private String stuName;
    @Column
    private String stuPassword;
    @Column
    private String stuClass;

    public Long getId() {
        return stid;
    }

    public void setId(Long id) {
        this.stid = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuID) {
        this.stuId = stuID;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
}
