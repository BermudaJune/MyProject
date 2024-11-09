package com.lanf.stu.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
* @author tanlingfei
* @version 1.0
* @description 成绩 vo类
* @date 2023-12-03 00:34:44
*/
@Data
public class TbScoreQueryVo {
       private String studentId;
       private String studentName;
       private String courseName;
       private Integer grade;
       private Date createTimeBegin;
       private Date createTimeEnd;
       private Date updateTimeBegin;
       private Date updateTimeEnd;
}

