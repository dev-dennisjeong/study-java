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
		//���� ���� �ݾ��� ���ݿ��� �������� ������ ��
		result =	(int)(cafe.price - (cafe.price * (discount / 100.0)));
		return result;
	}
	
}
