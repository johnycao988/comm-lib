package com.cs.keys;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;

import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;

import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec; 
import java.util.HashMap;

import java.util.Map;

import javax.crypto.Cipher;

public class KeyUtil {

	public static final String KEY_ALGORITHM = "RSA";

	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";

	private static final String PRIVATE_KEY = "RSAPrivateKey";

	private static final int MAX_ENCRYPT_BLOCK = 117;

	private static final int MAX_DECRYPT_BLOCK = 128;

	public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {

		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return key.getEncoded();

	}

	public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {

		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return key.getEncoded();

	}

 

	public static Map<String, Object> generateKeys() throws Exception {

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);

		keyPairGen.initialize(1024);

		KeyPair keyPair = keyPairGen.generateKeyPair();

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PUBLIC_KEY, publicKey);

		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;

	}

	public static byte[] signWithPrivateKey(byte[] encryptedData, byte[] privateKey) throws Exception {

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey pk = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(pk);
		signature.update(encryptedData);
		return signature.sign();

	}
	
 	public static boolean verifyWithPublicKey(byte[] encryptedData, byte[] publicKey, byte[] sign) throws Exception {

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(encryptedData);
		return signature.verify(sign);
	}

	public static byte[] encryptDataByPublicKey(byte[] data, byte[] publicKey) throws Exception {

		return encryptData(getPublicKeyCipher(publicKey,Cipher.ENCRYPT_MODE), data);

	}

	public static byte[] encryptDataByPrivateKey(byte[] data, byte[] privateKey) throws Exception {

		return encryptData(getPrivateKeyCipher(privateKey,Cipher.ENCRYPT_MODE), data);

	}

	private static Cipher getPublicKeyCipher(byte[] publicKey,int opmode) throws Exception {

		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pk = keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(opmode, pk);
		return cipher;
	}

	private static Cipher getPrivateKeyCipher(byte[] privateKey,int opmode) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pk = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));

		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(opmode, pk);
		return cipher;
	}

	private static byte[] encryptData(Cipher cipher, byte[] data) throws Exception {

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;

		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	public static byte[] decryptByPublicKey(byte[] encryptedData, byte[] publicKey) throws Exception {

		return decryptData(getPublicKeyCipher(publicKey,Cipher.DECRYPT_MODE), encryptedData);
	}

	public static byte[] decryptByPrivateKey(byte[] encryptedData, byte[] privateKey) throws Exception {

		return decryptData(getPrivateKeyCipher(privateKey,Cipher.DECRYPT_MODE), encryptedData);
	}

	private static byte[] decryptData(Cipher cipher, byte[] encryptedData) throws Exception {

		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

}