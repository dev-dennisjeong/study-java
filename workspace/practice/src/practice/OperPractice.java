package practice;

import java.util.Scanner;

public class OperPractice {
	public static void main(String[] args) {
//		사용자에게 키를 입력 받고
//		정수라면 정수로 출력
//		실수라면 실수로 출력
//		예) 183 -> 183 출력
//		183.5 -> 183.5
//		삼항 연산자를 사용!
//		뺼샘으로 풀면 쉬움. 서식문자로 풀면 더 쉬움
		
//		String message = "키를 입력 : ";
//		double height = 0.0;
//		double result = 0.0;
//		String format = "";
//		boolean check = false;
//		String forLength = "";
//		int length = 0;
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println(message);
//		height = sc.nextDouble();
//		check = height - (int)height == 0;
//		forLength = height - (int)height + "";
//		length = forLength.length() > 4 ? 4 : forLength.length();
//		format = check ? "%.0fcm" : "%." + (length -2) + "fcm";
//		System.out.printf(format, height);
		
//      심리 테스트
      /*
       * Q. 당신이 좋아하는 색을 선택하세요.
       * 1. 빨간색
       * 2. 노란색
       * 3. 검은색
       * 4. 흰색
       * 
       * 빨간색 : 불같고 열정적이고 적극적이다.
       * 노란색 : 발랄하고 밝고 귀엽고 개성있고 착하다.
       * 검은색 : 묵묵하고 든든하고 냉철하고 멋지다.
       * 흰색 : 천사같고 깔끔하고 정리를 좋아하고 배려심이 많다.
       * 
       */
		
		
		String message = "당신이 좋아하는 색을 선택하세요.\n1.빨간색\n2.노란색\n3.검은색\n4.흰색\n선택 : ";
		int choice = 0;
		String red = "빨간색 : 불같고 열정적이고 적극적이다.";
		String yellow = "노란색 : 발랄하고 밝고 귀엽고 개성있고 착하다.";
		String black = "검은색 : 묵묵하고 든든하고 냉철하고 멋지다.";
		String white = "흰색 : 천사같고 깔끔하고 정리를 좋아하고 배려심이 많다..";
		String errorMessage = "잘못 입력하셨습니다.";
		String result = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print(message);
		choice = sc.nextInt();
		
		result = choice == 1 ? red :
			choice == 2 ? yellow :
				choice == 3 ? black :
					choice == 4 ? white :
						errorMessage;
		
		System.out.println(result);
	}
}


















