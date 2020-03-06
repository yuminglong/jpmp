package com.jiebao.jpmp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.jiebao.jpmp.model.JpmpCommodity;
import com.jiebao.jpmp.model.JpmpSecond;

import java.util.List;

public interface JpmpSecondService extends IService<JpmpSecond> {
    /**
     * 根据一级id 查询二级
     * @param pageNumber
     * @param pageSize
     * @param tname
     * @param sid
     * @return
     */
    PageInfo<JpmpSecond> listSecond(Integer pageNumber, Integer pageSize, String tname, Integer sid);

    boolean deleteSecond(Integer tid);

    List<JpmpSecond> wxSecond(Integer tid);
}