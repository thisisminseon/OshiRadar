package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordBcrypt {
	
	// 비밀번호 암호화
	public static String hashPassword(String password) {
		// password : 입력 받는 비밀번호
		return BCrypt.hashpw(password, BCrypt.gensalt());
		// gensalt() : 솔트(salt) 자동 생성. 해시 된 비밀번호로 반환
		// salt : 해시 함수에 추가되는 임의의 데이터. 동일한 비밀번호라도 솔트가 다르면 해시 값이 달라짐.
	}
	
	// 비밀번호 비교
	public static boolean checkPassword(String password, String hashed) {
		// hashed : 해시 된 비밀번호
		return BCrypt.checkpw(password, hashed);
		// 입력 된 비밀번호와 해시 된 비밀번호를 비교
	}

}
