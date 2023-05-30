package threadTest;

public class ThreadTest {
	public static void main(String[] args) {
		
		//자원은 하나, 여러 개의 쓰레드
		//자원의 수는 run이라는 메소드가 재정의된 객체의 개수
		//Thread2 thread = new Thread2(); //run이 재정의된 객체 -> Thread2 하나 -> 자원
		//Runnable thread = new Thread2(); 위와 같음
		//부모타입							자식값 -> 업캐스팅
		//thread는 타입이 3개다 Thread2, Runnable, Object
		
		//Thread2처럼 Runnable 인터페이스를 선언하지 않아도
		//이렇게 익명메소드를 열어서 람다식으로 직접 구현할 수 있다
		Runnable target = () -> { // () -> {} 구현된 필드의 주소값
		// ()는 run()인데 이름이 하나 밖에 없으니까 익명메소드로 구현 가능
		// ()->{} ()이 메소드를 {}여기서 구현하는거니까
		// ()이 메소드가 실행이 되면 {}여기 안에 있는 코드가 실행이 된다
		// () -> {} - 할당된(구현된) 필드의 주소값
		//여러 번 쓸 때는 이렇게 해야함
		//한 번만 쓸 때는 Thread2 thread = () -> {}; 이렇게 해도 됨
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName());
				try {Thread.sleep(500);} catch (InterruptedException e) {;}
			}
		};
		
		//Thread를 상속받은 적이 없기 때문에 start 못 씀
		//그래서 Thread 객체를 직접 객체화 해준다
//		Thread t1 = new Thread(target, "?");
//		Thread t2 = new Thread(target, "!");
		//currentThread에 접근한 각각의 이름들이 출력이 된다
		
		//멀티쓰레드로 2개의 자원을 구현
//		t2.start();
		
//		try {t2.join();} catch (InterruptedException e) {;}
		//t2를 먼저 실행시킨다(다른 자원은 t2 끝날 때까지 실행 x)
		//처리 경로가 위에서 아래로이기 때문에 start를 위에 두고 join을 걸면 안걸린다
		//t2.start() t2.join() t1.start() 순으로 해야 t2가 끝날 때까지 t1이 wait 상태	
		
//		t1.start();
		//t2에 join을 걸어놨으므로 t1이 끝나면 t2가 실행된다
		
		//Thread는 메인쓰레드를 우선으로 처리한다
		//그러나 이 우선순위는 내부적인 것이기 때문에 순서가 일정하진 않다
		System.out.println("안녕");
		
		//2개의 쓰레드로 메모리에 할당
		Thread1 t1 = new Thread1("♥");
		Thread1 t2 = new Thread1("★");
		//t1과 t2에는 재정의된 메소드 run이 있다
		//원래 멀티쓰레드는 이렇게 하는게 아님 -> Thread 객체 하나로 멀티쓰레드를 구현해야함
		//그래야만 Thread 객체 하나에 있는 run을 여러 객체가 접근해서 멀티쓰레드를 확실히 구현할 수 있음
		
		//start()는 Thread의 객체
		//Thread에 있는 것을 재정의했기 때문에 t1과 t2 객체가 있는 run이
		//우리가 만든 run으로 start가 된다
		t1.start();
		t2.start();
		//하나하나 같이 실행된다 -> 멀티쓰레드
		//그런데 속도가 너무 빨라서 섞여서 출력된다.
		//이럴땐 Thread.sleep으로 속도를 늦춰서 확인할 수 있다(try-catch 잡아줘야함)
		//♥★ ★♥ ♥★ ★♥ ★♥ -> 그래도 순서가 일정하진 않다
		
//		t1.run();
//		t2.run();
	}
}














