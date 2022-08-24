package com.example.demo.helpers;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CipherHelper {
    public static String keys="AqX/HDem3uccXfH=";

    public static IvParameterSpec generateIv(String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String input, IvParameterSpec iv, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec skey = new SecretKeySpec(keys.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, skey, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] cipherText = cipher.doFinal(input.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, IvParameterSpec iv, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec skey = new SecretKeySpec(keys.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, skey, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
