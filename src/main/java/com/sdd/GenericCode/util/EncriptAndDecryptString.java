package com.sdd.GenericCode.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Component
public class EncriptAndDecryptString {

    private static final String UTF8 = "utf-8";
    private static final String SECRET_KEY = "bvj3mr4lvpbm2bnh";
    private static final String INIT_VECTOR = "bvj3mr4lvpbm2bnh";


    public static String encrypt(String plainText){

        try {
            byte[] byteSecret = SECRET_KEY.getBytes(UTF8);
            byte[] vector = INIT_VECTOR.getBytes(UTF8);

            byte[] byteKey = Arrays.copyOf(byteSecret,32);
            byte[] byteV = Arrays.copyOf(vector,16);

            SecretKeySpec sKeySpec = new SecretKeySpec(byteKey,"AES");
            IvParameterSpec ivSpec = new IvParameterSpec(byteV);


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE,sKeySpec,ivSpec);

            byte[] byteText = plainText.getBytes(UTF8);
            byte[] buf = cipher.doFinal(byteText);

            byte[] byteBase64 = Base64.getEncoder().encode(buf);
            return new String(byteBase64);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String encryptedText){

        try {
            byte[] byteData = Base64.getDecoder().decode(encryptedText.getBytes(UTF8));
            byte[] bytePass = SECRET_KEY.getBytes(UTF8);
            byte[] byteV = INIT_VECTOR.getBytes(UTF8);

            byte[] byteKey = Arrays.copyOf(bytePass,32);
            byte[] byteIV = Arrays.copyOf(byteV,16);

            SecretKeySpec secretKeySpec = new SecretKeySpec(byteKey,"AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(byteIV);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);

            return new String(cipher.doFinal(byteData));


        }
        catch (Exception e) {
            return null;
        }
    }
    public static SecretKey generateKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }

}
