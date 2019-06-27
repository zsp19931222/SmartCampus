package com.yunhuakeji.librarybase.rsa;


/**
 * RSA加解密错误码枚举类
 */
public enum RSAErrorEnum  implements EnumIFace {

    SECURIRY_KEY_IS_NULL("SECURITY_10001","秘钥为空, 请设置"),
    ARITHMETIC_NOT_EXISTS("SECURITY_10002","加密算法不存在，常用的有RSA,RSA/ECB/PKCS1Padding,RSA/ECB/NoPadding"),
    SECURITY_KEY_IS_INVALID("SECURITY_1003","秘钥非法"),
    CONTENT_LENGTH_IS_INVALID("SECURITY_1004","明文长度非法"),
    CONTENT_IS_BROKEN("SECURITY_1005","明文数据已损坏"),
    OTHER_SECURITY_EXCEPTION("SECURITY_1006","其他加解密异常");

    private final String code;
    private final String message;

    RSAErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
