package classStudy;

import java.util.Scanner;

//���� ���� Ŭ���� �����
class Door{
	String type;
	String password;
	boolean battery;
//��й�ȣ ���� Ƚ��
	int wrongCount;
	boolean power;
	

//�� �ʱ� ��й�ȣ ����
	{
		password = "0000";
	}
//������
	public Door() {;}
	

//���ϴ� ��й�ȣ�� ���� ����
	public Door(String type, String password, boolean battery) {
		this.type = type;
		this.password = password;
		this.battery = battery;
	}
	

//�� ����
//���͸��� Ȯ���ϰ�
//���͸��� �ְ�, ���� �����ִٸ�"��й�ȣ�� �Է��ϼ��� : " ���
//��й�ȣ�� ��ġ�Ѵٸ� "���� ���Ƚ��ϴ�." ���
//��й�ȣ�� ��ġ�����ʴ´ٸ� "��й�ȣ�� Ʋ�Ƚ��ϴ�." ���
//���͸��� �ְ�, ���� �����ִٸ� "�̹� ���� �����ֽ��ϴ�." ���
//���͸��� ���ٸ�, "���͸��� �����ϴ�." ���
	void open() {
		power = true;
	}

//�� ��ױ�
//���͸��� Ȯ���ϰ�
//���͸��� �ִٸ�, "���� �����ϴ�." ���
//���͸��� �ְ�, ���� ����ִٸ� "�̹� ���� ����ֽ��ϴ�." ���
//���͸��� ���ٸ�, "���͸��� �����ϴ�." ���
	void close() {
		power = true;
	}

//���� ���� ���ؼ� ��й�ȣ 4�ڸ��� �Է¹޾ƾ� �Ѵ�.
//3�� ���� �߸� �Է����� �� "���̷�" ���
//�� ���ڿ� �� ==�� �ƴ� equals()�� ���Ѵ�.
//"���ڿ�".equals("���ڿ�");
	boolean inputPassword(String password) {
		return this.password.equals(password);
	}
	
}

public class ClassTask {
	public static void main(String[] args) {
		String message = "1. �� ���� 2. �� ��ױ�";
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		Door home = new Door("doorLock", "1515", true);
		
//		���ϴ� �κп� ǥ���� �� �ִ� ������ flag��� �Ѵ�.
		boolean flag = false;
		
		if(home.battery == false) {
			System.out.println("���͸��� �����ϴ�.");
		}
		
		while(home.battery) {
			System.out.println(message);
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				//���� ���� ���� ��
				if(!home.power) {
					//��й�ȣ�� �Է��ϰ� �˻��Ѵ�.
						System.out.println("��й�ȣ�� �Է��ϼ���.");
						if(home.inputPassword(sc.next())) {
							//��й�ȣ�� ��ġ�ϸ� ���� ����.
							home.open();
							System.out.println("���� ���Ƚ��ϴ�.");
							//Ʋ�� Ƚ�� �ʱ�ȭ(��й�ȣ�� Ʋ���� ���� ������ ���)
							home.wrongCount = 0;
						}else {
							System.out.println("�����ȣ�� Ʋ�Ƚ��ϴ�.");
							//��й�ȣ�� Ʋ���� Ʋ�� Ƚ���� �߰��Ѵ�.
							home.wrongCount++;
							//3ȸ �̻� Ʋ���� ���̷��� �︰��.
							if(home.wrongCount>2) {
								System.out.println("���̷�");
								//���� �غ�
								flag = true;
							}
							System.out.println("��й�ȣ " + home.wrongCount + "ȸ ����");
						}
						break;
				}
				System.out.println("�̹� ���� �����ֽ��ϴ�.");
				break;
			case 2:
				//���� �������� ��
				if(home.power) {
					//���� �ݴ´�.
					home.close();
					System.out.println("���� �����ϴ�.");
					//���� �غ�
					flag = true;
					break;
				}
				System.out.println("�̹� ���� ����ֽ��ϴ�.");
				break;
			default : 
				break;
			}
			//flag�� true�� �� while�� Ż��
			if(flag == true) {break;}
		
		}
		
	}
}













