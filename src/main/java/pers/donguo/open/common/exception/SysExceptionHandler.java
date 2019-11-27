package pers.donguo.open.common.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;
import pers.donguo.open.common.utils.R;

/**
 * <p>Title: SysExceptionHandler.java </p>
 * <p>Description: 异常处理器</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class SysExceptionHandler {
	//异常Exception禁止直接捕获，直接捕获会导致springsecurity 的 AccessDeniedHandler 失效！！！
//	@ExceptionHandler(Exception.class)
//	public R handleException(Exception e){
//		log.error(e.getMessage(), e);
//		return R.error();
//	}
//	
	
	/**
	 * @title: handleSysException
	 * @Description: SysExceptionHandler's handleSysException | 自定义系统异常 手动抛出
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SysException.class)
	public R handleSysException(SysException e){
		return R.error(e.getCode(), e.getMessage());
	}
	
	/**
	 * @title: handlerNoFoundException
	 * @Description: SysExceptionHandler's handlerNoFoundException | 404 未找到指定路径异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return R.error(404, "请求路径不存在！");
	}

	/**
	 * @title: handleDuplicateKeyException
	 * @Description: SysExceptionHandler's handleDuplicateKeyException | mybatis在重复插入主键时会直接抛出此异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return R.error("该条记录已存在，禁止重复插入！");
	}
	
	 /**
	  * @title: validationExceptionHandler
	  * @Description: SysExceptionHandler's validationExceptionHandler | hibernate-validator 参数验证异常
	  * @param exception
	  * @return
	  */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
        return R.error(500, msg);
    }
	
}
