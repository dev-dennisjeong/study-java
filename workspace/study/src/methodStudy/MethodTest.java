package methodStudy;

import methodStudy.MethodTest;

//test Ŭ���� ����
public class MethodTest {
	//����Ÿ���� void�̰� �Ű������� data��� int �迭�� �޴� change��� �̸��� �޼ҵ带����
	void change(int[] data) {
		//data �迭 0��° �ε����� 20�̶� ���� ����
		data[0] = 20;
	}
	
	//���� �޼ҵ� ����
	public static void main(String[] args) {
		//int �迭�� data��� ������ 10�̶�� ��ĭ¥�� �迭 ����
		int[] data = {10};
		
//		Test test= new Test();
//		test.change(data);
//		�� ���� �� �Ÿ� �Ʒ�ó�� �����ϰ� ǥ�� ����
		//Test Ŭ������ change �޼ҵ� ���
		new MethodTest().change(data);
		
		//data�� 0��° �ε��� ���� ���
		//10�� 20���� �ٲ���
		System.out.println(data[0]);
	}
}
