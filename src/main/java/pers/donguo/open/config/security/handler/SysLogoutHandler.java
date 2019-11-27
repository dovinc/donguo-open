package pers.donguo.open.config.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.modules.sys.service.SysUserTokenService;

/**
 * <p>Title: SysLogoutHandler.java </p>
 * <p>Description: 自定义退出处理器</p>
 * @author Penguin
 * @date 2019年10月8日
 * @version 1.0
 */
@Slf4j
public class SysLogoutHandler implements LogoutHandler {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		//注入Service 和 Mapper
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		if(objectMapper == null)
			objectMapper = ac.getBean(ObjectMapper.class);
		if(sysUserTokenService == null)
			sysUserTokenService = ac.getBean(SysUserTokenService.class);
		
		//两种处理方式  1.request中获取token 在token表中将其过期  2.authentication中获取用户，将用户的对应token过期
		String headerTokenStr = request.getHeader(SysConst.TOKEN_HEADER);
		if(headerTokenStr != null) {
			String tokenStr = StrUtil.removePrefix(headerTokenStr, SysConst.TOKEN_PREFIX);
			sysUserTokenService.expired(tokenStr);
		}
		try {
			PrintWriter responseWriter = response.getWriter();
			responseWriter.write(objectMapper.writeValueAsString(R.ok("用户退出成功")));
			responseWriter.flush();
			log.info("退出成功");
		} catch (IOException e) {
			e.printStackTrace();
			log.error("退出失败，io异常！", e);
		}
	}
	
}
