package practice;

import java.util.Scanner;

public class IfPractice {
	public static void main(String[] args) {
//		사용자에게 아래의 메뉴를 출력하고
//		번호를 입력받는다.
		
//		1. 빨간색
//		2. 검은색
//		3. 노란색
//		4. 흰색

//		사용자가가 입력한 번호의 색상을 영어로 출력한다.
		
		String menu = "1. 빨간색\n2. 검은색\n3. 노란색\n4. 흰색\n선택 : ";
		int choice = 0;
		String result = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print(menu);
		choice = sc.nextInt();
		
		if(choice == 1) {
			result = "Red";
		}else if(choice == 2) {
			result = "Black";
		}else if(choice == 3) {
			result = "Yellow";
		}else if(choice == 4) {
			result = "White";
		}else {
			result = "잘못 입력하셨습니다.";
		}
		System.out.println(result);
	}
}















