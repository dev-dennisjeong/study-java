package classStudy;

import java.util.Scanner;

public class CafeMain {
	public static void main(String[] args) {
		Customer customer = new Customer("홍길동", 10000, 20); //고객의 정보
		Cafe[] cafe = { //카페에 메뉴와 가격을 담는다.
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
		result = customer.buyCoffee(cafe[choice-1]); //1번이 0번째 인덱스이므로 1을 빼준다
		System.out.print("주문하신 메뉴 : ");
		System.out.println(cafe[choice-1].menu); //메뉴를 가져온다.
		System.out.print("가격 : ");
		System.out.println(cafe[choice-1].price); //가격을 가져온다.
		System.out.print("지불한 금액 : ");
		System.out.println(customer.money); //고객의 돈
		System.out.print("할인율 : ");
		System.out.println(customer.discount + "%"); //고객의 할인율
		System.out.print("최종결제금액 : ");
		System.out.println(result); //할인된 최종 가격
		System.out.print("잔돈 : ");
		System.out.println(customer.money - result); //고객이 지불한 금액에서 결제 금액을 빼준다.
	}
}
