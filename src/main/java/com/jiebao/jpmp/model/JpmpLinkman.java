package com.jiebao.jpmp.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("jpmp_linkman")
public class JpmpLinkman {
    @TableId
    private Integer lid;

    private String linkman;

    private String phone;

    private String address;


}
