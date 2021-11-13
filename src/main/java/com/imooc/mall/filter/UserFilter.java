package com.imooc.mall.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * 描述：     用户过滤器
 */
public class UserFilter implements Filter {

    public static ThreadLocal<User> userThreadLocal = new ThreadLocal();
    public User currentUser = new User();

    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ("OPTIONS".equals(request.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = request.getHeader(Constant.JWT_TOKEN);
            if (StringUtils.isEmpty(token)) {
                PrintWriter out = new HttpServletResponseWrapper(
                        (HttpServletResponse) servletResponse).getWriter();
                out.write("{\n"
                        + "    \"status\": 10007,\n"
                        + "    \"msg\": \"NEED_LOGIN\",\n"
                        + "    \"data\": null\n"
                        + "}");
                out.flush();
                out.close();
                return;
            }

            Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            try {
                DecodedJWT jwt = verifier.verify(token);
                currentUser.setId(jwt.getClaim(Constant.USER_ID).asInt());
                currentUser.setRole(jwt.getClaim(Constant.USER_ROLE).asInt());
                currentUser.setUsername(jwt.getClaim(Constant.USER_NAME).asString());
                userThreadLocal.set(currentUser);
            } catch (TokenExpiredException e) {
                //token过期，抛出异常
                throw new ImoocMallException(ImoocMallExceptionEnum.TOKEN_EXPIRED);
            } catch (JWTDecodeException e) {
                //解码失败，抛出异常
                throw new ImoocMallException(ImoocMallExceptionEnum.TOKEN_WRONG);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
