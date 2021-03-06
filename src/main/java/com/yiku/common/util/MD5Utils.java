package com.yiku.common.util;

import java.security.MessageDigest;


public class MD5Utils {

    /**
     * MD5签名
     *
     * @param paramSrc
     *            the source to be signed
     * @param key
     * @return
     * @throws Exception
     */
    public static String sign(String paramSrc, String key) {
        String origin = paramSrc + "&key=" + key;
        return md5(origin);
    }
    /**
     * MD5签名
     *
     * @param paramSrc
     *            the source to be signed
     * @param key
     * @return
     * @throws Exception
     */
    public static String signWithLine(String paramSrc, String key) {
        String origin = paramSrc + "|" + key;
        return md5(origin);
    }

    /**
     * MD5验签
     *
     * @param source
     *            签名内容
     * @param tfbSign
     *            签名值
     * @param key
     * @return
     */
    public static boolean verify(String source, String tfbSign, String key) {
        String sign = md5(source + "&key=" + key);
        return tfbSign.equals(sign);
    }

    public final static String md5(String paramSrc) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = paramSrc.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
