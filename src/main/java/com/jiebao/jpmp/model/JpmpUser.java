package com.jiebao.jpmp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("jpmp_user")
public class JpmpUser {
    @TableId
    private Integer uid;

    private String username;

    private String password;


}
