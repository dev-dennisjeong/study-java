package methodTest;

//test 클래스 선언
public class Test {
	//리턴타입이 void이고 매개변수를 data라는 int 배열로 받는 change라는 이름의 메소드를선언
	void change(int[] data) {
		//data 배열 0번째 인덱스에 20이란 값을 대입
		data[0] = 20;
	}
	
	//메인 메소드 선언
	public static void main(String[] args) {
		//int 배열의 data라는 변수에 10이라는 한칸짜리 배열 선언
		int[] data = {10};
		
//		Test test= new Test();
//		test.change(data);
//		한 번만 쓸 거면 아래처럼 간단하게 표현 가능
		//Test 클래스의 change 메소드 사용
		new Test().change(data);
		
		//data에 0번째 인덱스 값을 출력
		//10을 20으로 바꿔줌
		System.out.println(data[0]);
	}
}
