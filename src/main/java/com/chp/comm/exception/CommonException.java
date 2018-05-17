package com.chp.comm.exception;

import org.apache.commons.lang3.StringUtils;

public class CommonException extends BaseException {
  private static final long serialVersionUID = -4527567935254966321L;

  public CommonException(String code) {
     super(code, (String)null, (Object[])null, (Throwable)null);
  }

  public CommonException(String code, String message) {
     super(code, message, (Object[])null, (Throwable)null);
  }

  public CommonException(String code, String message, Object[] values) {
     super(code, message, values, (Throwable)null);
  }

  public CommonException(String code, String message, Object[] values, Throwable cause) {
     super(code, message, values, cause);
  }

  public String getErrorCode() {
     String errorCode = "00099";
     String message = this.getMessage() == null?this.getCode():this.getMessage();
     if(!StringUtils.isEmpty(message)) {
        String[] messageValues = StringUtils.split(message, ":");
        if(messageValues.length > 1) {
           errorCode = messageValues[0];
        }      }

     return errorCode;
  }
}