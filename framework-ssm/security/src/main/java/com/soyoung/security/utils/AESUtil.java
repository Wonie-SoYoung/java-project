package com.soyoung.security.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.lang.Assert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @description: AES加密工具类，可用于一些H5跳转链接/API接口的参数加解密
 * @author: 梅晓寒
 **/
public class AESUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String KEY_AES = "AES";

    //用于构建十六进制输出
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * AES加密 128 AES/ECB/PKCS5Padding
     *
     * @param source      需要加密的字符串
     * @param key         需要加密的秘钥
     * @param hexOrBase64 true:hex,false:base64
     * @return 返回加密结果
     */
    public static String encryptAES(String source, String key, boolean hexOrBase64) {
        Assert.hasText(source, "source不能为空");
        Assert.isTrue(!StrUtil.hasBlank(key) && key.length() == 16, "key的长度需要16");
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            if (hexOrBase64) {
                return new String(AESUtil.parseByte2HexStr(cipher.doFinal(source.getBytes())));
            } else {
                return AESUtil.encodeBase64(cipher.doFinal(source.getBytes()));
            }
        } catch (Exception e) {
            throw new RuntimeException("出错啦!", e);
        }

    }

    /**
     * AES解密 128 AES/ECB/PKCS5Padding
     *
     * @param source      需要解密的字符串
     * @param key         需要解密的秘钥
     * @param hexOrBase64 true:hex,false:base64
     * @return 返回解密结果
     */
    public static String decryptAES(String source, String key, boolean hexOrBase64) {
        Assert.hasText(source, "source不能为空");
        Assert.isTrue(!StrUtil.hasBlank(key) && key.length() == 16, "key的长度需要16");
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            if (hexOrBase64) {
                return new String(cipher.doFinal(AESUtil.parseHexStr2Byte(source)));
            } else {
                return new String(cipher.doFinal(AESUtil.decodeBase64(source)));
            }
        } catch (Exception e) {
            throw new RuntimeException("出错啦!", e);
        }
    }

    /**
     * base64 加密
     *
     * @param data
     * @return
     * @author penghuiping
     * @date 2017-02-04
     */
    public static String encodeBase64(byte[] data) {
        Assert.notNull(data, "data不能为null");
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * base64 解密
     *
     * @param text
     * @return
     * @author penghuiping
     * @date 2017-02-04
     */
    public static byte[] decodeBase64(String text) {
        Assert.hasText(text, "text不能为空");
        return Base64.getDecoder().decode(text);
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
