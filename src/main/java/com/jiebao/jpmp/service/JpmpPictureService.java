package com.jiebao.jpmp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jiebao.jpmp.model.JpmpPicture;

import java.util.List;

public interface JpmpPictureService extends IService<JpmpPicture> {

    /**
     * 根据商品id 查询商品图片
     *
     * @param
     * @return
     */
    List<JpmpPicture> listPicture(Integer type, Integer fid);

    boolean deletePictrue(Integer pid);

}