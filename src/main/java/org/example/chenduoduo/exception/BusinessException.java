package org.example.chenduoduo.exception;

import org.example.chenduoduo.common.ErrorCode;

/**
 * @author luochen
 * @date 2025/4/20 10:10
 * 捕捉程序运行时异常
 */

public class BusinessException extends RuntimeException{
    private int code;
    private String description;
    /**
     * 通过错误码来获取错误信息
     * @param errorCode
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
    /**
     * 通过错误码和错误描述来获取错误信息
     * @param errorCode
     * @param description
     */
    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
//    /**
//     * 通过输入的错误码、错误信息、错误描述来获取错误信息，不通过封装错误码来获取错误信息
//     * @return
//     */
//    public BusinessException(int code, String message, String description) {
//        super(message);
//        this.code = code;
//        this.description = description;
//    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}
