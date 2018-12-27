package com.cly.comm.util;

import java.util.UUID;

import net.iharder.Base64;

public class IDUtil {

	private IDUtil() {

	}

	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getRandomBase64UUID() {

		String uid = getRandomUUID();

		return Base64.encodeBytes(uid.getBytes()).toString();
	}

	private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	/** js
	 * function genUUID8(){
     
      
      var chars=new Array( "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
              "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
              "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
              "U", "V", "W", "X", "Y", "Z" );
     
      var uid=S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4();    
     
      var uid8='';
         
       for (var i = 0; i < 8; i++) {
              var str = uid.substr(i * 4,  4);
              var x = parseInt(str, 16);
              uid8=uid8+(chars[x % 0x3E]);
         }    
     
      return uid8;
}


	 * 
	 * @return
	 */
	public static String genUUID8() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

}
