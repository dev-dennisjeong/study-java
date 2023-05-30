package threadTest;

//하나의 데이터를 외부에서 받아서 2개의 객체를 선언한 다음 객체 별로 출력해야할 데이터를
//출력할 수 있게 멀티로 돌린다.

//Thread 클래스 상속(실무에서 잘안씀)
public class Thread1 extends Thread{
	//받아야 할 데이터
	public String data;
	//기본 생성자
	public Thread1() {;}
	//초기화 생성자
	public Thread1(String data) {
						//외부에서 전달받은 이 데이터를 10번 출력
						//이것이 하나의 쓰레드
		super();
		this.data = data;
	}
	
	//Thread 안에 있는 run 메소드 재정의
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {//data를 10번 출력하는 반복문
			System.out.println(data);
			try {sleep(1000);} catch (InterruptedException e) {;}
			//Thread 상속을 받아서 어차피 내 필드에 있는 메소드이기 때문에
			//굳이 Thread.sleep이라고 할 필요없다(부모필드를 그대로 받은거)
		}
	}
}
//Thread1이라는 객체 하나가 하나의 쓰레드가 된다.
//run이라는 메소드가 재정의된 필드를 갖고 있는 객체가 Thread이고,
//Thread1은 Thread의 객체가 되었다.
