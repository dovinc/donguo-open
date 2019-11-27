package pers.donguo.open.config.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.donguo.open.common.utils.R;

/**
 * <p>Title: SysAuthenticationFailureHandler.java </p>
 * <p>Description: 登录失败自定义处理器</p>
 * @author Penguin
 * @date 2019年10月10日
 * @version 1.0
 */
@Component("sysAuthenticationFailureHandler")
public class SysAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.getWriter().write(objectMapper.writeValueAsString(R.error("登录失败!"+ exception.getMessage())));
	}


}
