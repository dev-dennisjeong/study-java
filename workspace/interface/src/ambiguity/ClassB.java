package ambiguity;

public class ClassB extends ClassA implements InterA /* ,InterB */{
	
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		super.printData();
	}
	
	/*
	 * @Override public void printData() { // TODO Auto-generated method stub
	 * InterA.super.printData(); }
	 */
	
	public static void main(String[] args) {
		new ClassB().printData();
	}
}
