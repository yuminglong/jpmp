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
@TableName("jpmp_commodity")
public class JpmpCommodity {//商品
    @TableId
    private Integer cid;

    private String title;

    private String intro;

    private Integer sid;

    private Integer tid;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private JpmpSecond jpmpSecond;

    @TableField(exist = false)
    private List<JpmpPicture> jpmpPictures;
}
