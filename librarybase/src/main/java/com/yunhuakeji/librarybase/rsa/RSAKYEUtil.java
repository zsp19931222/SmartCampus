package com.yunhuakeji.librarybase.rsa;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2019/3/5 0005.
 */

public class RSAKYEUtil {
    public static String PRIVATE_KEY =
            "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALUPuqBiS+1YbK/R" +
                    "cm+cVkpQwl9SXNBTGvxhi8kWBIL9AFlouREZsF+WfG0Ii796kTiOXxaVEa0zLjFM" +
                    "uAn/jobRZckEhE9zJAOeSyAvU8qQGFYIKjuHR9RxHCzrecVEDWDUbALlGJxZdp7a" +
                    "0/hojzVZfnbfoWgm4khcrYQa76WVAgMBAAECgYEAmc0wpMia4pR4TqlF4hUVH6+W" +
                    "TM5z1OqjQ7vAuCGh13r+bvSMMEB4F9qG+z+FJjQBY99cWpxqFYwiMvKOar/Q2ro4" +
                    "ge+EdcisU0k5WXXubUpol743H/KJpnd8kCWFk9pNiLuOl7qiJlTkB5KivvX2JxDt" +
                    "mvRO02oFfLXgAX+y00ECQQDl/iiVb6fLJhrf9FDbzvk3qRB7eruApkGr0DYS+TDy" +
                    "q72GSnkHEfj5EuB23NaeChyCJJtH09euSaIHw/chnKlpAkEAyYkabsZ+CyUPquJo" +
                    "p1UD2hFStPI+DDvniz19YTxzUcU/Fs38kTsoew82IhEm/k2rvaRkKRJLknHvm4h/" +
                    "PnwJTQJBALebKgz6YSrFlcjaAz8nQT+VIUpiVZPDpkOiabjF5LSmNBwkEfB6AZfd" +
                    "4QIjFNZ/3fhrfuddkB5cPBUU9ZKIvZkCQQCR3cPd7ZiI5HgkjN6GTkgNa4BbKwGx" +
                    "xSHfa8/1stUcmBEDpm9phlHUT7w0iAmbAgiNqBA+kdlU01ZDUlYWmZv5AkAhtORz" +
                    "WYq16KyHfM+7kgxniufX7dBcIWh4MOStl1q5OALrfsenzrf8Yzl5ss9JmmXZFuq2" +
                    "Am0fy9Sv6VjG7WrE";
    public static String PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDACwPDxYycdCiNeblZa9LjvDzb" +
                    "iZU1vc9gKRcG/pGjZ/DJkI4HmoUE2r/o6SfB5az3s+H5JDzmOMVQ63hD7LZQGR4k" +
                    "3iYWnCg3UpQZkZEtFtXBXsQHjKVJqCiEtK+gtxz4WnriDjf+e/CxJ7OD03e7sy5N" +
                    "Y/akVmYNtghKZzz6jwIDAQAB";
    public static RSAUtil encryptRSA = new RSAUtil(Charset.forName("utf-8"), "RSA/ECB/PKCS1Padding");
    public static RSAUtil decryptRSA = new RSAUtil(Charset.forName("utf-8"), "RSA");

}
