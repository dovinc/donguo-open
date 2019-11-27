package pers.donguo.open.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pers.donguo.open.config.security.filter.SysBeforeAuthenticationFilter;
import pers.donguo.open.config.security.handler.SysLogoutHandler;

/**
 * <p>Title: SpringSecurityConfig.java </p>
 * <p>Description: springSecurity @EnableWebSecurity：此注解会启用Spring Security </p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("securityUserDetailService")
	private UserDetailsService userDetailsService;
//	@Autowired
//	private CustomAuthenticationProvider authProvider;

	@Autowired
	@Qualifier("myMD5PasswordEncoder")
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("sysAccessDeniedHandler")
	private AccessDeniedHandler sysAccessDeniedHandler;
	
	@Autowired
	@Qualifier("sysAuthenticationEntryPoint")
	private SysAuthenticationEntryPoint sysAuthenticationEntryPoint;
	
	@Autowired()
	@Qualifier("sysAuthenticationFailureHandler")
	private AuthenticationFailureHandler sysAuthenticationFailureHandler;
	
	@Autowired()
	@Qualifier("sysAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler sysAuthenticationSuccessHandler;
	
//	@Autowired()
//	@Qualifier("sysBeforeAuthenticationFilter")
//	private SysBeforeAuthenticationFilter sysBeforeAuthenticationFilter;
	
//	@Bean  是用autoWired 和 bean 都是可以的  但是由于可能框架中已经有了多个passwordEncoder 的实现类 注册 所以要指定名字
//    public MD5PasswordEncoder getMD5PasswordEncoder() {
//        return new MD5PasswordEncoder();
//    }
	
	
	
	@Bean(name = "sysBeforeAuthenticationFilter")  //是用autoWired 和 bean 都是可以的  但是由于可能框架中已经有了多个passwordEncoder 的实现类 注册 所以要指定名字
	public SysBeforeAuthenticationFilter getSysBeforeAuthenticationFilter() throws Exception {
	      return new SysBeforeAuthenticationFilter(authenticationManager());
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)//authenticationProvider(authProvider).
				.passwordEncoder(passwordEncoder);
	}

	/**
	 * 1）HttpSecurity支持cors。 2）默认会启用CRSF，此处因为没有使用thymeleaf模板（会自动注入_csrf参数）， *
	 * 要先禁用csrf，否则登录时需要_csrf参数，而导致登录失败。 3）antMatchers：匹配 "/" 路径，不需要权限即可访问，匹配 "/user"
	 * 及其以下所有路径， *都需要 "USER" 权限 4）配置登录地址和退出地址
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
//        //swagger 允许访问
//        .regexMatchers("/doc/.*")
//        .permitAll()
				// 登录接口权限
				.regexMatchers("/sysUsers/login").permitAll()

				// 其余接口 访问受限
				.anyRequest().authenticated()

				.and()
				// token 访问,关闭session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and().exceptionHandling()
				// 自定义401返回
				.authenticationEntryPoint(sysAuthenticationEntryPoint)
				.accessDeniedHandler(sysAccessDeniedHandler).and().logout().permitAll().and()
				// token filter 根据header 生成认证信息 在UsernamePasswordAuthenticationFilter之前进行过滤器处理
				.addFilterBefore(new SysBeforeAuthenticationFilter(authenticationManager()),
						UsernamePasswordAuthenticationFilter.class);
		
		http.formLogin()
			.loginProcessingUrl("/sysUsers/login")
			.successHandler(sysAuthenticationSuccessHandler)
			.failureHandler(sysAuthenticationFailureHandler);

		// 定制退出
		http.logout()
				// .logoutUrl("/sys/doLogout") //只支持定制退出url
				// 支持定制退出url以及httpmethod
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
				.addLogoutHandler(
						(request, response, authentication) -> System.out.println("========Logout Start========"))
				.addLogoutHandler(new SysLogoutHandler()).logoutSuccessHandler(((request, response, authentication) -> {
					System.out.println("=====4=======");
//                    response.sendRedirect("/html/logoutsuccess1.html");
				}));
		// .logoutSuccessUrl("/html/logoutsuccess2.html") //成功退出的时候跳转的页面
		// .deleteCookies() //底层也是使用Handler实现的额
		// 清除认证信息
//                .clearAuthentication(true);
		// session失效
//                .invalidateHttpSession(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
//		.and()
//		.apply(smsCodeAuthenticationSecurityConfig)
//			.and()
//		.apply(imoocSocialSecurityConfig)
//			.and()
//		//记住我配置，如果想在'记住我'登录时记录日志，可以注册一个InteractiveAuthenticationSuccessEvent事件的监听器
//		.rememberMe()
//			.tokenRepository(persistentTokenRepository())
//			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//			.userDetailsService(userDetailsService)
//			.and()
//		.sessionManagement()
//			.invalidSessionStrategy(invalidSessionStrategy)
//			.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
//			.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
//			.expiredSessionStrategy(sessionInformationExpiredStrategy)
//			.and()
//			.and()
//		.logout()
//			.logoutUrl("/signOut")
//			.logoutSuccessHandler(logoutSuccessHandler)
//			.deleteCookies("JSESSIONID")
//			.and()
//		.csrf().disable();
	}

}