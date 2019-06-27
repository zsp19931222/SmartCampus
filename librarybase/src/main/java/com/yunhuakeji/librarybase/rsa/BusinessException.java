package com.yunhuakeji.librarybase.rsa;



/**
 * 通用的业务异常
 */
public final class BusinessException extends RuntimeException {

    private static final String DEFAULT_CODE = "SYSTEM";

    private static final String DEFAULT_MESSAGE = "程序抛出了异常";


    private final String code;

    private final String message;

    /**
     * 在业务代码里面，不允许调这个无参的构造方法
     */
    @Deprecated
    public BusinessException() {
        this(DEFAULT_CODE,DEFAULT_MESSAGE);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        this(DEFAULT_CODE,message);
    }

    public BusinessException(EnumIFace enumIFace) {
        this(enumIFace.getCode(),enumIFace.getMessage());
    }

    public BusinessException(EnumIFace enumIFace, boolean writableStackTrace) {
        this(enumIFace.getCode(),enumIFace.getMessage(),writableStackTrace);
    }

    /**
     *
     * @param code
     * @param message
     * @param writableStackTrace 是否打印堆栈信息
     */
    public BusinessException(String code, String message, boolean writableStackTrace) {
        super(message, null, false, writableStackTrace);
        this.message = message;
        this.code = code;
    }


    @Override
    public String toString() {
        return this.code + BaseConstant.COLON_STRING + this.message;
    }
}
