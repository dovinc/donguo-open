package pers.donguo.open.common.utils;

import java.util.HashMap;

import org.springframework.http.HttpStatus;

/**
 * <p>Title: R.java </p>
 * <p>Description: 统一返回类 继承HashMap 的数据结构</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
public class R extends HashMap<String, Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", Code.OK.value);
		put("msg", Code.OK.description);
	}

	public R(String key, Object value) {
		put("code", Code.OK.value);
		put("msg", Code.OK.description);
		put(key, value);
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;

	}

	public static R ok(String key, Object value) {
		R r = new R();
		r.put(key, value);
		return r;

	}

	public static R ok(String msg, String key, Object value) {
		R r = ok(msg);
		r.put(key, value);
		return r;

	}
	/**
	 * @title: error
	 * @Description: R's error
	 * @return
	 */
	public static R error() {
		return error(Code.INTERNAL_SERVER_ERROR.value, Code.INTERNAL_SERVER_ERROR.description);
	}

	public static R error(String msg) {
		return error(Code.INTERNAL_SERVER_ERROR.value, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		if (code != Code.OK.value)
			r.put("code", code);
		else
			r.put("code", Code.INTERNAL_SERVER_ERROR.value);
		r.put("msg", msg);
		return r;
	}
	
	public static R error(int code, Exception e) {
		R r = new R();
		if (code != Code.OK.value)
			r.put("code", code);
		else
			r.put("code", Code.INTERNAL_SERVER_ERROR.value);
		r.put("msg", e.getMessage());
		return r;
	}
	
	public static R error(Exception e) {
		return error(Code.INTERNAL_SERVER_ERROR.value, e);
	}
	
	public static R error(Code code) {
		if(code == null)
			throw new NullPointerException("ResponseCode can not be null !");
		R r = new R();
		r.put("code", code.value);
		r.put("msg", code.description);
		return r;
	}
	
	public static R error(HttpStatus status) {
		if(status == null)
			throw new NullPointerException("HttpStatus can not be null !");
		R r = new R();
		r.put("code", status.value());
		r.put("msg", status.getReasonPhrase());
		return r;
	}
	
	/**
	 * 以下为带有返回实例的R返回值
	 * @param msg
	 * @return
	 */
	public static R withResultObj(Object resultObj) {
		R r = new R();
		r.put("resultObj", resultObj);
		return r;
	}
	
	public static R withResultObj(String key, Object value) {
		HashMap<String, Object> resultObj = new HashMap<>();
		resultObj.put(key, value);
		return withResultObj(resultObj);
	}
	
	/**
	 * <p>Title: R.java enum Code</p>
	 * <p>Description: 返回编码enum</p>
	 * @author Penguin
	 * @date 2019年10月14日
	 * @version 1.0
	 */
	public enum Code{
		/**
		 * D: 服务端正常返回
		 */
		OK(200, "OK", "服务端正常返回。"),
		/**
		 * D: 401 客户试图未经授权访问受密码保护的页面。 ->前端处理方式为跳转至登录页面
		 */
		UNAUTHORIZED(401, "Unauthorized", "客户试图未经授权访问受密码保护的页面。"),
		/**
		 * D: 资源不可用。服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致。->前端处理方式为跳转至指定的未授权页面路由
		 */
		FORBIDDEN(403, "Forbidden", "资源不可用。服务器理解客户的请求，但拒绝处理它。"),
		/**
		 * D: 无法找到指定位置的资源。
		 */
		NOT_FOUND(404, "Not Found", "无法找到指定位置的资源。"),
		
		/**
		 * D：服务端错误
		 */
		INTERNAL_SERVER_ERROR(500, "Server Error", "服务端错误！");
		private int value;
		private String name;
		private String description;
		Code(int value, String name,String description) {
			this.value = value;
			this.name = name;
			this.description = description;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
	}
}
