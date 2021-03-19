package cho.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
import egovframework.rte.fdl.cryptography.impl.EgovARIACryptoServiceImpl;

@Component("sha256Util")
public class UserSha256 {

	/*
	 * public static String decrypted(String planText, String key) {
	 * EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
	 * EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new
	 * EgovARIACryptoServiceImpl();
	 *
	 * String hashedPassword = egovPasswordEncoder.encryptPassword(key);
	 * egovPasswordEncoder.setHashedPassword(hashedPassword);
	 * egovPasswordEncoder.setAlgorithm("SHA-256");
	 * egovARIACryptoServiceImpl.setPasswordEncoder(egovPasswordEncoder);
	 * egovARIACryptoServiceImpl.setBlockSize(1025);
	 *
	 * String decryptedStr =
	 * Base64.encodeBase64URLSafeString(egovARIACryptoServiceImpl.decrypt(Base64.
	 * decodeBase64(planText.getBytes()), key)); byte[] decrypted =
	 * egovARIACryptoServiceImpl.decrypt(Base64.decodeBase64(planText.getBytes()),
	 * key);
	 *
	 * return decryptedStr;
	 *
	 * }
	 */

	/*
	 * public static String encrypted(String planText, String key) {
	 *
	 * EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
	 * EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new
	 * EgovARIACryptoServiceImpl();
	 *
	 * String hashedPassword = egovPasswordEncoder.encryptPassword(key);
	 * egovPasswordEncoder.setHashedPassword(hashedPassword);
	 * egovPasswordEncoder.setAlgorithm("SHA-256");
	 * egovARIACryptoServiceImpl.setPasswordEncoder(egovPasswordEncoder);
	 * egovARIACryptoServiceImpl.setBlockSize(1025);
	 *
	 * String encryptedStr =
	 * Base64.encodeBase64URLSafeString(egovARIACryptoServiceImpl.encrypt(Base64.
	 * decodeBase64(planText.getBytes()), key)); //byte[] encrypted =
	 * egovARIACryptoServiceImpl.encrypt(Base64.decodeBase64(planText.getBytes()),
	 * key);
	 *
	 * return encryptedStr; }
	 */
	public static String testSHA256(String pwd) {
		try{

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			//출력
			return hexString.toString();

		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}


}
