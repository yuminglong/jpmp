package com.jiebao.jpmp.controller;

import com.jiebao.jpmp.model.JpmpStair;
import com.jiebao.jpmp.service.JpmpCommodityService;
import com.jiebao.jpmp.service.JpmpSecondService;
import com.jiebao.jpmp.service.JpmpStairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
public class JpmpSkipController {
    @Autowired
    private JpmpStairService jpmpStairService;

    @Autowired
    private JpmpSecondService jpmpSecondService;

    @Autowired
    private JpmpCommodityService jpmpCommodityService;

    @GetMapping("/")
    public String testSelect() {
        // List<JpmpStair> list = jpmpStairService.list();
        //return "index";
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        List<JpmpStair> list = jpmpStairService.list();
        return "index";
    }


    @GetMapping("/skip")
    public String skip(@RequestParam String table) {
        return table;
    }

}
