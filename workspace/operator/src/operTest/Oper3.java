package operTest;

import java.util.Scanner;

public class Oper3 {
	public static void main(String[] args) {
//		사용자에게 키를 입력 받고
//		정수라면 정수로 출력
//		실수라면 실수로 출력
//		예) 183 -> 183 출력
//		183.5 -> 183.5
//		삼항 연산자를 사용!
//		뺼샘으로 풀면 쉬움. 서식문자로 풀면 더 쉬움
		
		double height = 0.0;
		String message = "키를 입력하시오 : ";
		Scanner sc = new Scanner(System.in);
		boolean check = false;
		String format = null;
		String forLength = "";
		int length = 0;
	
		System.out.print(message);
		height = sc.nextDouble();
		check = height - (int)height == 0;
		forLength = height - (int)height + "";
		
		length = forLength.length() > 6? 6 : forLength.length();
		
		format = check ? "%.0fcm" : "%." + (length - 2) + "fcm";
		System.out.printf(format, height);
		
	}
}




