package com.okay.family.middle.common;


import com.okay.family.fun.frame.SourceCode;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils extends SourceCode {

    private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    private static final String CIPER_STRING = "RSA/ECB/PKCS1Padding";

    private static String pubkey;

    private static Map<Integer, String> pubkeys = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 4899433701794704772L;

        {
        put(1, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBvhQ1m/kDGjExBL3kV8a/fQL0Df6bPEUlQz+7GBCQgSZ5ZcJKZwzv/nwG+S2m5MH1efgr+98dY2b1jToVStiT9ltfPEtepwbIh0FbV78/lsizP7Ysqc0lrwxWXhpBVEHuG5qU3EiOQD5Xw1LIAoQJKjuqLs7j2GF6NQoJlUJnnQIDAQAB");
        put(2, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBvhQ1m/kDGjExBL3kV8a/fQL0Df6bPEUlQz+7GBCQgSZ5ZcJKZwzv/nwG+S2m5MH1efgr+98dY2b1jToVStiT9ltfPEtepwbIh0FbV78/lsizP7Ysqc0lrwxWXhpBVEHuG5qU3EiOQD5Xw1LIAoQJKjuqLs7j2GF6NQoJlUJnnQIDAQAB");
//        put(3, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBvhQ1m/kDGjExBL3kV8a/fQL0Df6bPEUlQz+7GBCQgSZ5ZcJKZwzv/nwG+S2m5MH1efgr+98dY2b1jToVStiT9ltfPEtepwbIh0FbV78/lsizP7Ysqc0lrwxWXhpBVEHuG5qU3EiOQD5Xw1LIAoQJKjuqLs7j2GF6NQoJlUJnnQIDAQAB");
//        put(4, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBvhQ1m/kDGjExBL3kV8a/fQL0Df6bPEUlQz+7GBCQgSZ5ZcJKZwzv/nwG+S2m5MH1efgr+98dY2b1jToVStiT9ltfPEtepwbIh0FbV78/lsizP7Ysqc0lrwxWXhpBVEHuG5qU3EiOQD5Xw1LIAoQJKjuqLs7j2GF6NQoJlUJnnQIDAQAB");

        //todo:处理不同环境的pubkey
    }};



    public static byte[] decode(String base64) throws Exception {
        return Base64.decodeBase64(base64.getBytes());
    }

    public static byte[] encryptByPubKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance(CIPER_STRING);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        return encryptedData;
    }

    /**
     * 获取加密密码
     *
     * @param pwd 明文密码
     * @return
     */
    public static String getPassword(String pwd,int envId) {
        pubkey = pubkeys.get(envId);
        String password = EMPTY;
        try {
            //获取公钥加密密码
            byte[] src = pwd.getBytes("utf-8");
            byte[] dst;
            dst = RSAUtils.encryptByPubKey(src, pubkey);
            password = encode(dst);
        } catch (Exception e) {
            logger.warn("加密密码失败！", e);
        }
        return password;
    }
    /*
    String pubkey = getPublicKey();
            logger.info("[pubkey]:" + pubkey);
            byte[] src = pwd.getBytes("utf-8");
            byte[] dst;
            try {
                dst = new RSAUtils().encryptByPubKey(src, pubkey);
                password = Base64Utils.encode(dst);
            } catch (Exception e) {
                e.printStackTrace();
            }
     */

    //    public static String encode(byte[] bytes) {
//        return new String(Base64.encodeBase64(bytes));
//
//    }
    public static String encode(byte[] bytes) throws Exception {
        return new String(Base64.encodeBase64(bytes));
    }

}