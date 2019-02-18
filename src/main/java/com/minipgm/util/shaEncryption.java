/**
 * Utility password encryption tool (SHA-256)
 *
 * @author Peiyuan
 * 2019-02-17
 */


package com.minipgm.util;

import java.nio.charset.StandardCharsets;
import java.security.*;
import org.apache.commons.codec.binary.Hex;

public class shaEncryption {


    public static String passwordEncryption(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "Encryption Failed";
    }

}
