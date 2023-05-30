package methodTest;

public class MethodTask {
//	1~10까지 println()으로 출력하는 메소드
//	void printOneToTen(int number){
//		for (int i = 0; i < number; i++) {
//			System.out.println(i+1);
//		}
//	}
		
//	"홍길동"을 n번 println()으로 출력하는 메소드
//	void printHong(int number){
//		for (int i = 0; i < number; i++) {
//			System.out.println((i+1) +".홍길동");
//		}
//	}
		
//	이름을 n번 println()으로 출력하는 메소드
//	void printName(String name, int number){
//		for (int i = 0; i < number; i++) {
//			System.out.println(name);
//		}
//	}
		
//	세 정수의 뺄셈을 해주는 메소드	
//	void minusNumber(int number1, int number2, int number3){
//		int result = 0;
//		result = number1 - number2 - number3;
//		System.out.println(result);
//	}
	
//	두 정수의 나눗셈 후 몫과 나머지 2개를 구하는 문제
	 int[] div(int number1, int number2) {
	      int[] result = null;

	      if (number2 != 0) {
	         result = new int[2];
	         result[0] = number1 / number2;
	         result[1] = number1 % number2;
	      }
	      return result;
	   }

	
//	1~n까지의 합을 구해주는
//	int addNumber(int number){
//		int result = 0;
//		for (int i = 0; i < number; i++) {
//			result += i+1;
//		}
//		return result;
//	}
	
//	홀수를 짝수로, 짝수를 홀수로 바꿔주는 메소드
//	int chageNumber(int number){
//		int result = 0;
//		result = number == 0 ? 0 : number + 1;
//		return result;
//	}
	
//	문자열을 입력받고 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드
	String changeAlphabet(String string){
		String result = "";
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if(c >= 65 && c<=90) {
				result += (char)(c+32);
			}else if(c>=97 && c<=122) {
				result += (char)(c-32);
			}else {
				result += c;
			}
		}
		return result;
	}
		
//	문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
//	int getCount(String str, char c){
//		int count = 0;
//		for (int i = 0; i < str.length(); i++) {
//			if(str.charAt(i) == c) {
//				count++;
//			}
//		}
//		return count;
//	}
	
//	5개의 정수를 입력받은 후 원하는 인덱스의 값을 구해주는 메소드
//		int getValue(int[] arData, int index) {
//			return arData[index];
//		}

//	5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
//	int[] getMaxAndMin(int[] arData){
//		int[] result = {arData[0], arData[0]};
//		
//		for (int i = 0; i < arData.length; i++) {
//			if(result[0] < arData[i]) {
//				result[0] = arData[i];
//			}
//			if(result[1] > arData[i]) {
//				result[1] = arData[i];
//			}
//		}
//		return result;
//		
//	}

//	indexOf() 만들기
//	문자열과 문자를 입력받은 뒤 해당 문자가 몇 번째 인덱스에 있는 지 검사하고
//	만약 해당 문자가 없으면 -1을 리턴한다.
	int indexOf(String str, char c) {
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == c) {
				return i;
			}
		}
		return -1;
	}

		
		
		
	
	public static void main(String[] args) {
		MethodTask mt = new MethodTask();
//		mt.printOneToTen(10);
//		mt.printHong(10);
//		mt.printName("정지욱", 5);
		
//		int[] result = mt.div(10, 2);
//		if(result == null) {
//			System.out.println("0으로 나눌 수 없습니다.");
//		}else {
//			System.out.println("몫 " + result[0]);
//			System.out.println("나머지 " + result[1]);
//		}
//		System.out.println(mt.addNumber(10));
//		System.out.println(mt.chageNumber(3));
//		System.out.println(mt.changeAlphabet("HeLlo123!!"));
//		int[] arData = {1,2,3,4,5};
//		System.out.println("최대값 : " + mt.getMaxAndMin(arData)[0]);
//		System.out.println("최소값 : " + mt.getMaxAndMin(arData)[1]);
//		System.out.println(mt.getCount("hello", 'l'));
		
//		int[] arData = {1,2,3,4,5};
//		System.out.println(mt.getValue(arData, 0));
		
		System.out.println(mt.indexOf("hello", 'o'));
	}
}



















