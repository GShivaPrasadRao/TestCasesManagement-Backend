package com.buildtechknowledge.spring.data.mongodb.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    private static final int SALT_LENGTH = 16;

    public static String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = salt + password;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(saltedPassword.getBytes());
        byte[] hashedPassword = messageDigest.digest();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
