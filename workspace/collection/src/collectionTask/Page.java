package collectionTask;

import java.util.Random;

public class Page {
	public static void main(String[] args) {
		User user = new User();
		UserField userField = new UserField();
		DBConnecter users = new DBConnecter();
		

		
		
//		userField.join(); //ȸ������
		System.out.println(userField.users.get(0)); 
		//ȸ������ �� ���� users�� �� ��峪 Ȯ��
		System.out.println(userField.checkId("abc122")); 
		//id �ߺ�üũ -> �ߺ��Ǹ� user�� ������ ������
		System.out.println(userField.checkId("abc123"));
		//id �ߺ� �ƴϸ� null
		System.out.println(userField.login("abc122", userField.decrypt("abc123"))); 
		//�α��� Ȯ�� -> �ߵǸ� user�� ������ ������
		System.out.println(userField.changePw("abc12")); //��й�ȣ ����
		System.out.println(userField.users.get(0)); //��й�ȣ �� ����Ƴ� Ȯ��
		}	
}
