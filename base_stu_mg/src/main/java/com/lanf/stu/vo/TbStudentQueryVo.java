package com.lanf.stu.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 学生信息 vo类
 * @date 2023-12-02 23:59:17
 */
@Data
public class TbStudentQueryVo {
    private String name;
    private Integer age;
    private String gender;
    private String genderName;
    private String classId;
    private String className;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
}

