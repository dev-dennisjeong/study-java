package inheritanceStudy;

//���ڽð�� ����Ʈ�ð� Ŭ���� ����
//���ڽð��� ����� �ð�����, �˶������ �ִ�.
//����Ʈ�ð�� ���� �� Ȯ��, �ɹڼ� Ȯ�� ����� �ִ�.
//����Ʈ �ð�� �������� �˶��� Ű�� �� �� �ִ�.

class ElectronicWatch{
	String brand;
	int price;
	String color;
	
	public ElectronicWatch() {;}

	public ElectronicWatch(String brand, int price, String color) {
		super();
		this.brand = brand;
		this.price = price;
		this.color = color;
	}
	
	void checkTime() {
		System.out.println("�ð� Ȯ��");
	}
	
	void turnAlarmOn() {
		System.out.println("�˶� �ѱ�");
	}
	
	void turnAlarmOff() {
		System.out.println("�˶� ����");
	}
}	
class SmartWatch	 extends ElectronicWatch{
	int version;
	
	public SmartWatch(String brand, int price, String color, int version) {
		super();
		this.version = version;
	}

	@Override
	void turnAlarmOn() {
		System.out.println("�������� �˶� �ѱ�");
		super.turnAlarmOn();
	}

	@Override
	void turnAlarmOff() {
		System.out.println("�������� �˶� ����");
	}
	
	void checkWalk() {
		System.out.println("���� �� Ȯ��");
	}
	
	void checkBPM() {
		System.out.println("�ɹڼ� Ȯ��");
	}
}
	
public class InheritacneTask {
	public static void main(String[] args) {
		SmartWatch galaxyWatch = new SmartWatch("galaxy", 300000, "black", 4);
		galaxyWatch.checkTime();
		galaxyWatch.turnAlarmOn();
		galaxyWatch.turnAlarmOff();
		galaxyWatch.checkWalk();
		galaxyWatch.checkBPM();
	}
}
