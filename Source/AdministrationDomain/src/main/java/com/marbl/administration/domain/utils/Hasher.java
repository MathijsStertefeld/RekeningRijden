package com.marbl.administration.domain.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    
    private String algorithm;
    private String charsetName;

    public Hasher(String algorithm, String charsetName) {
        this.algorithm = algorithm;
        this.charsetName = charsetName;
    }

    public String hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(text.getBytes(charsetName));
            byte[] digest = md.digest();
            BigInteger bi = new BigInteger(1, digest);
            text = String.format("%0" + (digest.length << 1) + "X", bi);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
        }

        return text;
    }
}
