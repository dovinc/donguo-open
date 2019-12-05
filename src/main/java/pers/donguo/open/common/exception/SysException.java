package pers.donguo.open.common.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>Title: SysException.java </p>
 * <p>Description: 系统业务异常</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
public class SysException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 异常编码 默认500
	 */
	private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

	public SysException() {
		super();
	}

	public SysException(String msg) {
		super(msg);
	}

	public SysException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public SysException(int code, String msg, Throwable e) {
		super(msg, e);
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
