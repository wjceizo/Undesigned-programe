package com.programming.techie.springngblog.dto;

public class RegisterRequest {
    private String stuId;
    private String stuPassword;
    private String stuClass;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    private String stuName;


    public String getStuId() {
        return stuId;
    }

    public void setUsername(String username) {
        this.stuId = username;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setEmail(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setPassword(String password) {
        this.stuPassword = password;
    }
}
