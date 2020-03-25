package com.jiebao.jpmp.controller;

import com.github.pagehelper.PageInfo;
import com.jiebao.jpmp.model.*;
import com.jiebao.jpmp.service.*;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class JpmpParentController {
    @Autowired
    private JpmpStairService jpmpStairService;

    @Autowired
    private JpmpSecondService jpmpSecondService;

    @Autowired
    private JpmpPictureService jpmpPictureService;

    @Autowired
    private JpmpCommodityService jpmpCommodityService;

    @Autowired
    private JpmpUserService jpmpUserService;


    @PostMapping("/login")
    public boolean login(JpmpUser user) {
        return jpmpUserService.login(user);
    }

    @GetMapping("/listStair")
    public PageInfo<JpmpStair> listStair(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(required = false) String sname) {
        return jpmpStairService.listStair(pageNumber, pageSize, sname);
    }

    @GetMapping("/Stair")
    public List<JpmpStair> Stair() {
        return jpmpStairService.list();
    }


    @PostMapping("/saveStair")
    public String saveStair(JpmpStair jpmpStair) {
        return jpmpStairService.saveOrUpdate(jpmpStair) ? "操作成功" : "异常";
    }

    @GetMapping("/deleteStair")
    public String deleteStair(Integer sid) {
        //删除一级————> > 删除二级&&三级 &&图片
        return jpmpStairService.deleteStair(sid) ? "删除成功" : "删除异常";
    }

    @GetMapping("/listSecond")
    public PageInfo<JpmpSecond> listSecond(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(required = false) String tname, @RequestParam(required = false) Integer sid) {
        return jpmpSecondService.listSecond(pageNumber, pageSize, tname, sid);
    }

    @GetMapping("/deleteSecond")
    public String deleteSecond(Integer tid) {
        // 删除二级--->>三级 &&图片
        return jpmpSecondService.deleteSecond(tid) ? "删除成功" : "删除异常";
    }


    @PostMapping("/saveSencond")
    public Integer saveSencond(JpmpSecond jpmpSecond) {
        return jpmpSecondService.saveOrUpdate(jpmpSecond) ? jpmpSecond.getTid() : 0;
    }

    @GetMapping("/listCommodity")
    public PageInfo<JpmpCommodity> listCommodity(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(required = false) Integer sid, @RequestParam(required = false) Integer tid, @RequestParam(required = false) String title) {
        return jpmpCommodityService.listCommodity(pageNumber, pageSize, sid, tid, title);
    }

    @GetMapping("/deleteCommodity")
    public String deleteCommodity(Integer cid) {
        return jpmpCommodityService.deleteCommodity(cid) ? "删除成功" : "删除异常";
    }

    @PostMapping("/saveCommodity")
    public Integer saveCommodity(JpmpCommodity jpmpCommodity) {
        return jpmpCommodityService.saveOrUpdate(jpmpCommodity) ? jpmpCommodity.getCid() :0;
    }

    @GetMapping("listPicture")
    public List<JpmpPicture> listPicture(Integer type, Integer fid) {
        return jpmpPictureService.listPicture(type, fid);
    }

    @GetMapping("/deletePictrue")
    public String deletePictrue(Integer pid) {
        return jpmpPictureService.deletePictrue(pid) ? "删除成功" : "删除异常";
    }


    @PostMapping("/picture")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) Integer type) throws Exception {
        String pathString = "/shop/picture/";
        String src = "";
        if (file != null) {
            src = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename();
        }
        JpmpPicture jpmpPicture = new JpmpPicture();
        if (type != null) {
            jpmpPicture.setType(type);
        }
        jpmpPicture.setPname("http://114.116.15.213:9099/"+src);
        pathString = pathString + src;
        try {
            File files = new File(pathString);
            if (!files.getParentFile().exists()) {
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);
            Thumbnails.of(pathString)
                    .scale(1f)/*.size(200,200)*/
                    .outputQuality(0.5f).outputFormat("jpg")
                    .toFile(pathString);

            jpmpPicture.setUrl(pathString);
            jpmpPictureService.saveOrUpdate(jpmpPicture);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);//0表示成功，1失败
        map.put("msg", "上传成功");//提示消息
        String result = new JSONObject(map).toString();
        return result;
    }

    @GetMapping("/picturecid")
    public boolean picturecid(Integer type, Integer fid) {
        //System.out.println("cid" + cid);
        jpmpCommodityService.picturecid(type, fid);
        return true;
    }


}
