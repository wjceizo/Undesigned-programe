# Web开发技术课程实验报告

## 1 项目概述

CherishTime1:

wjceizo: 后端数据库的连接，主要负责签到与互动模块。
技术选择：python + flask + mysql/sqlite

agaogaolu:

DaSheeep:后端登录注册上传下载逻辑功能.
技术选择：java + springboot + mysql




## 2 系统功能
**功能图：**

![modules.png](https://s2.loli.net/2022/06/01/RaLwVpqIchNSoD1.png)


### 2.1 登陆模块

#### 2.1.1 用户注册

用户主要包括教师和学生，教师可以通过工号，姓名，电话号码或邮箱注册一个教师账号，学生可以通过学号，姓名，电话号码或邮箱注册一个学生账号。

#### 2.1.2 用户登陆

用户主要包括教师和学生，教师可以通过工号、密码来登陆教师账号，学生可以通过学号、密码来登陆学生账号。

### 2.2 签到模块

#### 2.2.1 签到发布

在上课时，教师可以发布签到，发布签到可以自定义时间。

#### 2.2.2 学生签到

在教师发布签到之后，学生可以按时签到，并且签到信息可以反馈到教师端。

#### 2.2.3 签到统计

在签到时间结束后，教师端可以查看学生签到情况。

### 2.3 文件共享模块

#### 2.3.1 文件上传模块

教师可以在智能课堂上传教材以及其他教学文件。

#### 2.3.2 文件下载模块

学生可以下载教师在智能课堂上传的教材以及其他教学文件。

### 2.4 作业提交模块

#### 2.4.1 作业发布模块

教师可以发布作业，发布的作业可以定义提交时间，提交方式，批阅时间。

#### 2.4.2 作业提交模块

在教师发布作业之后，学生可以提交作业，并且提交的作业可以反馈到教师端。

#### 2.4.3 作业批阅模块

在学生提交完作业之后，教师可以批阅学生的作业，并将学生的作业的得分反馈给学生。

### 2.5 师生互动模块

#### 2.5.1 课堂点名模块

在上课时，教师对所有学生提问是否听懂，学生回复，没有回答也可以记做考勤打分。

#### 2.5.2 学生反馈模块

在被点名回答问题时，学生可以回答问题并反馈到教师端。



## 3 功能图

### 3.1 登录与注册ER图



### 3.2签到与互动ER图

![签到与互动ER图.png](https://s2.loli.net/2022/06/01/2Sp8zuRxmkEcOsH.png)

