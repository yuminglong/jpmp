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
import com.jiebao.jpmp.service.JpmpSecondService;
import com.jiebao.jpmp.service.JpmpStairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;


@Slf4j
@Service
public class JpmpSecondServiceImpl extends ServiceImpl<JpmpSecondMapper, JpmpSecond> implements JpmpSecondService {

    @Resource
    private JpmpStairMapper jpmpStairMapper;

    @Resource
    private JpmpCommodityMapper jpmpCommodityMapper;

    @Resource
    private JpmpPictureMapper jpmpPictureMapper;

    @Override
    public PageInfo<JpmpSecond> listSecond(Integer pageNumber, Integer pageSize, String tname, Integer sid) {
        Page page = PageHelper.startPage(pageNumber, pageSize);
        List<JpmpSecond> list = baseMapper.listSecond(tname, sid);
        for (JpmpSecond s : list) {
            s.setJpmpStair(jpmpStairMapper.selectById(s.getSid()));
        }
        PageInfo info = new PageInfo<>(page.getResult());
        return info;
    }

    @Override
    public boolean deleteSecond(Integer tid) {
        try {
            List<JpmpCommodity> list1 = jpmpCommodityMapper.listCommodity(null, tid, null);//三级
            for (JpmpCommodity c : list1) {
                List<JpmpPicture> list2 = jpmpPictureMapper.listPicture(2, c.getCid());//商品图片
                for (JpmpPicture p : list2) {
                    File file = new File(p.getUrl());//删除图片
                    if (file.exists()) {
                        file.delete();
                    }
                    jpmpPictureMapper.deleteById(p.getPid());//删除数据库图片数据
                }
                jpmpCommodityMapper.deleteById(c.getCid());//删除商品记录

            }
            baseMapper.deleteById(tid);//删除种类记录

        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    @Override
    public List<JpmpSecond> wxSecond(Integer tid) {
        int sid = baseMapper.selectById(tid).getSid();//品牌id
        return baseMapper.listSecond(null,sid);
    }
}
