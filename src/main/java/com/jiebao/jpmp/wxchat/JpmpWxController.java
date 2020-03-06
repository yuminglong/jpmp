package com.jiebao.jpmp.wxchat;

import com.jiebao.jpmp.model.JpmpCommodity;
import com.jiebao.jpmp.model.JpmpPicture;
import com.jiebao.jpmp.model.JpmpSecond;
import com.jiebao.jpmp.model.JpmpStair;
import com.jiebao.jpmp.service.JpmpCommodityService;
import com.jiebao.jpmp.service.JpmpPictureService;
import com.jiebao.jpmp.service.JpmpSecondService;
import com.jiebao.jpmp.service.JpmpStairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class JpmpWxController {
    @Autowired
    private JpmpStairService jpmpStairService;

    @Autowired
    private JpmpSecondService jpmpSecondService;

    @Autowired
    private JpmpPictureService jpmpPictureService;

    @Autowired
    private JpmpCommodityService jpmpCommodityService;


    @GetMapping("/WxStair")
    public List<JpmpStair> Stair() {
        return jpmpStairService.wxlistStair();
    }

    @GetMapping("/wxStairComd")
    public List<JpmpSecond> wxSecond(Integer tid) {
        //根据二级tid 查询一级sid--->根据sid 查询与二级tid 同级
        return jpmpSecondService.wxSecond(tid);
    }

    @GetMapping("/wxlistCommodity")
    public List<JpmpCommodity> wxlistCommodity(Integer tid) {
        return jpmpCommodityService.wxlistCommodity(tid);
    }

    @GetMapping("/DetailCommodity")
    public JpmpCommodity DetailCommodity(Integer cid) {
        return jpmpCommodityService.DetailCommodity(cid);
    }

    @GetMapping("/WxPicture")
    public List<JpmpPicture> WxPicture() {
        return jpmpPictureService.listPicture(3,0);
    }


}
