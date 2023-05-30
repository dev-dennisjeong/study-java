package ambiguity;

public interface InterA {
	public default void printData() { //default 붙이면 일반 메소드라는 뜻
		System.out.println("InterA");
	}
}
