package threadTest;

public class Thread2 implements Runnable{ //Runnable 인터페이스 상속
	//강제성이 있기 때문에 반드시 run을 재정의 해야한다
	//Runnable엔 run 밖에 없다. 
	//Runnable은 함수형 인터페이스 -> run 구현할 때 람다 쓸 수 있음
	
	@Override
	public void run() {
		//이 자원에 접근한 객체의 이름을 10번 출력
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName());
			//currentThread -> Thread에서 이 자원에 접근한 static 메소드를 가져올 수 있다
			//Thread.currentThread는 Thread 객체
			//Thread 객체는 각 쓰레드 별로 이름을 줄 수 있음
			//-> Thread에는 이름을 전달하는 생성자가 있다
			//이 생성자는 super()로 접근하면 된다
			//그런데 Runnable은 Thread를 상속받은 적이 없다(Thread의 자식이 아님)
			//이 자원에 접근한 쓰레드 객체의 이름을 getName()으로 가져와야한다
			try {Thread.sleep(500);} catch (InterruptedException e) {;}
			//Thread를 받은 적이 없으므로 Thread.sleep으로 해줘야함
		}
	}
}
