package com.jiebao.jpmp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiebao.jpmp.mapper.JpmpCommodityMapper;
import com.jiebao.jpmp.mapper.JpmpPictureMapper;
import com.jiebao.jpmp.mapper.JpmpSecondMapper;
import com.jiebao.jpmp.mapper.JpmpStairMapper;
import com.jiebao.jpmp.model.JpmpCommodity;
import com.jiebao.jpmp.model.JpmpPicture;
import com.jiebao.jpmp.model.JpmpSecond;
import com.jiebao.jpmp.service.JpmpCommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;


@Slf4j
@Service
public class JpmpCommodityServiceImpl extends ServiceImpl<JpmpCommodityMapper, JpmpCommodity> implements JpmpCommodityService {

    @Resource
    private JpmpPictureMapper jpmpPictureMapper;

    @Resource
    private JpmpStairMapper jpmpStairMapper;

    @Resource
    private JpmpSecondMapper jpmpSecondMapper;

    @Override
    public PageInfo<JpmpCommodity> listCommodity(Integer pageNumber, Integer pageSize, Integer sid, Integer tid, String title) {
        Page page = PageHelper.startPage(pageNumber, pageSize);
        List<JpmpCommodity> list = baseMapper.listCommodity(sid, tid, title);
        for (JpmpCommodity c : list) {
            JpmpSecond jpmpSecond = jpmpSecondMapper.selectById(c.getTid());
            jpmpSecond.setJpmpStair(jpmpStairMapper.selectById(c.getSid()));
            c.setJpmpSecond(jpmpSecond);
        }
        PageInfo info = new PageInfo<>(page.getResult());
        return info;

    }

    @Override
    public boolean deleteCommodity(Integer cid) {
        try {
            List<JpmpPicture> list2 = jpmpPictureMapper.listPicture(2, cid);//商品图片
            for (JpmpPicture p : list2) {
                File file = new File(p.getUrl());//删除图片
                if (file.exists()) {
                    file.delete();
                }
                jpmpPictureMapper.deleteById(p.getPid());//删除数据库图片数据
            }
            baseMapper.deleteById(cid);//删除种类记录

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }

    }

    @Override
    public void picturecid(Integer type, Integer fid) {
        List<JpmpPicture> list = jpmpPictureMapper.listPicture(0, 0);
        for (JpmpPicture p : list) {
            p.setType(type);
            p.setFid(fid);
            jpmpPictureMapper.updateById(p);
        }
    }

    @Override
    public List<JpmpCommodity> wxlistCommodity(Integer tid) {
        List<JpmpCommodity> list = baseMapper.listCommodity(null, tid, null);
        for (JpmpCommodity j : list) {
            List<JpmpPicture> list2 = jpmpPictureMapper.listPicture(2, j.getCid());
            j.setJpmpPictures(list2);
        }
        return list;
    }

    @Override
    public JpmpCommodity DetailCommodity(Integer cid) {
        JpmpCommodity jpmpCommodity = baseMapper.selectById(cid);
        jpmpCommodity.setJpmpPictures(jpmpPictureMapper.listPicture(2, cid));
        return jpmpCommodity;
    }
}
