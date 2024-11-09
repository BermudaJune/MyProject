package com.lanf.stu.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanf.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "班级")
@TableName("tb_class")
public class TbClass extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "班级名称")
    @TableField("name")
    private String name;
}
