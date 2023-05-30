package practice;

import java.util.Scanner;

public class ArPractice2 {
	public static void main(String[] args) {
//	      각 버튼을 눌렀을 때, 이동하는 페이지는 다음과 같다.
	      
//	      btn1 : 회원가입 페이지
//	      btn2 : 로그인 페이지
//	      btn3 : 마이 페이지
	      
//	      회원가입 -> 로그인 -> 마이 페이지
//	      위 순서대로 입력해야 하며, 만약 이전 단계를 진행하기 전에 다음 단계 버튼을 누를 경우, 이전 단계에 대한 메세지를 출력해준다.
//	      예) 로그인 클릭 시, "회원가입을 먼저 진행해주세요" 출력
	      
//	      알맞은 단계의 버튼을 누르면, 해당 단계에 대한 메세지를 출력해준다.
//	      예)   회원가입 클릭 시, 회원가입 성공
//	         마이 페이지 클릭 시, 본인 이름과 나이 출력
		
		
		Scanner sc = new Scanner(System.in);
		int[] arData = {1, 2, 3};
		String message = "1. 회원가입 2. 로그인 3. 마이페이지 이동\n선택 : ";
		String[] arMessage = {"회원가입 성공", "로그인 성공", "마이페이지"};
		String[] errorMessages = {"회원가입 먼저 하세요.", "로그인 먼저 하세요."};
		String errorMessage = "잘못 입력하셨습니다.";
		int choice = 0, temp = 0;
		boolean check = false;
		
		
		while(true) { //반복을 돌린다
			for (int i = 0; i < arMessage.length; i++) {
				System.out.print(arData[i] + " ");
			}// 1 2 3 버튼을 출력
			System.out.println();
			temp = temp == 0 ? 0 : choice;
//			temp라는 임시 변수를 만든다.
//			temp가 0이면 0을 담고, temp가 0이 아니라면 choice의 값을 담는다.
//			
			choice = sc.nextInt();
			
			check = choice - 1 == temp;
//			choice - 1이 temp값이라면 check는 true
			
			
			if(!check) {//check가 false라면 -> choice가 1이 아니라면 -> 회원가입을 먼저 누르지 않고 다음 버튼을 눌렀다면
				temp = 0; //temp에 0을 담는다 -> temp값을 초기화
				System.out.println(errorMessages[choice-2]); 
				//choice-2번째 배열을 출력한다. -> choice가 2라면 회원가입 먼저하라는 메시지,
				//choice가 3이라면 로그인을 먼저하라는 메시지를 추력
				continue; //즉시 다음 반복으로 넘어간다 -> 처음부터 다시
			}
			if(choice == 3) { //choice가 3이라면
				temp = 0; //temp에 0을 담고
				continue; //즉시 다음 반복으로 넘어간다 -> 처음부터 다시
			}
			System.out.println(arMessage[choice-1]); 
			//choice-1번째 배열을 출력한다 -> choice가 1이라면 회원가입 성공이라는 메시지를 출력한다.
			temp = choice; //temp에 choice값을 담는다. -> 그래야 choice가 2일때 에러메시지가 안뜸
			//1 2 3을 순서대로 누른다고 가정했을때,
			//을 입력하고 temp값에 choice를 저장하지 않는다면,
			//그 다음에 2를 입력하면 위에 check 값에서 2 - 1 == 0 이 false가 되므로 에러메시지를 출력한다.
			//따라서 choice값을 순서대로 temp에 담아줘야한다.
			
		}
	}
}












