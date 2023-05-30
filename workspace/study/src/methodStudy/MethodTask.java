package methodStudy;

import methodStudy.MethodTask;

public class MethodTask {
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
	String changeUpperLower(String string) {
		char c = ' '; //string의 문자를 담아줄 변수
		String result = ""; //result에 문자를 누적연결해줄 때는 null이 아닌 빈 문자열로 초기화
		for (int i = 0; i < string.length(); i++) {
			c = string.charAt(i); //c에 string의 i번째 문자를 담아준다
			if(c >= 65 && c <=  90) { //string의 i번째 문자가 대문자인지 검사
				result += (char)(c + 32); //대문자라면 소문자로 변경
			}else if(c >= 97 && c <= 122) { //string의 i번째 문자가 소문자인지 검사
				result += (char)(c-32); //소문자라면 대문자로 변경
			}else {
				result += c; //만약 다른 문자가 들어있다면, 그대로 리턴
			}
		}
		return result;
	}
		
//	문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
	int countString(String string, char c) {
		int count = 0; //문자의 개수를 담아줄 변수
		char check = ' '; //string의 문자를 담아줄 변수
		
		for (int i = 0; i < string.length(); i++) {
			check = string.charAt(i); //check에 string의 i번째 문자를 담는다.
			if(check == c) { //check에 담긴 string 문자와 사용자가 입력한 c가 같은지 검사
				count ++; //만약 같다면 count에 1씩 더해준다
			}
		}
		return count;
	}
	
//	5개의 정수를 입력받은 후 원하는 인덱스의 값을 구해주는 메소드
	int getValue(int[] arData, int index) {
		return arData[index]; //사용자가 원하는 인덱스의 번호에 담긴 값을 리턴
	}

//	5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
	int[] getMaxMin(int[] arData) {
		int[] result = {arData[0], arData[1]};
		for (int i = 0; i < arData.length; i++) {
			if(result[0] < arData[i]) { //최대값 검사
				result[0] = arData[i];
			}
			if(result[1] > arData[i]) {//최소값 검사
				result[1] = arData[i];
			}
		}
		return result;
	}

//	indexOf() 만들기
//	indexOf() -> () 안의 문자가 몇 번째 인덱스에 위치해있는지 알려준다.
	int indexOf(String string, char c) {
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == c) { //string의 i번째 문자가 c와 같다면
				 return i; //i를 리턴한다. -> 인덱스 번호
			}
		}
		return -1; //만약 문자열 안에 입력한 문자가 없다면 -1을 리턴한다.
	}
		
		
		
	
	public static void main(String[] args) {
		MethodTask mt = new MethodTask();
		//1부터 10까지 출력
//		mt.printOneToTen(); 
		
		//홍길동 n번 출력
//		mt.pringHong(10);
		
		//사용자가 입력한 이름 n번 출력
//		mt.printName("정지욱", 5);
		
		//사용자가 입력한 세 정수의 뺄셈
//		System.out.println(mt.subtractNumber(10, 5, 2));
		
		//사용자가 입력한 두 정수의 나눗셈의 몫과 나머지
//		int[] result = mt.divideNumber(10, 3);
//		if(result == null) {
//			System.out.println("0으로 나눌 수 없다.");
//		}else {
//			System.out.println("몫 : " + result[0]);
//			System.out.println("나머지 : " + result[1]);
//		}
		
		//1부터 10까지의 합
//		System.out.println(mt.sumOneToTen(10));
		
		//홀수를 짝수로, 짝수를 홀수로 변경
//		System.out.println(mt.changeNumber(2));
		
		//대문자를 소문자, 소문자를 대문자로 변경
//		System.out.println(mt.changeUpperLower("HEllo"));
		
		//원하는 문자의 개수
//		System.out.println(mt.countString("hello", 'k'));
		
		//원하는 인덱스의 값
//		int[] arData = {1, 2, 3, 4, 5};
//		System.out.println(mt.getValue(arData, 2));
		
		//최대값, 최소값
//		int[] arData = {10, 2, 3, 4, 6};
//		System.out.println("최대값: " + mt.getMaxMin(arData)[0]);
//		System.out.println("최소값: " + mt.getMaxMin(arData)[1]);
		
		//indexOf()
//		System.out.println(mt.indexOf("hello", 'l'));
	}
}




















