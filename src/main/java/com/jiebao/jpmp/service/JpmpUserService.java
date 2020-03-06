package com.jiebao.jpmp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jiebao.jpmp.model.JpmpUser;


public interface JpmpUserService extends IService<JpmpUser> {


     boolean login(JpmpUser user);

}