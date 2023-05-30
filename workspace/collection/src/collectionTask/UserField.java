package collectionTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


public class UserField {
//	User user = new User();

	public static ArrayList<User> users = DBConnecter.users;
	
	String encryptPw = ""; //암호화키 받을 변수
	final int KEY = 3; //암,복호화 할 때 사용할 상수
	String code = ""; //인증 코드 넣을 변수
	
	//ID중복검사
	public User checkId(String id) {
		User user =  new User();
//		반복문으로 검사
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getId().equals(id)) {
				return user;
			} //중복이 있으면 users에 담긴 user의 정보를 리턴 -> 회원가입 불가능
		}
		return null; //중복이 없다면 비어있는 거니까 null을 리턴 -> 회원가입 가능
	}
	
	
	//회원가입
	public void join(User user) { //유저로 받아야함
			users.add(user); //users에 user 정보를 넣어줌
	}
			
//로그인
	public User login(String id, String password) { 

		User user = new User(); //user 객체화
		
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getId().equals(id) && users.get(i).getPassword().equals(encrypt(password))) { //암호화된 pw
				user =  users.get(i);
				
				return user; 
				//만약 users에 있는 id, pw가 입력한 id, pw와 같다면 users에서 해당 정보를 user에 담고 user 리턴
		      }
		}
		return null; // 하나라도 일치하지 않으면 null -> 로그인 실패
	}
	
//	암호화
	public String encrypt(String password) { 
		for (int i = 0; i < password.length(); i++) { 
			encryptPw += (char)(password.charAt(i) * KEY); 
			//pw의 문자로 하나하나 key 값을 이용해 바꿔주고 그 문자를 문자열 encryptPw에 넣어줌
		}
		return encryptPw;
	}
//	복호화 위와 동일, 그냥 key만 빼준거임
	String decryptPw = "";
	public String decrypt(String encryptPw) {
		for (int i = 0; i < encryptPw.length(); i++) {
			decryptPw += (char)(encryptPw.charAt(i) / KEY); 
		}
		return decryptPw;
	}
	
//	비밀번호 변경(비밀번호 찾기 서비스)
	public String changePw(String password) { //유저로 받아야함
		String result = null; //user의 pw를 넣어줄 변수
		for (User user : users) { //forEach문 User타입의 user 변수 : user 정보가 들어있는 users
			user.setPassword(password); //입력한 pw로 세팅
			result = user.getPassword(); //입력한 pw를 가져와서 result에 담음
		}
		return result; //result 리턴
	}
	
		
	
//	인증번호 전송
	public String sendCode(String phoneNumber) { //인증번호 String타입으로 받아서 리턴할거임
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			code += r.nextInt(10);			
		}//숫자 0~9 중 5개의 숫자를 랜덤으로 code에 넣어줌
		
		String api_key = "NCSF8LRG4LDF323M";
		String api_secret = "UGS24OKHX6YCPEFTFUK7WBZFEFD35UCD";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); //발신자 번호 -> 입력한 번호
		params.put("from", "01041998127");
		params.put("type", "SMS");
		params.put("text", code); //code -> 문자로 보낸 랜덤 코드
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCode());
		}
		return code; //사용자에게 보낸 인증code를 리턴함
	}
	
	
	
	
	
	
	
	
}







