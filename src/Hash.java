import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.*;

public class Hash {
    String UsernameInput, PasswordInput;
    byte[] salt;

    Hash(String InputUsername, String InputPassword) {

        UsernameInput = InputUsername;
        PasswordInput = InputPassword;

    }

    void SetSalt(String input) {

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecureRandom random = new SecureRandom();
            KeySpec spec = new PBEKeySpec(input.toCharArray());

            byte[] salt = factory.generateSecret(spec).getEncoded();
            System.out.println(salt);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    byte[] HashPassword() {

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecureRandom random = new SecureRandom();
            // byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(PasswordInput.toCharArray(), salt, 65536, 128);

            // SecretKeyFactory factory =
            // SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println(hash);
            return hash;
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}