package inheritanceStudy;

class Phone{
	void sendMessage() {
		System.out.println("���ڸ޽��� ����");
	}
	
	
	void call() {
		System.out.println("��ȭ�ɱ�");
	}
	
	void connectData() {
		System.out.println("������ �ѱ�");
	}
}

class SmartPhone extends Phone{

	@Override
	void connectData() {
		super.connectData();
		System.out.println("�������� �����ϱ�");
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
