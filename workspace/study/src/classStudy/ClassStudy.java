package classStudy;

import classStudy.A;

class A{
	int number;
	
	public A(int number) {
		this.number = number;
	}
	
	void printNumber(int number) {
		System.out.println("�Ű����� number >> " + number); //10
		System.out.println("��ü�� ������ number >>" + this.number); //5
		System.out.println("������ >> " + this); //A�� �ּҰ�
	}
}

public class ClassStudy {
	public static void main(String[] args) {
		A a = new A(5);
		
		a.printNumber(10);
		System.out.println(a); //A�� �ּҰ�
	}
}
