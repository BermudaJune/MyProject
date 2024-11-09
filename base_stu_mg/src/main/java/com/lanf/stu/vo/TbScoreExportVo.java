package com.lanf.stu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 成绩 导出类
 * @date 2023-12-03 00:34:44
 */
@Data
@ApiModel(description = "成绩")
public class TbScoreExportVo {
    @ApiModelProperty(value = "学生名称")
    @ExcelProperty("学生名称")
    private String studentName;
    @ApiModelProperty(value = "课程名称")
    @ExcelProperty("课程名称")
    private String courseName;
    @ApiModelProperty(value = "成绩")
    @ExcelProperty("成绩")
    private Integer grade;
}
