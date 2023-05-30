package controllStatement;

public class ForTask {
	public static void main(String[] args) {
//		브론즈
//		1~100까지 출력
//		100~1까지 출력
//		1~100까지 중 짝수만 출력
//		for(int i=0; i<100; i++) {
//			System.out.println(i+1);
//		}
//		for(int i=0; i<100; i++) {
//			System.out.println(100-i);
//		}
//		for(int i=0; i<50; i++) {
//			System.out.println((i+1) * 2);
//		}

//		실버
//		1~10까지 합 출력
//		int total = 0;
//		for(int i=0; i<10; i++) {
//			//total = total + (i+1);
//			total += i + 1;
//		}
//		System.out.println(total);
		
//		1~n까지 합 출력
//		int total2 = 0;
//		int n = 0;
//		Scanner sc = new Scanner(System.in);
//		System.out.print("n 입력: ");
//		n = sc.nextInt();
//		for(int i=0; i<n; i++) {
//			total2 = total2 + (i+1);
//		}
//		System.out.println(total2);
		
		
//		골드
//		A~F까지 출력
//		for(int i=0; i<6; i++) {
//			System.out.println((char)(i+65));
//		}
//		A~F까지 중 C 제외하고 출력
//		for(int i=0; i<6; i++) {
//			System.out.println((char)(i > 1 ? i + 66 : i + 65));
//		}
		
		
//		다이아
//		0 1 2 3 0 1 2 3 0 1 2 3 출력
//		for(int i=0; i<12; i++) {
//			int number = 0;
//			number = i % 4;
//			System.out.println(number);
//		}
//		char check = ' ';
//		check = 'a';
//		System.out.println((int)check);
//		aBcDeFgH...Z 출력
		for(int i=0; i<26; i++) {
			System.out.print((char)(i % 2 == 0 ? i + 97 : i + 65));
		}
		
	}
}
