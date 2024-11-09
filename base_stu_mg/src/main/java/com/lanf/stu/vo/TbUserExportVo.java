package com.lanf.stu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 用户表 导出类
* @date 2023-12-18 21:28:38
*/
@Data
@ApiModel(description = "用户表")
public class TbUserExportVo  {
      @ApiModelProperty(value = "用户名")
      @ExcelProperty("用户名")
      private String userName;
      @ApiModelProperty(value = "密码")
      @ExcelProperty("密码")
      private String password;
      @ApiModelProperty(value = "昵称")
      @ExcelProperty("昵称")
      private String nickname;
   }
