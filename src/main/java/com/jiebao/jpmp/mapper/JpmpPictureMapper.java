package com.jiebao.jpmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiebao.jpmp.model.JpmpPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JpmpPictureMapper extends BaseMapper<JpmpPicture> {
    @Select("SELECT * FROM jpmp_picture p\n" +
            "WHERE p.fid = #{fid} and p.type = #{type} ORDER BY p.create_time desc")
    List<JpmpPicture> listPicture(@Param("type") Integer type, @Param("fid") Integer fid);

}
