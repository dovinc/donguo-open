package pers.donguo.open.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import pers.donguo.open.common.utils.R;
import pers.donguo.open.common.utils.constant.SysConst;
import pers.donguo.open.config.security.SysUserDetails;
import pers.donguo.open.modules.sys.entity.SysUser;
import pers.donguo.open.modules.sys.entity.SysUserToken;
import pers.donguo.open.modules.sys.service.SysUserTokenService;

/**
 * ！！！未使用------------自定义登录逻辑 与认证逻辑
 * <p>Title: JWTAuthenticationFilter.java </p>
 * <p>Description: 过滤器</p>
 * @author Penguin  
 * @date 2019年10月3日  
 * @version 1.0
 */
public class SysAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SysUserTokenService sysUserTokenService;

    private AuthenticationManager authenticationManager;

    public SysAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        try {
            SysUser user = objectMapper.readValue(request.getInputStream(), SysUser.class);
            //从流中获取数据的时候。 此时用户未认证还没有权限信息相关
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        SysUserDetails userDetails = (SysUserDetails) authResult.getPrincipal();
        //根据userId 创建token 并保存
        SysUserToken userToken = sysUserTokenService.createUserToken(userDetails.getUserId(), userDetails.getUsername());
        response.setHeader(SysConst.TOKEN_HEADER, userToken.getToken());
        //获取response 输出流，将token写入返回结果
        response.getWriter().write(objectMapper.writeValueAsString(R.withResultObj("token", userToken.getToken())));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //身份认证失败
    	response.getWriter().write(objectMapper.writeValueAsString(R.error(R.Code.UNAUTHORIZED.getValue(), "身份认证失败")));
    }
}
