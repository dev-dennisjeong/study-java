package classStudy;

import classStudy.A;

class A{
	int number;
	
	public A(int number) {
		this.number = number;
	}
	
	void printNumber(int number) {
		System.out.println("매개변수 number >> " + number); //10
		System.out.println("객체가 저장한 number >>" + this.number); //5
		System.out.println("참조값 >> " + this); //A의 주소값
	}
}

public class ClassStudy {
	public static void main(String[] args) {
		A a = new A(5);
		
		a.printNumber(10);
		System.out.println(a); //A의 주소값
	}
}
