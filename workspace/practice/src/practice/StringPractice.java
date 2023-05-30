package practice;

import java.util.Scanner;

public class StringPractice {
	public static void main(String[] args) {
//      사용자에게 입력받은 문자열 중 소문자는 모두 대문자로,
//      대문자는 모두 소문자로 변경한다.
//		Scanner sc = new Scanner(System.in);
//		char c = ' ';
//		
//		System.out.println("문자열 입력");
//		String str = sc.next();
//		char[] arData = new char[str.length()];
//		
//		for (int i = 0; i < arData.length; i++) {
//			c = str.charAt(i);
//			c = c >= (char)(65) && c<=(char)(90) ? (char)(c+32) :
//				c >= (char)(97) && c <= (char)(122) ? (char)(c-32) :
//					c;
//			System.out.print(c);
//		}
		
		
//      정수를 한글로 변경
//      예) 1024 -> 일공이사
		Scanner sc = new Scanner(System.in);
		String hangle = "공일이삼사오육칠팔구";
		String result = "";
		String str = null;
		
		System.out.println("정수입력");
		str = sc.next();
		for (int i = 0; i < str.length(); i++) {
			result += hangle.charAt(str.charAt(i) - 48);
			 
		}
		System.out.println(result);
		
		
	}
}












