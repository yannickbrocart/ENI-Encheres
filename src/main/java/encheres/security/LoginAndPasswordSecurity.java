package encheres.security;

import java.security.NoSuchAlgorithmException;

import encheres.BusinessException;

public class LoginAndPasswordSecurity {

	public static String passwordEncryption(String password) throws BusinessException {
		byte[] shaInBytes;
		try {
			shaInBytes = ShaUtils.digest(password);
			String encryptedPassword = ShaUtils.bytesToHex(shaInBytes);
			return encryptedPassword;
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException("Impossible de chiffrer le mot de passe - " + e.getMessage());
		}
	}

	public static void validateLoginAndPassword(String login, String password) throws BusinessException {
		if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			throw new BusinessException("Le login ou le mot de passe sont incorrects");
		}
	}
}
