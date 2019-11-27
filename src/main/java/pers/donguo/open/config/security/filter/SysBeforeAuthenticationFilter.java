package pers.donguo.open.config.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import pers.donguo.open.common.exception.SysException;
import pers.donguo.open.common.utils.R;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.config.security.SecurityUserDetailsService;
import pers.donguo.open.modules.sys.entity.SysUserToken;
import pers.donguo.open.modules.sys.service.SysUserTokenService;

/**
 * <p>Title: JWTAuthorizationFilter.java</p>
 * <p>Description: 在认证过滤器【AuthenticationFilter】之前需要执行的过滤器</p>
 * 
 * @author Penguin
 * @date 2019年10月3日
 * @version 1.0
 */
@Slf4j
public class SysBeforeAuthenticationFilter extends BasicAuthenticationFilter {
	private ObjectMapper objectMapper;
	private SysUserTokenService sysUserTokenService;
	private SecurityUserDetailsService securityUserDetailsService;

	public SysBeforeAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	/**
	 * Description：初始化时，在此处注入需要使用的Bean，由于filter 不属于spring容器管理
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		// getWebApplicationContext(ServletContext sc, String attrName) 从ServletContext
		// 中 获取到 指定名称的 的attr 属性。
		// 无第二个参数则为取默认的WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE

		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		if (objectMapper == null)
			objectMapper = ac.getBean(ObjectMapper.class);
		if (sysUserTokenService == null)
			sysUserTokenService = ac.getBean(SysUserTokenService.class);
		if (securityUserDetailsService == null)
			securityUserDetailsService = ac.getBean(SecurityUserDetailsService.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		if (objectMapper == null)
			objectMapper = ac.getBean(ObjectMapper.class);
		if (sysUserTokenService == null)
			sysUserTokenService = ac.getBean(SysUserTokenService.class);
		if (securityUserDetailsService == null)
			securityUserDetailsService = ac.getBean(SecurityUserDetailsService.class);

		// 获取请求头中的token字符串
		String headerTokenStr = request.getHeader(SysConst.TOKEN_HEADER);
		// 如果请求头中没有Authorization信息则直接放行了
		if (headerTokenStr == null || !headerTokenStr.startsWith(SysConst.TOKEN_PREFIX)) {
			super.doFilterInternal(request, response, chain);
			return;
		}
		log.info("tokenStr" + headerTokenStr);
		log.info("------------------------------------------------------------");
		// 如果请求头中有token，则进行解析，并且设置认证信息
		try {
			String tokenStr = StrUtil.removePrefix(headerTokenStr, SysConst.TOKEN_PREFIX);
			// 获取springSecurity 的本地变量
			UserDetails userDetails = getAuthenticationByToken(tokenStr);
			// 此处调用Authentication 3个参数的方法，其中会有super.setAuthenticated(true); 用于后续会不会进行登录认证的判断
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
		} catch (SysException e) {
			// 返回json形式的错误信息
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			// 统一异常处理
			response.getWriter().write(objectMapper.writeValueAsString(R.error(e.getCode(), e.getMessage())));
			response.getWriter().flush();
			return;
		}
		super.doFilterInternal(request, response, chain);
	}
	/**
	 * @title: getAuthenticationByToken
	 * @Description: SysBeforeAuthenticationFilter's getAuthenticationByToken 通过token  获取userDetails 认证对象【Authentication】
	 * @param tokenStr
	 * @return
	 * @throws SysException
	 */
	private UserDetails getAuthenticationByToken(String tokenStr) throws SysException {
		// 此处应当从库中或者redis 中查询session信息
		SysUserToken token = sysUserTokenService.findByToken(tokenStr);
		// token 为空 或token过期时间在当前时间之前 抛出token过期异常
		if (token == null || token.getExpiredTime().before(new Date()))
			throw new SysException(HttpStatus.UNAUTHORIZED.value(), "Token is expired, please login again!");
		// 每次登陆时，刷新token过期时间【后期决定是否保留此逻辑】
		sysUserTokenService.refreshExpiredTime(token);
		String username = token.getUsername();
		UserDetails userDetails = securityUserDetailsService.loadUserByUsername(username);
		return userDetails;
	}
}
