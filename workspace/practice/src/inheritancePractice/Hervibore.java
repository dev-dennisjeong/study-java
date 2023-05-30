package inheritancePractice;

public class Hervibore extends Animal{

	@Override
	void eat() {
		System.out.print("풀을 ");
		super.eat();
	}

	@Override
	void sleep() {
		System.out.print("서서 ");
		super.sleep();
	}
	
	
	
	
}
