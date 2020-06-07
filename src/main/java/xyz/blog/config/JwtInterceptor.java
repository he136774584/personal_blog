package xyz.blog.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.blog.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if (header == null || "".equals(header) || !header.startsWith("Bearer ")) {
            throw new RuntimeException("令牌不正确！");
        }
        String token = header.substring(7);
        try {
            Claims claims = jwtUtils.parseJWT(token);
            String roles = (String) claims.get("role");
            if (roles != null && roles.equals("1")) {
                request.setAttribute("admin", token);
            }
            if (roles != null && roles.equals("2")) {
                request.setAttribute("user", token);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("令牌不正确！");
        }
    }
}
