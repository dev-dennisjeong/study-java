package practice;

import java.util.Scanner;

public class InputPractice {
	public static void main(String[] args) {
//		두 정수를 입력한 뒤 덧셈 출력
//		단, next()만 사용
//		int firstNumber = 0, secondNumber = 0;
//		String firstMsg = "첫번째 정수 입력 : ";
//		String secondMsg = "두번째 정수 입력 : ";
//		int result = 0;
//		String resultMsg = null;
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.print(firstMsg);
//		firstNumber = Integer.parseInt(sc.next()); // sc,next()를 값으로 볼 줄 알아야 한다!
//		System.out.print(secondMsg);
//		secondNumber = Integer.parseInt(sc.next());
//		
//		result = firstNumber + secondNumber;
//		resultMsg = "두 정수의 합은 " + result + " 입니다.";
//		System.out.println(resultMsg);
		
//		3개의 정수를 한 번에 입력받는 후
//		세 정수의 곱셈 출력
//		next()만 사용.
		
		int firstNumber = 0, secondNumber = 0, thirdNumber = 0;		
		String message = "정수를 입력하세요 : ";
		String format = "%d * %d * %d = %d";
//		int multiple = 0;
		
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		firstNumber = Integer.parseInt(sc.next());
		secondNumber = Integer.parseInt(sc.next());
		thirdNumber = Integer.parseInt(sc.next());
		
		int multiple = firstNumber * secondNumber * thirdNumber;
		
		System.out.printf(format, firstNumber, secondNumber, thirdNumber, multiple);
	}
}













