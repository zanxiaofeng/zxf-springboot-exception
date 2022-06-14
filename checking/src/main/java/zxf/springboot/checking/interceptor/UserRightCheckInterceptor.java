package zxf.springboot.checking.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import zxf.springboot.checking.annotation.UserRightCheck;
import zxf.springboot.checking.spel.SpelExtensions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserRightCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserRightCheck userRightCheck = getUserRightCheck(handler);
        if (userRightCheck != null) {
            checkUserRight(userRightCheck);
        }
        return true;
    }

    private UserRightCheck getUserRightCheck(Object handler) {
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getMethodAnnotation(UserRightCheck.class);
        }
        return null;
    }

    private void checkUserRight(UserRightCheck userRightCheck) throws Exception {
        if (userRightCheck.adminOnly() && !SpelExtensions.isAdmin()) {
            throw new IllegalAccessException("Invalid user right for adminOnly");
        }

        if (userRightCheck.rights().length > 0 && !SpelExtensions.hasRights(userRightCheck.rights())) {
            throw new IllegalAccessException("Invalid user right by list");
        }

        if (!StringUtils.isEmpty(userRightCheck.express()) && !SpelExtensions.evaluate(userRightCheck.express())) {
            throw new IllegalAccessException("Invalid user right by express");
        }
    }
}
