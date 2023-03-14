package org.mnsg.resource.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String text){
        MessageDigest instance = null;
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = instance.digest(text.getBytes());
//        return new BigInteger(1, digest).toString(16);
        return byte2hex(digest);
    }
    public static String byte2hex(byte[] b) {
        StringBuilder hs= new StringBuilder();
        String stmp="";
        for (byte value : b) {
            //为了保证二进制机器数不变，这里需要& 0XFF
            stmp = (Integer.toHexString(value & 0XFF));
            //如果只有一位，需要在前面补上0凑足两位
            if(stmp.length() == 1) {
                hs.append("0").append(stmp);
            }else {
                hs.append(stmp);
            }
        }
        return hs.toString();
    }
}
