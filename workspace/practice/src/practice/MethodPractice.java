package practice;

import practice.MethodPractice;

public class MethodPractice {
//	1~10까지 println()으로 출력하는 메소드
	void printOneToTen() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1);
		}
	}
	
//	"홍길동"을 n번 println()으로 출력하는 메소드
	void pringHong(int number) {
		for (int i = 0; i < number; i++) {
			System.out.println(i + 1 + ". 홍길동");
		}
	}
	
//	이름을 n번 println()으로 출력하는 메소드
	void printName(String name, int number) {
		for (int i = 0; i < number; i++) {
			System.out.println(i + 1 + "." +name);
		}
	}
		
//	세 정수의 뺄셈을 해주는 메소드	
	int subtractNumber(int firstNumber, int secondNumber, int thirdNumber) {
		int result = 0;
		result = firstNumber - secondNumber - thirdNumber;
		return result;
	}
	
//	두 정수의 나눗셈 후 몫과 나머지 2개를 구하는 문제
	int[] divideNumber(int firstNumber, int secondNumber) {
		int[] result = null;
		if(secondNumber != 0) {
			result = new int[2];
			result[0] = firstNumber / secondNumber;
			result[1] = firstNumber % secondNumber;
		}
		return result;
	}
	
//	1~n까지의 합을 구해주는 메소드
	int sumOneToTen(int number) {
		int total = 0;
		for (int i = 0; i < number; i++) {
			total += i + 1;
		}
		return total;
	}
	
//	홀수를 짝수로, 짝수를 홀수로 바꿔주는 메소드
	int changeNumber(int number) {
		if(number == 0) {
			return number;
		}else {
			return number + 1;
		}
	}
	
//	문자열을 입력받고 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드
	String changeUpperLower(String message) {
		char c = ' ';
		String result = "";
		for (int i = 0; i < message.length(); i++) {
			c = message.charAt(i);
			if(c >= 65 && c <=  90) {
				result += (char)(c + 32);
			}else if(c >= 97 && c <= 122) {
				result += (char)(c-32);
			}else {
				result += c;
			}
		}
		return result;
	}
		
//	문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
	int countString(String message, char c) {
		int count = 0;
		char check = ' ';
		
		for (int i = 0; i < message.length(); i++) {
			check = message.charAt(i);
			if(check == c) {
				count ++;
			}
		}
		return count;
	}
	
//	5개의 정수를 입력받은 후 원하는 인덱스의 값을 구해주는 메소드
	int getValue(int[] arData, int index) {
		return arData[index];
	}

//	5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
	int[] getMaxMin(int[] arData) {
		int[] result = {arData[0], arData[1]};
		for (int i = 0; i < arData.length; i++) {
			if(result[0] < arData[i]) {
				result[0] = arData[i];
			}
			if(result[1] > arData[i]) {
				result[1] = arData[i];
			}
		}
		return result;
	}

//	indexOf() 만들기
//	indexOf() -> () 안의 문자가 몇 번째 인덱스에 위치해있는지 알려준다.
	int indexOf(String string, char c) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == c) {
				 return i;
			}
		}
		return -1;
	}
		
		
		
	
	public static void main(String[] args) {
		MethodPractice mp = new MethodPractice();
//		mp.printOneToTen();
//		mp.pringHong(10);
//		mp.printName("정지욱", 5);
//		System.out.println(mp.subtractNumber(10, 5, 2));
//		int[] result = mp.divideNumber(10, 3);
//		if(result == null) {
//			System.out.println("0으로 나눌 수 없다.");
//		}else {
//			System.out.println("몫 : " + result[0]);
//			System.out.println("나머지 : " + result[1]);
//		}
//		System.out.println(mp.sumOneToTen(10));
//		System.out.println(mp.changeNumber(2));
//		System.out.println(mp.changeUpperLower("HEllo"));
//		System.out.println(mp.countString("hello", 'k'));
//		int[] arData = {1, 2, 3, 4, 5};
//		System.out.println(mp.getValue(arData, 2));
//		int[] arData = {10, 2, 3, 4, 6};
//		System.out.println(mp.getMaxMin(arData)[0]);
//		System.out.println(mp.getMaxMin(arData)[1]);
//		System.out.println(mp.indexOf("hello", 'l'));
	}
}




















