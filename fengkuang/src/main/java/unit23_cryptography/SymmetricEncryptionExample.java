package unit23_cryptography;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class SymmetricEncryptionExample {

    /**
     *要使用Java编写一个对称加密算法的例子，你需要掌握以下必备知识：
     * 了解对称加密算法：熟悉常见的对称加密算法原理，如AES（Advanced Encryption Standard）、DES（Data Encryption Standard）、3DES（Triple DES）等，
     * 理解它们的工作模式（如ECB、CBC、CTR等）和填充方式（如PKCS5Padding、NoPadding等）。
     * Java加密体系（JCA/JCE）：Java Cryptography Architecture (JCA) 和 Java Cryptography Extension (JCE) 提供了加密服务的框架和实现。
     * 了解如何使用JCE来实现对称加密算法，包括加载算法、创建密钥和Cipher对象等。
     * 密钥生成：知道如何生成对称密钥，包括固定密钥和动态密钥生成。对于动态密钥，通常使用KeyGenerator类。
     * Cipher类：熟悉javax.crypto.Cipher类的使用，它是执行加密和解密操作的核心类。了解如何初始化Cipher对象（包括加密模式、填充方式和密钥），以及如何调用doFinal方法进行实际的加密或解密。
     * 密钥和IV管理：对于需要IV（初始化向量）的模式（如CBC），了解如何生成和管理IV。IV通常需要与密文一起发送给解密方。
     * 异常处理：了解如何处理加密和解密过程中可能抛出的异常，如NoSuchAlgorithmException、InvalidKeyException、IllegalBlockSizeException等。
     * 编码与解码：了解如何使用Base64或其他编码方式处理加密后的字节数据，以便于存储或传输。
     * 资源管理：了解如何安全地使用和清理敏感资源，比如密钥材料和Cipher对象，以避免内存泄漏或安全漏洞。
     *
     */

    public static void main(String[] args) throws Exception {
        // 生成AES密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES-128密钥长度
        SecretKeySpec secretKey = new SecretKeySpec(keyGen.generateKey().getEncoded(), "AES");

        // 生成IV
        byte[] iv = new byte[16]; // AES通常使用16字节的IV
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 加密
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] plaintext = "Hello, symmetric encryption!".getBytes("UTF-8");
        byte[] ciphertext = cipher.doFinal(plaintext);

        // 结合IV和密文
        String combined = Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(ciphertext);

        System.out.println("Combined IV+Ciphertext: " + combined);

        // 解密
        String[] parts = combined.split(":");
        byte[] decodedIv = Base64.getDecoder().decode(parts[0]);
        byte[] decodedCiphertext = Base64.getDecoder().decode(parts[1]);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(decodedIv));
        byte[] decryptedtext = cipher.doFinal(decodedCiphertext);

        System.out.println("Decrypted Text: " + new String(decryptedtext, "UTF-8"));
    }
}
