package com.lanf.stu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 学生信息 导出类
 * @date 2023-12-02 23:59:17
 */
@Data
@ApiModel(description = "学生信息")
public class TbStudentExportVo {
    @ApiModelProperty(value = "姓名")
    @ExcelProperty("姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    @ExcelProperty("年龄")
    private Integer age;
    @ApiModelProperty(value = "性别")
    @ExcelProperty("性别")
    private String genderName;
    @ApiModelProperty(value = "班级")
    @ExcelProperty("班级")
    private String className;
}
