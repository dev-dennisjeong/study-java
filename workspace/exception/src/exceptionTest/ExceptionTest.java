package exceptionTest;

public class ExceptionTest{
	public static void main(String[] args) {
		try {
			System.out.println(10/0);
			System.out.println("연산 성공!");
		} catch(ArithmeticException e) {
			e.printStackTrace(); //-> 배포 전 검증하는 단계
			//몇번째 줄이 잘못됐는지 알려줌
			System.out.println(e.getMessage());
			System.out.println(e); //위랑 같은거 문자열로 간단하게 출력
			System.out.println("0으로 나눌 수 없습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
