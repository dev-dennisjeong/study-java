package practice;

import java.util.Scanner;

public class WhilePractice {
	public static void main(String[] args) {
		int num = 0;
		String msg = "무한반복";
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("선택 : ");
			num = sc.nextInt();
		switch(num) {
		case 1:
			System.out.println(msg);
			break;
		}
		if(num != 1) {
			break;
		}
		}
	}
}
