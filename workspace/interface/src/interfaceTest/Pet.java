package interfaceTest;

public interface Pet {
	//Pet이라는 인터페이스를 만든다
	public final static int EYES= 2; // 타입 앞에 final static 생략되어있음
	// static -> 모든 객체가 공유하기 위해서 붙인다
	public int NOSE = 1;
	
	//{}바디 만들지 않고 선언
	public abstract void giveHand(); //접근자 뒤에 abstract 생략되어있음
	public void bang();
	public void sitDown();
	public void wailtNow();
	public void getNose();
}
