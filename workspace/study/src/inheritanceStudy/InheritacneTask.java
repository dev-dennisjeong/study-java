package inheritanceStudy;

//전자시계와 스마트시계 클래스 생성
//전자시계의 기능은 시간보기, 알람기능이 있다.
//스마트시계는 걸음 수 확인, 심박수 확인 기능이 있다.
//스마트 시계는 음성으로 알람을 키고 끌 수 있다.

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
		System.out.println("시간 확인");
	}
	
	void turnAlarmOn() {
		System.out.println("알람 켜기");
	}
	
	void turnAlarmOff() {
		System.out.println("알람 끄기");
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
		System.out.println("음성으로 알람 켜기");
		super.turnAlarmOn();
	}

	@Override
	void turnAlarmOff() {
		System.out.println("음성으로 알람 끄기");
	}
	
	void checkWalk() {
		System.out.println("걸음 수 확인");
	}
	
	void checkBPM() {
		System.out.println("심박수 확인");
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
