# Web开发技术课程实验报告

## 1 项目概述

### 1.1 项目背景

随着国家综合国力的提升，教育领域投入越来越大，每年进入高校学习的人数都在增加，依靠传统的教学安排工作方式很容易出现问题，在这种情况下，想要结合当下流行的互联网开发技术，开发一套功能完善的教务管理系统，针对校园课程工作进行管理。

### 1.2 系统描述

在本套智能课堂系统中，需要我们满足对于校园基础信息以及日常工作两大方面的管理需要，其中基础信息管理包括了校园的教师、教室、学生、课程等，需要在系统中对这些数据实现管理功能，另外针对日常教学任务的安排也需要提供合理的管理功能，比如班级授课工作的安排、学生上课的安排等等，学生或者教师可以在登陆系统之后清楚的看到自己需要完成或者一段时间内容的工作和课程安排，提升工作效率，满足日常办公需要。

### 1.3 团队分工

CherishTime1:文档撰写，类图，系统框架图，时序图等绘制。

技术选择：文档撰写：markdown  绘图：Enterprise Architect + mermaid

wjceizo: 后端数据库的连接，主要负责签到与互动模块，教师上传图片识别到班级学生模块。
技术选择：后端：Java+ SpringBoot+ MyBatisPlus + Mysql 识别模块：Python + Flask + Openvino

agaogaolu:负责前端页面的制作

技术选择：Vue + ElementUI

DaSheeep:后端登录注册上传下载逻辑功能.
技术选择：java + springboot + mysql


## 2 系统功能
**功能图：**

![QQ图片20220609233703](https://user-images.githubusercontent.com/53281427/172990283-002493b7-3bd2-4814-8b82-8f8e2d13a86c.png)



**系统架构图：**

![1](https://user-images.githubusercontent.com/53281427/172431586-324332bb-0926-44e0-8889-c22a5ef0c0ff.png)


### 2.1 登陆模块

#### 2.1.1 用户注册

用户主要包括教师和学生，教师可以通过工号，姓名，电话号码或邮箱注册一个教师账号，学生可以通过学号，姓名，电话号码或邮箱注册一个学生账号。

#### 2.1.2 用户登陆

用户主要包括教师和学生，教师可以通过工号、密码来登陆教师账号，学生可以通过学号、密码来登陆学生账号。

![login](https://user-images.githubusercontent.com/37702975/172293308-02560905-ce03-4efa-86e4-362128f82159.png)

### 2.2 签到模块

#### 2.2.1 签到发布

在上课时，教师可以发布签到，发布签到可以自定义时间。
![image](https://user-images.githubusercontent.com/37702975/172980091-b1093810-149e-4ff3-949b-a42cca96633b.png)

#### 2.2.2 学生签到

在教师发布签到之后，学生可以按时签到，并且签到信息可以反馈到教师端。

##### 签到类图

```mermaid
classDiagram
学生 -- 已签到信息 : n-n
学生 -- 签到信息 : n-1
管理员 -- 签到信息 : 1-n
管理员 -- 添加学生信息 : n-n
老师 -- 签到信息 : n-1
老师 -- 签到信息 : n-1
老师 -- 添加学生信息 : n-n
学生 : 签签到()
学生 : 学号
学生 : 班级
学生 : 姓名
已签到信息 : 学号
已签到信息 : 班级
已签到信息 : 查查看()
已签到信息 : 删删除()
签到信息 : 学号
签到信息 : 班级
签到信息 : 姓名
签到信息 : 签到时间
签到信息 : 签签到()
管理员 : 编号
管理员 : 姓名
管理员 : 查查询()
管理员 : 删删除()
管理员 : 保保存()
添加学生信息 : 学号
添加学生信息 : 姓名
添加学生信息 : 班级
添加学生信息 : 添添加()
老师 : 工号
老师 : 姓名
老师 : 班级
老师 : 查查询()
老师 : 签签到()
老师 : 删删除()
老师 : 保保存()
`````

##### 签到时序图

```mermaid
	    sequenceDiagram
	    participant 学生 
	    participant 签到界面 
	    participant 提示画面
	    participant 已签到人员界面
	    participant 数据库
	    学生 ->> 签到界面: 输入学号
	    签到界面 ->> 提示画面: 验证通过吗？
	    提示画面 ->> 学生:提示是否签到
	    学生 ->> 已签到人员界面:确认签到
	    已签到人员界面 ->> 数据库:返回数据
	    数据库 ->> 数据库:添加签到数据
	    已签到人员界面 ->> 签到界面:显示已经签到人员界面
```

#### 2.2.3 签到统计

在签到时间结束后，教师端可以查看学生签到情况。

### 2.3 基础信息管理模块
基础信息管理，这是为了保证系统运行提供的管理功能，包括班级信息、用户信息、教室信息、课程信息等，管理员登陆之后可以在这里编辑这些信息，实现对校园基础设施信息的管理工作。

### 2.4 日常工作处理模块

日常办公管理，在这里管理员可以根据校园的实际情况发布一些通知或者是活动信息，教师和学生登陆到系统中之后可以看到这些信息，另外就是针对校园各个班级课程安排、上课时间等也可以在这里进行管理，学生登陆之后可以了解自己的课程安排情况，教师可以了解自己的工作安排。
班级管理
![image](https://user-images.githubusercontent.com/37702975/172979881-13d5f9e3-0337-48fb-ab09-6d7b806c9d85.png)
用户管理
![image](https://user-images.githubusercontent.com/37702975/172979929-2672a3b0-4e67-4533-8300-11511360cd5f.png)
活动管理
![image](https://user-images.githubusercontent.com/37702975/172979839-e2cb6665-1718-4f2c-bbd9-d732c70cc9b4.png)

### 2.5老师智能考勤模块

老师端可以上课时拍一张全体学生的照片，通过人工智能对象检测算法，检测出有多少学生来教师上课，同时统计学生缺勤与是否签到造假的情况。
数据集：http://vis-www.cs.umass.edu/fddb/



## 3 功能图

### 3.1 登录与注册ER图

![在线制图- (1)](https://user-images.githubusercontent.com/53281427/172417526-218aa171-7c46-429d-8ddc-aaeabe6646c8.jpg)


### 3.2签到与互动ER图

![签到1](https://user-images.githubusercontent.com/53281427/172158685-e24891c4-7388-4f4a-aad7-f7730cb5a11e.jpg)

### 3.3学生年级老师ER图
![sql_img.jpg](https://s2.loli.net/2022/06/06/f8mCsnVi1t5JIkM.jpg)

### 3.4智能考勤预期结果

![dec.jpg](https://s2.loli.net/2022/06/10/MnSpd9VZmzQEfcH.jpg)



