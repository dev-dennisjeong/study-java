package classStudy;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		//Random Ŭ����
		//�������� ���ڸ� �̾��ش�.
		Random random = new Random();
		//0~6������ ���� �� 10�� ���ڸ� �������� ����Ѵ�.
		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt(6));
		}
	}
}
