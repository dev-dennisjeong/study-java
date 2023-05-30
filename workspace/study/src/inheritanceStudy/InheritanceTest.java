package inheritanceStudy;

class Phone{
	void sendMessage() {
		System.out.println("문자메시지 전송");
	}
	
	
	void call() {
		System.out.println("전화걸기");
	}
	
	void connectData() {
		System.out.println("데이터 켜기");
	}
}

class SmartPhone extends Phone{

	@Override
	void connectData() {
		super.connectData();
		System.out.println("와이파이 연결하기");
	}
}

public class InheritanceTest {
	public static void main(String[] args) {
		SmartPhone galaxy = new SmartPhone();
		galaxy.sendMessage();
		galaxy.call();
		galaxy.connectData();
	}
}
