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
import com.jiebao.jpmp.model.JpmpStair;
import com.jiebao.jpmp.service.JpmpStairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;


@Slf4j
@Service
public class JpmpStairServiceImpl extends ServiceImpl<JpmpStairMapper, JpmpStair> implements JpmpStairService {

    @Resource
    private JpmpSecondMapper jpmpSecondMapper;

    @Resource
    private JpmpCommodityMapper jpmpCommodityMapper;

    @Resource
    private JpmpPictureMapper jpmpPictureMapper;

    @Override
    public PageInfo<JpmpStair> listStair(Integer pageNumber, Integer pageSize, String sname) {
        Page page = PageHelper.startPage(pageNumber, pageSize);
        List<JpmpStair> list = baseMapper.listStair(sname);
        PageInfo info = new PageInfo<>(page.getResult());
        return info;
    }

    @Override
    public Boolean deleteStair(Integer sid) {
        //删除一级————> > 删除二级&&三级 &&图片
        try {
            List<JpmpSecond> list = jpmpSecondMapper.listSecond(null, sid);//二级
            for (JpmpSecond j : list) {
                List<JpmpCommodity> list1 = jpmpCommodityMapper.listCommodity(null, j.getTid(), null);//三级
                for (JpmpCommodity c : list1) {
                    List<JpmpPicture> list2 = jpmpPictureMapper.listPicture(2,c.getCid());
                    for (JpmpPicture p : list2) {
                        File file = new File(p.getUrl());//删除图片
                        if (file.exists()) {
                            file.delete();
                        }
                        jpmpPictureMapper.deleteById(p.getPid());//删除数据库图片数据
                    }
                    jpmpCommodityMapper.deleteById(c.getCid());//删除商品记录

                }
                jpmpSecondMapper.deleteById(j.getTid());//删除种类记录
            }
            baseMapper.deleteById(sid);//删除品牌
        } catch (Exception e) {
            return false;
        } finally {
            return true;
        }
    }

    @Override
    public List<JpmpStair> wxlistStair( ) {

        List<JpmpStair> list= baseMapper.listStair(null);
        for (JpmpStair j:list){
           List<JpmpSecond> list2= jpmpSecondMapper.listSecond(null,j.getSid());
           for(JpmpSecond s:list2){
             s.setJpmpPictures(jpmpPictureMapper.listPicture(1,s.getTid()));
           }
            j.setListSecond(list2);
        }
        return  list;
    }
}
