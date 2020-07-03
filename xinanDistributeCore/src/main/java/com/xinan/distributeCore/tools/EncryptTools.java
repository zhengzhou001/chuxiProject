package com.xinan.distributeCore.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 常用加密算法工具类
 */
public class EncryptTools {
	

    /**
     * 用MD5算法进行加密
     *
     * @param str 需要加密的字符串
     * @return MD5加密后的结果
     */
    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }
    public static String encode(String str, String method) {
        MessageDigest md;
        String dstr = null;
        try {
            md = MessageDigest.getInstance(method);


			char[] charArray = str.toCharArray();
			byte[] byteArray = new byte[charArray.length];
			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++){
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			dstr= hexValue.toString() ;








        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dstr;
    }
    

	/**
	 * 加密
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception 出错
	 */

	public static String encryptDes(String src,String key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据建立 DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		// 建立一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 用密匙原始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, new IvParameterSpec(key.getBytes()));
		// 现在，获取数据并加密
		// 正式执行加密操作
 		return new BASE64Encoder().encode(cipher.doFinal(src.getBytes()));
 	}
	/**
	 * 解密
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception 出错
	 */

	public static String decryptDes(String src,String key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据建立一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		// 建立一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 用密匙原始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, new IvParameterSpec(key.getBytes()));
		// 现在，获取数据并解密
		// 正式执行解密操作
		
 		return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(src))) ;
	}

	 
	public static void main(String[] args) throws Exception {
		String aString ="asd";
		String eString = encryptDes(aString, "bjtczzyf");
		System.out.println(eString); 
		System.out.println(decryptDes(eString, "bjtczzyf"));


		System.out.println(encodeMD5String("zz1001"));
	}
}