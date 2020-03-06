package com.jiebao.jpmp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiebao.jpmp.mapper.JpmpPictureMapper;
import com.jiebao.jpmp.model.JpmpPicture;
import com.jiebao.jpmp.service.JpmpPictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Slf4j
@Service
public class JpmpPictureServiceImpl extends ServiceImpl<JpmpPictureMapper, JpmpPicture> implements JpmpPictureService {

    @Override
    public List<JpmpPicture> listPicture(Integer type,Integer fid) {
        return baseMapper.listPicture(type,fid);
    }

    @Override
    public boolean deletePictrue(Integer pid) {
        JpmpPicture jpmpPicture = baseMapper.selectById(pid);
        File file = new File(jpmpPicture.getUrl());//删除图片
        if (file.exists()) {
            file.delete();
        }
        return baseMapper.deleteById(pid) > 0 ? true : false;
    }
}
