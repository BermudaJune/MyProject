package com.lanf.stu.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 班级 vo类
 * @date 2023-12-02 23:27:27
 */
@Data
public class TbClassQueryVo {
    private String name;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
}

