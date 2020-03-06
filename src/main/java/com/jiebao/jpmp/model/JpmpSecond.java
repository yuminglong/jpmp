package com.jiebao.jpmp.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@TableName("jpmp_second")
public class JpmpSecond {//二级分类
    @TableId
    private Integer tid;

    private String tname;

    private Integer sid;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private List<JpmpPicture> jpmpPictures;

    @TableField(exist = false)
    private List<JpmpCommodity> jpmpCommoditiy;

    @TableField(exist = false)
    private JpmpStair jpmpStair;

}
