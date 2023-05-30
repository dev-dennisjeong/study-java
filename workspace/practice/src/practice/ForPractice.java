package practice;

import java.util.Scanner;

public class ForPractice {
	public static void main(String[] args) {
//		브론즈
//		1~100까지 출력
//		int number = 0;
//		for (int i = 0; i < 100; i++) {
//			number = i +1 ;
//			System.out.println(number);
//		}
		
//		100~1까지 출력
//		int number = 0;
//		for (int i = 0; i < 100; i++) {
//			number = 100 - i;
//			System.out.println(number);
//		}
		
//		1~100까지 중 짝수만 출력
//		int number = 0;
//		for (int i = 0; i < 50; i++) {
//			number =  (i + 1)*2;
//			System.out.println(number);	
//		}
		
//		실버
//		1~10까지 합 출력
//		int total = 0;
//		for (int i = 0; i < 10; i++) {
//			total += i + 1;
//		}
//		System.out.println(total);
		
//		1~n까지 합 출력
//		int total = 0;
//		int number = 0;
//		String message = "숫자 입력 : ";
//		Scanner sc = new Scanner(System.in);
//		System.out.print(message);
//		number = sc.nextInt();
//		for (int i = 0; i < number; i++) {
//			total += i + 1;
//		}
//		System.out.println(total);
		
//		골드
//		A~F까지 출력
//		for (int i = 0; i < 6; i++) {
//			char c = (char)(i+65);
//			System.out.println(c);
//		}
		
		//		A~F까지 중 C 제외하고 출력
//		for (int i = 0; i < 5; i++) {
//			char c = i > 1 ? (char)(i+66) : (char)(i+65);
//			System.out.println(c);
//		}
		
		
		
		
//		다이아
//		0 1 2 3 0 1 2 3 0 1 2 3 출력
//		int number = 0;
//		for (int i = 0; i < 12; i++) {
//			number = i % 4;
//			System.out.println(number);
//		}
//		aBcDeFgH...Z 출력
		char c = ' ';
		for (int i = 0; i < 26; i++) {
			c = i % 2 == 1 ? (char)(i+65) : (char)(i+97);
			System.out.println(c);
		}
	}
}












