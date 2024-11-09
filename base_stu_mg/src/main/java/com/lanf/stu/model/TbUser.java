package com.lanf.stu.model;

import com.lanf.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel(description = "用户表")
@TableName("tb_user")
public class TbUser extends BaseEntity {
        private static final long serialVersionUID = 1L;
        @ApiModelProperty(value = "用户名")
        @TableField("user_name")
        private String userName;
        @ApiModelProperty(value = "密码")
        @TableField("password")
        private String password;
        @ApiModelProperty(value = "昵称")
        @TableField("nickname")
        private String nickname;
}
