package collectionTask;

import java.util.Random;

public class Page {
	public static void main(String[] args) {
		User user = new User();
		UserField userField = new UserField();
		DBConnecter users = new DBConnecter();
		

		
		
//		userField.join(); //회원가입
		System.out.println(userField.users.get(0)); 
		//회원가입 한 정보 users에 잘 담겼나 확인
		System.out.println(userField.checkId("abc122")); 
		//id 중복체크 -> 중복되면 user의 정보를 가져옴
		System.out.println(userField.checkId("abc123"));
		//id 중복 아니면 null
		System.out.println(userField.login("abc122", userField.decrypt("abc123"))); 
		//로그인 확인 -> 잘되면 user의 정보를 가져옴
		System.out.println(userField.changePw("abc12")); //비밀번호 변경
		System.out.println(userField.users.get(0)); //비밀번호 잘 변경됐나 확인
		}	
}
