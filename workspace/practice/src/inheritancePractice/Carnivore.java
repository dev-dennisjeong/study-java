package inheritancePractice;

public class Carnivore extends Animal{

	@Override
	void eat() {
		System.out.print("고기를 ");
		super.eat();
	}

	@Override
	void sleep() {
		System.out.print("누워서 ");
		super.sleep();
	}
	
	
	
}
