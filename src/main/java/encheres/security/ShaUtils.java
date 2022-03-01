package encheres.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaUtils {

	public static byte[] digest(String pwd) throws NoSuchAlgorithmException {
		// Algorithme SHA-256 pour chiffrer le mot de passe
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] encodedHash = md.digest(pwd.getBytes(StandardCharsets.UTF_8));
		byte[] result = md.digest(encodedHash);

		return result;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
