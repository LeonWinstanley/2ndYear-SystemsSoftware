import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.*;

public class Hash {
    String UsernameInput;
    char[] PasswordInput;
    byte[] salt;

    Hash(String InputUsername, char[] InputPassword) {

        UsernameInput = InputUsername;
        PasswordInput = InputPassword;

    }

    void SetSalt() {

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecureRandom random = new SecureRandom();
            KeySpec spec = new PBEKeySpec(UsernameInput.toCharArray());

            byte[] salt = factory.generateSecret(spec).getEncoded();
            System.out.println(salt);

        } catch (Exception e) {
            System.out.println("SetSalt():" + e);
        }
    }

    byte[] HashPassword() {
        byte[] hash = null;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecureRandom random = new SecureRandom();
            // byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(PasswordInput, salt, 65536, 128);

            // SecretKeyFactory factory =
            // SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
            System.out.println(hash);

        } catch (Exception e) {
            System.out.println("HashPassword():" + e);
        }
        return hash;

    }

}