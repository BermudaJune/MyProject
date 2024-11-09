package com.lanf.stu.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanf.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "成绩")
@TableName("tb_score")
public class TbScore extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "学生")
    @TableField("student_id")
    private String studentId;
    @TableField(exist = false)
    private String studentName;
    @ApiModelProperty(value = "课程名称")
    @TableField("course_name")
    private String courseName;
    @ApiModelProperty(value = "成绩")
    @TableField("grade")
    private Integer grade;
}
