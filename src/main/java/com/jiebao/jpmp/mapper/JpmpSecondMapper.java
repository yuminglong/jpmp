package com.jiebao.jpmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiebao.jpmp.model.JpmpCommodity;
import com.jiebao.jpmp.model.JpmpSecond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JpmpSecondMapper extends BaseMapper<JpmpSecond> {
    @Select({"<script>",
            "SELECT * FROM jpmp_second s " +
                    "<where>" +
                    "<if test='tname != null'> s.tname like concat('%', #{tname}, '%') </if>" +
                    "<if test='sid != null'>and s.sid = #{sid} </if>" +
                    " </where>",
            "</script>",
    })
    List<JpmpSecond> listSecond(@Param("tname") String tname, @Param("sid") Integer sid);

}
