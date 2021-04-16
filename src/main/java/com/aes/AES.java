package com.aes;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AES {

    private static byte[] encrypt(byte[] text, byte[] key) throws Exception {
        SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(text);
    }

    private static byte[] decrypt(byte[] text, byte[] key) throws Exception {
        SecretKeySpec aesKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return cipher.doFinal(text);
    }

    public static String encodeAES(String text, String key) throws Exception {
        byte[] keybBytes = DigestUtils.md5(key);
        byte[] passwdBytes = text.getBytes();
        byte[] aesBytyes = encrypt(passwdBytes, keybBytes);
        return new String(Base64.encodeBase64(aesBytyes));
    }

    public static String deCodeAES(String password, String key) throws Exception {
        byte[] keybBytes = DigestUtils.md5(key);
        byte[] debase64Bytes = Base64.decodeBase64(password.getBytes());
        return new String(decrypt(debase64Bytes, keybBytes));
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        try{
            String encodeAES = encodeAES("13724082628","hHMogstx1gcJQLcu");
            System.out.println(encodeAES);
            System.out.println(deCodeAES("+eNPuf+OyiIwUybAm1ik2A==","hHMogstx1gcJQLcu"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
