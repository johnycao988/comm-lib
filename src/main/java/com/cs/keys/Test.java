package com.cs.keys;


import java.util.Base64;
import java.util.Map;

public class Test {

	public static void main(String[] args) {

		Map<String, Object> keyMap;

		try {

			keyMap = KeyUtil.generateKeys();
			
			byte[] privateKey=KeyUtil.getPrivateKey(keyMap) ;
			byte[] publicKey=KeyUtil.getPublicKey(keyMap); 
			
			System.out.println("Public Key:\r\n"+Base64.getEncoder().encodeToString(publicKey));
			System.out.println("Private Key:\r\n"+Base64.getEncoder().encodeToString(privateKey));
			
			
			String data="testkl4k 12k231l2";
			
			testEncryptDataByPublicKey(data, publicKey, privateKey);
			
			testEncryptDataByPrivateKey(data, publicKey, privateKey);
			
			testSign(data, publicKey, privateKey);
			
		//	byte[] sign=KeyUtil.signWithPrivateKey(encryptedData,privateKey);
			
		//	System.out.println("Verify:"+KeyUtil.verifyWithPublicKey(ss.getBytes(), KeyUtil.getPublicKey(keyMap), sign));

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	

	}

	private static void testEncryptDataByPublicKey(String data, byte[] publicKey, byte[] privateKey) throws Exception{
		
        System.out.println("Test Encrypt Data by Public Key  is:\r\n"+data);
		
		byte[] encryptedData=KeyUtil.encryptDataByPublicKey(data.getBytes(), publicKey);
		
		System.out.println("encrpted Data is:\r\n"+new String(encryptedData));
		
		byte[] decryptedData=KeyUtil.decryptByPrivateKey(encryptedData, privateKey);
		
		System.out.println("Decrpted Data is:\r\n"+new String(decryptedData));
		
	}
	
private static void testEncryptDataByPrivateKey(String data, byte[] publicKey, byte[] privateKey) throws Exception{
		
        System.out.println("Test Encrypt Data by Private Key  is:\r\n"+data);
		
		byte[] encryptedData=KeyUtil.encryptDataByPrivateKey(data.getBytes(), privateKey);
		
		System.out.println("encrpted Data is:\r\n"+new String(encryptedData));
		
		byte[] decryptedData=KeyUtil.decryptByPublicKey(encryptedData, publicKey);
		
		System.out.println("Decrpted Data is:\r\n"+new String(decryptedData));
		
	}

private static void testSign(String data, byte[] publicKey, byte[] privateKey) throws Exception{
	
    System.out.println("Test Sign..."+data);
	
	byte[] encryptedData=KeyUtil.encryptDataByPrivateKey(data.getBytes(), privateKey);
	
	System.out.println("encrpted Data is:\r\n"+new String(encryptedData));
	
	byte sign[]=KeyUtil.signWithPrivateKey(encryptedData, privateKey);
	
	System.out.println("Sign Verifiy :"+ KeyUtil.verifyWithPublicKey(encryptedData, publicKey, sign));
	
}

}
