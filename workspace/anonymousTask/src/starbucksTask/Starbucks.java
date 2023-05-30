package starbucksTask;


// 스타벅스 본사
public class Starbucks {
//	지점들이 처음에 한 번 등록할 때
//	본사에서 정해놓은 양식을 작성하여 form으로 전달한다.
	public void resgister(Form form) {
//		지점에서 메뉴판 검사
		String[] menu = form.getMenu();
		for (int i = 0; i < menu.length; i++) {
			System.out.println(menu[i]);
		}
		
//		만약 무료나눔매장일 경우에는 무료나눔매장입니다
		if(form instanceof FormAdapter) {
			System.out.println("무료나눔매장입니다.");
			return;
		}
		
		form.sell("아메리카노");
	}
}
