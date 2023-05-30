package inheritancePractice;

public class InheritancePractice {
	
//	if(video instanceof Animation) {
//		Animation animation = (Animation) video;
//		animation.getSubtitle();
//	}else if(video instanceof Film) {
//		Film film = (Film) video;
//		film.moveChair();
//	}else if(video instanceof Drama) {
//		Drama drama = (Drama) video;
//		drama.sellGoods();
//	}
	public void checkAnimal(Animal animal){
		if(animal instanceof Carnivore) {
			Carnivore tiger = (Carnivore) animal;
			tiger.eat();
		}else if(animal instanceof Hervibore) {
			Hervibore rabbit = (Hervibore) animal;
			rabbit.eat();
		}
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Animal[] animals = {
				new Carnivore(),
				new Hervibore()	
		};
		InheritancePractice ip = new InheritancePractice();
		
		for (int i = 0; i < animals.length; i++) {
			ip.checkAnimal(animals[i]);
		}
		
//		Hervibore rabbit = new Hervibore();
//		rabbit.eat();
//		rabbit.sleep();
//		
//		Carnivore tiger = new Carnivore();
//		tiger.eat();
//		tiger.sleep();
	}
}










