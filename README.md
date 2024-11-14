# MyProject
2024neu开源课程

## 项目简介
本项目是一个基于Web的学生信息管理系统，采用前后端分离架构，结合前端技术（HTML、CSS、JavaScript、Vue）和后端技术（Spring Boot、Spring MVC、MyBatis）来实现。系统主要功能包括用户管理、学生信息管理、班级管理和成绩管理，使用MySQL数据库进行数据存储。前端界面基于Vue和Element UI，提供用户友好的交互体验。

## 目录
- [项目简介](#项目简介)
- [功能说明](#功能说明)
- [应用技术](#应用技术)
- [安装与运行](#安装与运行)
- [项目结构](#项目结构)
- [功能展示](#功能展示)

## 功能说明
1. **用户功能**：
   - 登录、登出、注册功能，支持用户身份验证和会话管理。
   - 用户可以通过Web页面操作信息管理系统。
2. **管理员功能**：
   - 对用户、学生、班级和成绩信息进行增删改查。
   - 实现数据库中班级表与成绩表的主外键关联，并支持一对一、一对多的多表关联查询。
   - 支持根据用户ID、学号或成绩对用户、学生或成绩信息进行排序和模糊查询。
3. **学生管理功能**：
   - 允许管理员管理学生个人信息、成绩和其他数据。
   - 支持报表生成，便于信息的批量展示。
4. **班级管理功能**：
   - 班级信息的增删改查操作，支持班级与学生、教师的关联管理。

## 应用技术
1. **后端开发**：
   - 使用Spring Boot框架搭建后端服务，设计并实现RESTful API，处理前端请求并响应数据。
   - 使用MyBatis实现数据库交互和数据持久化。
2. **前端开发**：
   - 使用Vue.js和Element UI进行组件化开发，提供直观的用户界面和响应式布局。
   - 使用Ajax实现页面与服务器的异步数据交互，提升用户体验。
3. **数据库设计**：
   - 使用MySQL数据库进行数据存储，设计用户、学生、班级和成绩表，并实现主外键关联及一对多、多对多查询。

## 安装与运行

### 前提条件
确保已安装以下软件：
- [Java JDK 1.8](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.8](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/)（用于安装和运行Vue前端）
- [MySQL](https://dev.mysql.com/downloads/mysql/)（用于数据库）

### 后端运行
1. 克隆项目到本地：
   ```bash
   git clone https://github.com/your-username/student-management-system.git
   cd student-management-system
