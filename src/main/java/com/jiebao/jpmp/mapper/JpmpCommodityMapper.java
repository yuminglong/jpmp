package com.jiebao.jpmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiebao.jpmp.model.JpmpCommodity;
import com.jiebao.jpmp.model.JpmpPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JpmpCommodityMapper extends BaseMapper<JpmpCommodity> {


    @Select({"<script>",
            "SELECT * FROM jpmp_commodity c" +
                    "<where>" +
                    "<if test='sid != null and sid !=0'> c.sid = #{sid} </if>" +
                    "<if test='tid !=null and tid != 0'> and c.tid = #{tid} </if>" +
                    "<if test='title != null'>and  c.title like concat('%', #{title}, '%') </if>" +
                    " </where>",
            "</script>",
    })
    List<JpmpCommodity> listCommodity(@Param("sid") Integer sid, @Param("tid") Integer tid, @Param("title") String title);

}
