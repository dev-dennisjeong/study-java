package classStudy;

import java.util.Scanner;

public class CafeMain {
	public static void main(String[] args) {
		Customer customer = new Customer("ȫ�浿", 10000, 20); //���� ����
		Cafe[] cafe = { //ī�信 �޴��� ������ ��´�.
				new Cafe("���̽��Ƹ޸�ī��", 2500),
				new Cafe("ī����", 3000),
				new Cafe("�ٴҶ��", 3500),
				new Cafe("���̽�����", 3000)
		};
		Scanner sc = new Scanner(System.in);
		int choice = 0, result = 0;
		
		System.out.println("� �޴��� �����Ͻðڽ��ϱ�?");
		System.out.println("1. ���̽��Ƹ޸�ī�� 2. ī��� 3. �ٴҶ�� 4. ���̽�����");
		choice = sc.nextInt();
		result = customer.buyCoffee(cafe[choice-1]); //1���� 0��° �ε����̹Ƿ� 1�� ���ش�
		System.out.print("�ֹ��Ͻ� �޴� : ");
		System.out.println(cafe[choice-1].menu); //�޴��� �����´�.
		System.out.print("���� : ");
		System.out.println(cafe[choice-1].price); //������ �����´�.
		System.out.print("������ �ݾ� : ");
		System.out.println(customer.money); //���� ��
		System.out.print("������ : ");
		System.out.println(customer.discount + "%"); //���� ������
		System.out.print("���������ݾ� : ");
		System.out.println(result); //���ε� ���� ����
		System.out.print("�ܵ� : ");
		System.out.println(customer.money - result); //���� ������ �ݾ׿��� ���� �ݾ��� ���ش�.
	}
}
