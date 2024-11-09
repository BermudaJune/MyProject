package com.lanf.stu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 班级 导出类
 * @date 2023-12-02 23:27:27
 */
@Data
@ApiModel(description = "班级")
public class TbClassExportVo {
    @ApiModelProperty(value = "班级名称")
    @ExcelProperty("班级名称")
    private String name;
}
