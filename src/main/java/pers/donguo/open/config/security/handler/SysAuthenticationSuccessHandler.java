package pers.donguo.open.config.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.donguo.open.common.utils.R;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.config.security.SysUserDetails;
import pers.donguo.open.modules.sys.entity.SysUserToken;
import pers.donguo.open.modules.sys.service.SysUserTokenService;

/**
 * <p>
 * Title: SysAuthenticationSuccessHandler.java
 * </p>
 * <p>
 * Description:登录成功自定义处理器
 * </p>
 * 
 * @author Penguin
 * @date 2019年10月10日
 * @version 1.0
 */
@Component("sysAuthenticationSuccessHandler")
public class SysAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpStatus.OK.value());
		// 生成token
		SysUserDetails userDetails = (SysUserDetails) authentication.getPrincipal();
		// 根据userId 创建token 并保存
		SysUserToken userToken = sysUserTokenService.createUserToken(userDetails.getUserId(),userDetails.getUsername());
		String tokenStr = SysConst.TOKEN_PREFIX + userToken.getToken();
		// 获取response 输出流，将token写入header与responseBody中 【前端获取token的方式二选一即可】
		response.setHeader(SysConst.TOKEN_HEADER, tokenStr);
		response.getWriter().write(objectMapper.writeValueAsString(R.withResultObj(SysConst.TOKEN_HEADER, tokenStr)));
	}

}
