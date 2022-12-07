/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
//import java.util.Base64;

/**
 *
 * @author bunhu
 */
public class AES {
    private static final String ALGORITHM = "AES";
//    private static final byte[] keyValue = new byte[16];
 
    public static Key generateRandomKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secRandom = new SecureRandom();
        keyGen.init(secRandom);
        Key key = keyGen.generateKey();
        return key;
    }
    
    public static String convertKeytoString(Key key) {
        return java.util.Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    public static Key generateKey(String keyValue) {
        return new SecretKeySpec(keyValue.getBytes(),ALGORITHM);
    }
    
    public static String encrypt(String valueToEnc, Key key) throws Exception {
 
       
       Cipher cipher = Cipher.getInstance(ALGORITHM);
       cipher.init(Cipher.ENCRYPT_MODE, key);
 
       byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
       byte[] encryptedByteValue = new Base64().encode(encValue);
       System.out.println("Encrypted Value :: " + new String(encryptedByteValue));
 
       return new String(encryptedByteValue);
   }
    
    public static String decrypt(String encryptedValue, Key key) throws Exception {
       // Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
         
        byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
 
        byte[] enctVal = cipher.doFinal(decodedBytes);
        
        System.out.println("Decrypted Value :: " + new String(enctVal));
        return new String(enctVal);
    }
    

//    @SuppressWarnings("empty-statement")
//    public static void main(String[] args) throws Exception {
//        Key key = generateRandomKey();
//        String encriptValue = encrypt("Alo 1 2 3 4",key);
//        decrypt(encriptValue,key);
//        System.out.println("----------------------------------------");
//        String value= convertKeytoString(key);
//        encriptValue= encrypt("Alo 1 2 3 4", generateKey(value));
//        decrypt(encriptValue, generateKey(value));
//        System.out.println("----------------------------------------");
//        System.out.println(value);
//        System.out.println(key.getEncoded());
//    }
}
