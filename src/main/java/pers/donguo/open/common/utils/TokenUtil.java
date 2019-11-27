package pers.donguo.open.common.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * <p>Title: TokenUtil.java </p>
 * <p>Description: token工具类</p>
 * @author Penguin
 * @date 2019年10月4日
 * @version 1.0
 */
public class TokenUtil {
	/**
	 * @title: createToken
	 * @Description: 不带任何参数的token 生成
	 * @return
	 */
	public static String createToken() {
		return DigestUtil.md5Hex(UUID.randomUUID(true).toString() + String.valueOf(System.currentTimeMillis()), "UTF-8");
	}
	
	/**
	 * @title: TokenUtil's createToken
	 * @Description:有args参数 无 但不带tokenHeader 的token 生成
	 * @param args 参数数组
	 * @return
	 */
	public static String createToken(String[] args) {
		if(args == null)
			throw new NullPointerException("Param String[] args can not be null! you can use the createToken() without param.");

		StringBuilder argsBuilder = new StringBuilder();
		for (String arg : args) {
			argsBuilder.append(arg);
		}
		return DigestUtil.md5Hex(argsBuilder.toString(), "UTF-8");
	}
	
	
	/**
	 * @title: createToken
	 * @Description: 有tokenHeader参数 无args 的token 生成
	 * @param tokenHead
	 * @return
	 */
	public static String createToken(String tokenHead) {
		if(tokenHead == null)
			throw new NullPointerException("Param String tokenHead args can not be null! you can use the createToken() without param.");
		
		String baseToken = createToken();
		if (StrUtil.isBlank(tokenHead))
			return baseToken;
		else
			return tokenHead + baseToken;
	}
	/**
	 * @title: createToken
	 * @Description: 带全参数的token生成
	 * @param tokenHead
	 * @param args
	 * @return
	 */
	public static String createToken(String tokenHead, String[] args) {
		String baseToken = createToken(args);
		if (StrUtil.isBlank(tokenHead))
			return baseToken;
		else
			return tokenHead + baseToken;
	}

	/**
	 * @title: compareToken
	 * @Description: 比较token 是否相等
	 * @param firstToken
	 * @param secondToken
	 * @return
	 */
	public static boolean compareToken(String firstToken, String secondToken) {
		if (StrUtil.isNotBlank(firstToken))
			throw new NullPointerException("Param String firstToken can not be null!");
		if (StrUtil.equals(firstToken, secondToken))
			return true;
		return false;
	}
	
}