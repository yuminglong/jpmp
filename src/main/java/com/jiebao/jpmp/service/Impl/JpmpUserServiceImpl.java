package com.jiebao.jpmp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiebao.jpmp.mapper.*;
import com.jiebao.jpmp.model.*;
import com.jiebao.jpmp.service.JpmpUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class JpmpUserServiceImpl extends ServiceImpl<JpmpUserMapper, JpmpUser> implements JpmpUserService {

    @Override
    public boolean login(JpmpUser user) {
        return baseMapper.login(user.getUsername(), user.getPassword()) > 0 ? true : false;
    }

}
