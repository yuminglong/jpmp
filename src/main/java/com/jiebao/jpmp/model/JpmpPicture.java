package com.jiebao.jpmp.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("jpmp_picture")
public class JpmpPicture {//一级分类
    @TableId
    private Integer pid;

    private String url;

    private String pname;

    private Integer type;

    private Integer fid;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private  JpmpCommodity jpmpCommodity;

}
