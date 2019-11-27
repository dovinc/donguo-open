package pers.donguo.open.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.donguo.open.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: SysAuthenticationEntryPoint.java </p>
 * <p>Description: 用来解决匿名用户访问无权限资源时的异常没有携带token或者token无效【切点】</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Component("sysAuthenticationEntryPoint")
public class SysAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Autowired
	ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //设置为对应http状态码
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(R.error(HttpStatus.UNAUTHORIZED)));
    }
}
