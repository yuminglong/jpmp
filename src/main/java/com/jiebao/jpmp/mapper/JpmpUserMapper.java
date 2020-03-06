package com.jiebao.jpmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiebao.jpmp.model.JpmpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface JpmpUserMapper extends BaseMapper<JpmpUser> {

    @Select({"SELECT COUNT(*) FROM `jpmp_user` u where u.username = #{username} AND u.password = #{password}"})
    Integer login(@Param("username") String uname, @Param("password") String password);

}
