package com.lanf.stu.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
* @author tanlingfei
* @version 1.0
* @description 用户表 vo类
* @date 2023-12-18 21:28:38
*/
@Data
public class TbUserQueryVo {
       private String userName;
       private String password;
       private String nickname;
       private Date createTimeBegin;
       private Date createTimeEnd;
       private Date updateTimeBegin;
       private Date updateTimeEnd;
}

