package com.marbl.administration.domain.utils;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//</editor-fold>

// This utility is used for hashing the password before authenticating.

public class Hasher {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private String algorithm;
    private String charsetName;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Hasher(String algorithm, String charsetName) {
        this.algorithm = algorithm;
        this.charsetName = charsetName;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
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
    //</editor-fold>
}
