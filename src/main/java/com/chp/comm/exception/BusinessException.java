package com.chp.comm.exception;

/**
 * @Class Name BusinessException
 * @Author zhangli
 * @Create In 2017年12月14日
 */
public class BusinessException extends CommonException {

    private static final long serialVersionUID = 1817037495049619471L;

    /**
     * @param code 异常码
     * @param message 异常信息
     * @param values 参数
     * @param cause 异常
     */
    public BusinessException(String code, String message, Object[] values, Throwable cause) {
        super(code, message, values, cause);
    }

    /**
     * @param code 异常码
     * @param message 异常信息
     * @param values 参数
     */
    public BusinessException(String code, String message, Object[] values) {
        super(code, message, values);
    }
    
    /**
     * @param code 异常码
     * @param values 参数
     */
    public BusinessException(String code, Object[] values) {
      super(code, code, values);
    }

    /**
     * @param code 异常码
     * @param message 异常信息
     */
    public BusinessException(String code, String message) {
        super(code, message);
    }

    /**
     * @param code 异常码
     */
    public BusinessException(String code) {
        super(code);
    }

    

}
