package com.lanf.stu.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanf.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "学生信息")
@TableName("tb_student")
public class TbStudent extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;
    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private String gender;
    @TableField(exist = false)
    private String genderName;
    @ApiModelProperty(value = "班级")
    @TableField("class_id")
    private String classId;
    @TableField(exist = false)
    private String className;
}
