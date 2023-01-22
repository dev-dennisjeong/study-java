package classStudy;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		//Random 클래스
		//무작위의 숫자를 뽑아준다.
		Random random = new Random();
		//0~6까지의 숫자 중 10개 숫자를 랜덤으로 출력한다.
		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt(6));
		}
	}
}
