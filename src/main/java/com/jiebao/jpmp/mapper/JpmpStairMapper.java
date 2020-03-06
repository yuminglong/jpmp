package com.jiebao.jpmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiebao.jpmp.model.JpmpStair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JpmpStairMapper extends BaseMapper<JpmpStair> {

    @Select({"<script>",
            "SELECT * FROM jpmp_stair s  " +
            "<if test='sname != null'> where s.sname like concat('%', #{sname}, '%') </if>" +
            "ORDER BY s.create_time desc",
            "</script>",
    })
    List<JpmpStair>listStair(@Param("sname")String sname);

}
