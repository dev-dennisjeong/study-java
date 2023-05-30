package classStudy;

public class Customer {
	String name;
	int money;
	int discount;
	
	public Customer() {;}

	public Customer(String name, int money, int discount) {
		this.name = name;
		this.money = money;
		this.discount = discount;
	}
	
	int buyCoffee(Cafe cafe) {
		int result = 0;
		//최종 결제 금액은 가격에서 할인율을 곱해준 값
		result =	(int)(cafe.price - (cafe.price * (discount / 100.0)));
		return result;
	}
	
}
