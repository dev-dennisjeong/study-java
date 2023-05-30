package methodStudy;

import methodStudy.MethodTask;

public class MethodTask {
//	1~10���� println()���� ����ϴ� �޼ҵ�
	void printOneToTen() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1);
		}
	}
	
//	"ȫ�浿"�� n�� println()���� ����ϴ� �޼ҵ�
	void pringHong(int number) {
		for (int i = 0; i < number; i++) {
			System.out.println(i + 1 + ". ȫ�浿");
		}
	}
	
//	�̸��� n�� println()���� ����ϴ� �޼ҵ�
	void printName(String name, int number) {
		for (int i = 0; i < number; i++) {
			System.out.println(i + 1 + "." +name);
		}
	}
		
//	�� ������ ������ ���ִ� �޼ҵ�	
	int subtractNumber(int firstNumber, int secondNumber, int thirdNumber) {
		int result = 0;
		result = firstNumber - secondNumber - thirdNumber;
		return result;
	}
	
//	�� ������ ������ �� ��� ������ 2���� ���ϴ� ����
	int[] divideNumber(int firstNumber, int secondNumber) {
		int[] result = null;
		if(secondNumber != 0) {
			result = new int[2];
			result[0] = firstNumber / secondNumber;
			result[1] = firstNumber % secondNumber;
		}
		return result;
	}
	
//	1~n������ ���� �����ִ� �޼ҵ�
	int sumOneToTen(int number) {
		int total = 0;
		for (int i = 0; i < number; i++) {
			total += i + 1;
		}
		return total;
	}
	
//	Ȧ���� ¦����, ¦���� Ȧ���� �ٲ��ִ� �޼ҵ�
	int changeNumber(int number) {
		if(number == 0) {
			return number;
		}else {
			return number + 1;
		}
	}
	
//	���ڿ��� �Է¹ް� �ҹ��ڴ� �빮�ڷ�, �빮�ڴ� �ҹ��ڷ� �ٲ��ִ� �޼ҵ�
	String changeUpperLower(String string) {
		char c = ' '; //string�� ���ڸ� ����� ����
		String result = ""; //result�� ���ڸ� ������������ ���� null�� �ƴ� �� ���ڿ��� �ʱ�ȭ
		for (int i = 0; i < string.length(); i++) {
			c = string.charAt(i); //c�� string�� i��° ���ڸ� ����ش�
			if(c >= 65 && c <=  90) { //string�� i��° ���ڰ� �빮������ �˻�
				result += (char)(c + 32); //�빮�ڶ�� �ҹ��ڷ� ����
			}else if(c >= 97 && c <= 122) { //string�� i��° ���ڰ� �ҹ������� �˻�
				result += (char)(c-32); //�ҹ��ڶ�� �빮�ڷ� ����
			}else {
				result += c; //���� �ٸ� ���ڰ� ����ִٸ�, �״�� ����
			}
		}
		return result;
	}
		
//	���ڿ��� �Է¹ް� ���ϴ� ������ ������ �����ִ� �޼ҵ�
	int countString(String string, char c) {
		int count = 0; //������ ������ ����� ����
		char check = ' '; //string�� ���ڸ� ����� ����
		
		for (int i = 0; i < string.length(); i++) {
			check = string.charAt(i); //check�� string�� i��° ���ڸ� ��´�.
			if(check == c) { //check�� ��� string ���ڿ� ����ڰ� �Է��� c�� ������ �˻�
				count ++; //���� ���ٸ� count�� 1�� �����ش�
			}
		}
		return count;
	}
	
//	5���� ������ �Է¹��� �� ���ϴ� �ε����� ���� �����ִ� �޼ҵ�
	int getValue(int[] arData, int index) {
		return arData[index]; //����ڰ� ���ϴ� �ε����� ��ȣ�� ��� ���� ����
	}

//	5���� ������ �Է¹ް� �ִ밪�� �ּҰ��� �����ִ� �޼ҵ�
	int[] getMaxMin(int[] arData) {
		int[] result = {arData[0], arData[1]};
		for (int i = 0; i < arData.length; i++) {
			if(result[0] < arData[i]) { //�ִ밪 �˻�
				result[0] = arData[i];
			}
			if(result[1] > arData[i]) {//�ּҰ� �˻�
				result[1] = arData[i];
			}
		}
		return result;
	}

//	indexOf() �����
//	indexOf() -> () ���� ���ڰ� �� ��° �ε����� ��ġ���ִ��� �˷��ش�.
	int indexOf(String string, char c) {
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == c) { //string�� i��° ���ڰ� c�� ���ٸ�
				 return i; //i�� �����Ѵ�. -> �ε��� ��ȣ
			}
		}
		return -1; //���� ���ڿ� �ȿ� �Է��� ���ڰ� ���ٸ� -1�� �����Ѵ�.
	}
		
		
		
	
	public static void main(String[] args) {
		MethodTask mt = new MethodTask();
		//1���� 10���� ���
//		mt.printOneToTen(); 
		
		//ȫ�浿 n�� ���
//		mt.pringHong(10);
		
		//����ڰ� �Է��� �̸� n�� ���
//		mt.printName("������", 5);
		
		//����ڰ� �Է��� �� ������ ����
//		System.out.println(mt.subtractNumber(10, 5, 2));
		
		//����ڰ� �Է��� �� ������ �������� ��� ������
//		int[] result = mt.divideNumber(10, 3);
//		if(result == null) {
//			System.out.println("0���� ���� �� ����.");
//		}else {
//			System.out.println("�� : " + result[0]);
//			System.out.println("������ : " + result[1]);
//		}
		
		//1���� 10������ ��
//		System.out.println(mt.sumOneToTen(10));
		
		//Ȧ���� ¦����, ¦���� Ȧ���� ����
//		System.out.println(mt.changeNumber(2));
		
		//�빮�ڸ� �ҹ���, �ҹ��ڸ� �빮�ڷ� ����
//		System.out.println(mt.changeUpperLower("HEllo"));
		
		//���ϴ� ������ ����
//		System.out.println(mt.countString("hello", 'k'));
		
		//���ϴ� �ε����� ��
//		int[] arData = {1, 2, 3, 4, 5};
//		System.out.println(mt.getValue(arData, 2));
		
		//�ִ밪, �ּҰ�
//		int[] arData = {10, 2, 3, 4, 6};
//		System.out.println("�ִ밪: " + mt.getMaxMin(arData)[0]);
//		System.out.println("�ּҰ�: " + mt.getMaxMin(arData)[1]);
		
		//indexOf()
//		System.out.println(mt.indexOf("hello", 'l'));
	}
}




















