package classPractice;

class Animal {
	String type;
	int age;
	
	public Animal() {;}

	public Animal(String type, int age) {
		this.type = type;
		this.age = age;
	}

	void eat() {
		System.out.println(this.type + "(이)가 먹는다.");
	}
	
	void sleep() {
		System.out.println(this.type + "(이)가 잠을 잔다.");
	}
	
	void move() {
		System.out.println(this.type + "(이)가 달린다.");
	}
}

public class ClassStudy2 {
	public static void main(String[] args) {
		Animal tiger = new Animal("호랑이", 5);
		tiger.eat();
		Animal eagle = new Animal("독수리", 3);
		eagle.eat();
		eagle.move();
	}
}
