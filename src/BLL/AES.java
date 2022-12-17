package BLL;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private SecretKey key;
    private int KEY_SIZE = 128;
    private int T_LEN = 128;
    private byte[] IV;

    public void init() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        key = generator.generateKey();
    }

    public SecretKey getSKey() {
        return key;
    }

    public void initFromStrings(String secretKey, String IV) {
        key = new SecretKeySpec(decode(secretKey), "AES");
        this.IV = decode(IV);
    }

    public static SecretKey StringtoSecretKey(String key) {//chuyển chuỗi khóa về dạng object SecretKey(khóa bí mật của AES)
        byte[] decodedKey = Base64.getDecoder().decode(key);
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;// trả về đối tượng là SecretKey
    }

    public static String encrypt(String strToEncrypt, SecretKey key1) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key1);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
        }
        return null;
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String getSecretKey() {
        return encode(key.getEncoded());
    }

    public String getIV() {
        return encode(IV);
    }

    private void exportKeys() {
        System.err.println("SecretKey : " + encode(key.getEncoded()));
        System.err.println("IV : " + encode(IV));
    }
}
