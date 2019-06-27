package com.yunhuakeji.librarybase.rsa;


import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 * 使用时注意事项：
 * 1.不能每个方法都去new RSAUtil，会造成速度非常慢
 * 2.前端-到后端使用的加密算法是“RSA/ECB/PKCS1Padding”
 * 3.后端-到前端使用的加密算法是“RSA”
 * 4.使用时，必须在运行代码中加入如下代码：Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
 */
public final class RSAUtil {
    private Charset charset;
    private String paddingMode;
    private static final String RSA_BC = "BC";
    private static final String RSA_KEY_FACTORY_ARITHMETIC_DEFAULT = "RSA";

    public RSAUtil(String charset, String paddingMode) {
        this.charset = Charset.forName(charset);
        this.paddingMode = paddingMode;
    }

    public RSAUtil(Charset charset, String paddingMode) {
        this.charset = charset;
        this.paddingMode = paddingMode;
    }
    public RSAUtil(String paddingMode) {
        this.paddingMode = paddingMode;
    }


    /**
     * 使用私钥解密
     *
     * @param keyString
     * @param dataString
     * @return
     * @throws Exception
     */
    public String decryptByKeyString(String keyString, String dataString) throws Exception {
        RSAPrivateKey key = this.loadPrivateKeyByStr(keyString);
        byte[] dataByte = Base64.decode(dataString, 0);
        return new String(this.doRSA(key, 2, dataByte), this.charset);
    }

    /**
     * 使用公钥加密
     *
     * @param keyString
     * @param dataString
     * @return
     * @throws Exception
     */
    public String encryptByKeyString(String keyString, String dataString) throws Exception {
        RSAPublicKey key = this.loadRsaPublicKey(keyString);
        return new String(Base64.encode(this.doRSA(key, 1, dataString.getBytes(this.charset)), 0));
    }

    /**
     * 真正处理加解密的方法
     *
     * @param key
     * @param RSAMode
     * @param dataByte
     * @return
     * @throws Exception
     */
    private byte[] doRSA(Key key, int RSAMode, byte[] dataByte) throws Exception {
        if (key == null) {
            throw new BusinessException(RSAErrorEnum.SECURIRY_KEY_IS_NULL);
        } else {
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance(this.paddingMode);
                cipher.init(RSAMode, key);
                byte[] output = cipher.doFinal(dataByte);
                return output;
            } catch (NoSuchAlgorithmException e) {
                throw new BusinessException(RSAErrorEnum.ARITHMETIC_NOT_EXISTS);
            } catch (InvalidKeyException e) {
                throw new BusinessException(RSAErrorEnum.SECURITY_KEY_IS_INVALID);
            } catch (IllegalBlockSizeException e) {
                throw new BusinessException(RSAErrorEnum.CONTENT_LENGTH_IS_INVALID);
            } catch (BadPaddingException e) {
                throw new BusinessException(RSAErrorEnum.CONTENT_IS_BROKEN);
            } catch (Exception e) {
                throw new BusinessException(RSAErrorEnum.OTHER_SECURITY_EXCEPTION);
            }
        }
    }

    /**
     * 生成公钥对象
     *
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    private RSAPublicKey loadRsaPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(publicKeyStr, 0);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_FACTORY_ARITHMETIC_DEFAULT);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(RSAErrorEnum.ARITHMETIC_NOT_EXISTS);
        } catch (InvalidKeySpecException e) {
            throw new BusinessException(RSAErrorEnum.SECURITY_KEY_IS_INVALID);
        } catch (NullPointerException e) {
            throw new BusinessException(RSAErrorEnum.SECURIRY_KEY_IS_NULL);
        } catch (Exception e) {
            throw new BusinessException(RSAErrorEnum.OTHER_SECURITY_EXCEPTION);
        }
    }

    /**
     * 生成私钥对象
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    private RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(privateKeyStr, 0);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_FACTORY_ARITHMETIC_DEFAULT);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(RSAErrorEnum.ARITHMETIC_NOT_EXISTS);
        } catch (InvalidKeySpecException e) {
            throw new BusinessException(RSAErrorEnum.SECURITY_KEY_IS_INVALID);
        } catch (NullPointerException e) {
            throw new BusinessException(RSAErrorEnum.SECURIRY_KEY_IS_NULL);
        } catch (Exception e) {
            throw new BusinessException(RSAErrorEnum.OTHER_SECURITY_EXCEPTION);
        }
    }

}