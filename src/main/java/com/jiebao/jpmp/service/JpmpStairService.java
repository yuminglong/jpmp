package com.jiebao.jpmp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.jiebao.jpmp.model.JpmpSecond;
import com.jiebao.jpmp.model.JpmpStair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JpmpStairService extends IService<JpmpStair> {



    /**
     * 查询一级列表
     * @param pageNumber
     * @param pageSize
     * @param sname 品牌名字
     * @return
     */
    PageInfo<JpmpStair> listStair(Integer pageNumber, Integer pageSize, String sname);

    /**
     * 删除一级 品牌
     * @param sid
     * @return
     */
   Boolean deleteStair(Integer sid);


   List<JpmpStair> wxlistStair( );

}