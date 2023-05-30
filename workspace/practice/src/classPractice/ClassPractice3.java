package classPractice;

import java.util.Scanner;

public class ClassPractice3 {
	public static void main(String[] args) {
		Customer customer = new Customer("홍길동", 10000, 20);
		Cafe[] cafe = {
				new Cafe("아이스아메리카노", 2500),
				new Cafe("카페라뗴", 3000),
				new Cafe("바닐라라떼", 3500),
				new Cafe("아이스초코", 3000)
		};
		Scanner sc = new Scanner(System.in);
		int choice = 0, result = 0;
		
		System.out.println("어떤 메뉴를 구매하시겠습니까?");
		System.out.println("1. 아이스아메리카노 2. 카페라떼 3. 바닐라라떼 4. 아이스초코");
		choice = sc.nextInt();
		result = customer.buyCoffee(cafe[choice-1]);
		System.out.println("주문하신 메뉴");
		System.out.println(cafe[choice-1].menu);
		System.out.println("가격");
		System.out.println(cafe[choice-1].price);
		System.out.println("지불한 금액");
		System.out.println(customer.money);
		System.out.println("할인율");
		System.out.println(customer.discount + "%");
		System.out.println("최종결제금액");
		System.out.println(result);
		System.out.println("잔돈");
		System.out.println(customer.money - result);
	}
}
