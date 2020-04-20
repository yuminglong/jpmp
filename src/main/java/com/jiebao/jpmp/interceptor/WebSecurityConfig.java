package com.jiebao.jpmp.interceptor;

import com.jiebao.jpmp.model.JpmpUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class WebSecurityConfig implements HandlerInterceptor {

    public final static String SESSION_KEY = "user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute(SESSION_KEY) != null) {
          /* JpmpUser user = (JpmpUser)request.getSession().getAttribute(SESSION_KEY);
            System.out.println(user.getPassword()+ user.getUsername());*/
            return true;
        }
        response.sendRedirect("/");
        return false;
    }


}


