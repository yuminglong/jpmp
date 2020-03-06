package com.jiebao.jpmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.jiebao.jpmp.model.JpmpCommodity;

import java.util.List;

public interface JpmpCommodityService extends IService<JpmpCommodity> {


    /**
     * 根据二级id 查询三级(商品）
     *
     * @param pageNumber
     * @param pageSize
     * @param sid
     * @param tid
     * @param title
     * @return
     */
    PageInfo<JpmpCommodity> listCommodity(Integer pageNumber, Integer pageSize, Integer sid, Integer tid, String title);

    boolean deleteCommodity(Integer cid);

    void picturecid(Integer type, Integer fid);

    List<JpmpCommodity> wxlistCommodity(Integer tid);

   JpmpCommodity DetailCommodity(Integer cid);

} 