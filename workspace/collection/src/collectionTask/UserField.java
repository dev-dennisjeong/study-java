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
	
	String encryptPw = ""; //��ȣȭŰ ���� ����
	final int KEY = 3; //��,��ȣȭ �� �� ����� ���
	String code = ""; //���� �ڵ� ���� ����
	
	//ID�ߺ��˻�
	public User checkId(String id) {
		User user =  new User();
//		�ݺ������� �˻�
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getId().equals(id)) {
				return user;
			} //�ߺ��� ������ users�� ��� user�� ������ ���� -> ȸ������ �Ұ���
		}
		return null; //�ߺ��� ���ٸ� ����ִ� �Ŵϱ� null�� ���� -> ȸ������ ����
	}
	
	
	//ȸ������
	public void join(User user) { //������ �޾ƾ���
			users.add(user); //users�� user ������ �־���
	}
			
//�α���
	public User login(String id, String password) { 

		User user = new User(); //user ��üȭ
		
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getId().equals(id) && users.get(i).getPassword().equals(encrypt(password))) { //��ȣȭ�� pw
				user =  users.get(i);
				
				return user; 
				//���� users�� �ִ� id, pw�� �Է��� id, pw�� ���ٸ� users���� �ش� ������ user�� ��� user ����
		      }
		}
		return null; // �ϳ��� ��ġ���� ������ null -> �α��� ����
	}
	
//	��ȣȭ
	public String encrypt(String password) { 
		for (int i = 0; i < password.length(); i++) { 
			encryptPw += (char)(password.charAt(i) * KEY); 
			//pw�� ���ڷ� �ϳ��ϳ� key ���� �̿��� �ٲ��ְ� �� ���ڸ� ���ڿ� encryptPw�� �־���
		}
		return encryptPw;
	}
//	��ȣȭ ���� ����, �׳� key�� ���ذ���
	String decryptPw = "";
	public String decrypt(String encryptPw) {
		for (int i = 0; i < encryptPw.length(); i++) {
			decryptPw += (char)(encryptPw.charAt(i) / KEY); 
		}
		return decryptPw;
	}
	
//	��й�ȣ ����(��й�ȣ ã�� ����)
	public String changePw(String password) { //������ �޾ƾ���
		String result = null; //user�� pw�� �־��� ����
		for (User user : users) { //forEach�� UserŸ���� user ���� : user ������ ����ִ� users
			user.setPassword(password); //�Է��� pw�� ����
			result = user.getPassword(); //�Է��� pw�� �����ͼ� result�� ����
		}
		return result; //result ����
	}
	
		
	
//	������ȣ ����
	public String sendCode(String phoneNumber) { //������ȣ StringŸ������ �޾Ƽ� �����Ұ���
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			code += r.nextInt(10);			
		}//���� 0~9 �� 5���� ���ڸ� �������� code�� �־���
		
		String api_key = "NCSF8LRG4LDF323M";
		String api_secret = "UGS24OKHX6YCPEFTFUK7WBZFEFD35UCD";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); //�߽��� ��ȣ -> �Է��� ��ȣ
		params.put("from", "01041998127");
		params.put("type", "SMS");
		params.put("text", code); //code -> ���ڷ� ���� ���� �ڵ�
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCode());
		}
		return code; //����ڿ��� ���� ����code�� ������
	}
	
	
	
	
	
	
	
	
}







