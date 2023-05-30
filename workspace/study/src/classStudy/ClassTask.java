package classStudy;

import java.util.Scanner;

//문을 여는 클래스 만들기
class Door{
	String type;
	String password;
	boolean battery;
//비밀번호 오류 횟수
	int wrongCount;
	boolean power;
	

//문 초기 비밀번호 세팅
	{
		password = "0000";
	}
//생성자
	public Door() {;}
	

//원하는 비밀번호로 세팅 가능
	public Door(String type, String password, boolean battery) {
		this.type = type;
		this.password = password;
		this.battery = battery;
	}
	

//문 열기
//배터리를 확인하고
//배터리가 있고, 문이 닫혀있다면"비밀번호를 입력하세요 : " 출력
//비밀번호가 일치한다면 "문이 열렸습니다." 출력
//비밀번호가 일치하지않는다면 "비밀번호가 틀렸습니다." 출력
//배터리가 있고, 문이 열려있다면 "이미 문이 열려있습니다." 출력
//배터리가 없다면, "배터리가 없습니다." 출력
	void open() {
		power = true;
	}

//문 잠그기
//배터리를 확인하고
//배터리가 있다면, "문이 잠겼습니다." 출력
//배터리가 있고, 문이 잠겨있다면 "이미 문이 잠겨있습니다." 출력
//배터리가 없다면, "배터리가 없습니다." 출력
	void close() {
		power = true;
	}

//문을 열기 위해서 비밀번호 4자리를 입력받아야 한다.
//3번 연속 잘못 입력했을 시 "사이렌" 출력
//※ 문자열 비교 ==이 아닌 equals()로 비교한다.
//"문자열".equals("문자열");
	boolean inputPassword(String password) {
		return this.password.equals(password);
	}
	
}

public class ClassTask {
	public static void main(String[] args) {
		String message = "1. 문 열기 2. 문 잠그기";
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		Door home = new Door("doorLock", "1515", true);
		
//		원하는 부분에 표시할 수 있는 변수를 flag라고 한다.
		boolean flag = false;
		
		if(home.battery == false) {
			System.out.println("배터리가 없습니다.");
		}
		
		while(home.battery) {
			System.out.println(message);
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				//문이 닫혀 있을 때
				if(!home.power) {
					//비밀번호를 입력하고 검사한다.
						System.out.println("비밀번호를 입력하세요.");
						if(home.inputPassword(sc.next())) {
							//비밀번호가 일치하면 문을 연다.
							home.open();
							System.out.println("문이 열렸습니다.");
							//틀린 횟수 초기화(비밀번호를 틀리고 문을 열었을 경우)
							home.wrongCount = 0;
						}else {
							System.out.println("비빌번호를 틀렸습니다.");
							//비밀번호를 틀리면 틀린 횟수를 추가한다.
							home.wrongCount++;
							//3회 이상 틀리면 사이렌이 울린다.
							if(home.wrongCount>2) {
								System.out.println("사이렌");
								//나갈 준비
								flag = true;
							}
							System.out.println("비밀번호 " + home.wrongCount + "회 오류");
						}
						break;
				}
				System.out.println("이미 문이 열려있습니다.");
				break;
			case 2:
				//문이 열려있을 때
				if(home.power) {
					//문을 닫는다.
					home.close();
					System.out.println("문이 잠겼습니다.");
					//나갈 준비
					flag = true;
					break;
				}
				System.out.println("이미 문이 잠겨있습니다.");
				break;
			default : 
				break;
			}
			//flag가 true일 때 while문 탈출
			if(flag == true) {break;}
		
		}
		
	}
}













