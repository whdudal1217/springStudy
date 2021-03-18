package cho.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
import egovframework.rte.fdl.cryptography.impl.EgovARIACryptoServiceImpl;

@Component("sha256Util")
public class UserSha256 {

	public static String encrypt(String planText, String key) {
		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new EgovARIACryptoServiceImpl();

		String hashedPassword = egovPasswordEncoder.encryptPassword(key);
		egovPasswordEncoder.setHashedPassword(hashedPassword);
		egovPasswordEncoder.setAlgorithm("SHA-256");
		egovARIACryptoServiceImpl.setPasswordEncoder(egovPasswordEncoder);
		egovARIACryptoServiceImpl.setBlockSize(1025);

		String decryptedStr = Base64.encodeBase64URLSafeString(egovARIACryptoServiceImpl.decrypt(Base64.decodeBase64(planText.getBytes()), key));
		byte[] decrypted = egovARIACryptoServiceImpl.decrypt(Base64.decodeBase64(planText.getBytes()), key);

		return decryptedStr;

	}

	public static String decrypted(String planText, String key) {

		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new EgovARIACryptoServiceImpl();

		String hashedPassword = egovPasswordEncoder.encryptPassword(key);
		egovPasswordEncoder.setHashedPassword(hashedPassword);
		egovPasswordEncoder.setAlgorithm("SHA-256");
		egovARIACryptoServiceImpl.setPasswordEncoder(egovPasswordEncoder);
		egovARIACryptoServiceImpl.setBlockSize(1025);

		String encryptedStr = Base64.encodeBase64URLSafeString(egovARIACryptoServiceImpl.encrypt(Base64.decodeBase64(planText.getBytes()), key));
		byte[] encrypted = egovARIACryptoServiceImpl.encrypt(Base64.decodeBase64(planText.getBytes()), key);

		return encryptedStr;
	}


}
