package classPractice;

class B{
	int number;
	
	public B(int number) {
		this.number = number;
	}
	
	void printNumber(int number) {
		System.out.println("매개변수 number >> " + number);
		System.out.println("객체가 저장한 number >>" + this.number);
		System.out.println("참조값 >> " + this);
	}
}

public class ClassStudy {
	public static void main(String[] args) {
		B b = new B(5);
		
		b.printNumber(10);
		System.out.println(b);
	}
}
