package pers.donguo.open.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: MD5PassWordEncoder.java </p>
 * <p>Description: 自定义加密方式</p>
 * @author Penguin
 * @date 2019年10月7日
 * @version 1.0
 */
@Component("myMD5PasswordEncoder")
@Slf4j
public class MD5PasswordEncoder implements PasswordEncoder {
	/**
	 * 由于此加密接口未提供加盐的方法，所以此方法的参数rawPassword 应当为加盐后的密码
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		if(rawPassword == null)
			throw new NullPointerException("Param rawPassWord can not be null！");
		return DigestUtil.md5Hex(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.info(rawPassword+","+encodedPassword);
		// 由于springSecurity 默认加密方式的限制 将盐salt 通过encodedPassword 已加密的密码传入 在此处拆分并对比
		String[] strArr = encodedPassword.split(",");
		if(strArr.length < 2) {
			return encode(rawPassword).equals(encodedPassword);
		}else {
			log.info("strArr[0]:" + strArr[0]);
			log.info("strArr[1]:" + strArr[1]);
			log.info("加密后密码:" + encode(rawPassword+strArr[0]));
			boolean rtn = encode(rawPassword+strArr[0]).equals(strArr[1]);
			log.info("checkResult:"+rtn);
			return rtn;
		}
	}

}
