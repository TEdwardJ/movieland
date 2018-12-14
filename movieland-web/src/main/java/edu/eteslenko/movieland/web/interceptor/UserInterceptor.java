package edu.eteslenko.movieland.web.interceptor;

import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.service.SecurityService;
import edu.eteslenko.movieland.web.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("uuid");
        if (token != null) {
            User user = securityService.getByUuid(token);
            UserHolder.setCurrentUser(user);
        }
        return true;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
