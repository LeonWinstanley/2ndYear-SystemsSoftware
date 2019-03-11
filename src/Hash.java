import java.io.*;
import java.math.BigInteger;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.*;

public class Hash {
    String UsernameInput;
    char[] PasswordInput;
    String fileHash;
    String algoHash;
    byte[] salt = null;

    Hash(String InputUsername, char[] InputPassword) {

        UsernameInput = InputUsername;
        PasswordInput = InputPassword;

    }

    String HashPassword() {
        String hashed = "";
        try {
            Integer interations = 65565;
            byte[] salt = SetSalt();
            PBEKeySpec spec = new PBEKeySpec(PasswordInput, salt, interations, 64);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            hashed = interations + ":" + ToHex(salt) + ":" + ToHex(hash);

        } catch (Exception e) {
            System.out.println("HashPassword():" + e);
        }
        return hashed;

    }

    byte[] SetSalt() {
        byte[] salt = new byte[16];
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (Exception e) {
            System.out.println("SetSalt():" + e);
        }
        return salt;
    }

    String ToHex(byte[] convert) {
        String hex = "";
        try {
            BigInteger bi = new BigInteger(1, convert);
            hex = bi.toString(16);
            int paddingLength = (convert.length * 2) - hex.length();
            if (paddingLength > 0) {
                hex = String.format("%0" + paddingLength + "d", 0) + hex;
            }
        } catch (Exception e) {
            System.out.println("ToHex :" + e);
        }
        return hex;
    }

    // ***************** Validate hash ***********************

    void InputFileHash(String hashFromFile, String hashFromAlgo) {
        fileHash = hashFromFile;
        algoHash = hashFromAlgo;
        ValidateHash();
    }

    boolean ValidateHash() {
        boolean result = false;
        try {
            boolean matched = ValidatePassword(fileHash, algoHash);
            System.out.println(matched);
            result = matched;
        } catch (Exception e) {
            System.out.println("ValidateHash() : " + e);
        }
        return result;
    }

    boolean ValidatePassword(String fileHashValidate, String algoHashValidate) {
        boolean result = false;

        String[] parts = fileHashValidate.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        try {

            PBEKeySpec spec = new PBEKeySpec(fileHashValidate.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            if (diff == 0) {
                result = true;
            }
        } catch (Exception e) {

            System.out.println("ValidatePassword() :" + e);

        }
        return result;
    }

    byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}