package pers.donguo.open.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.donguo.open.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * <p>Title: SysAccessDeniedHandler.java </p>
 * <p>Description: 用来解决认证过的用户访问无权限资源时的异常，没有访问权限</p>
 * @author Penguin  
 * @date 2019年9月29日  
 * @version 1.0
 */
@Component("sysAccessDeniedHandler")
public class SysAccessDeniedHandler implements AccessDeniedHandler {
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write(objectMapper.writeValueAsString(R.error(HttpStatus.FORBIDDEN)));
	}
}
