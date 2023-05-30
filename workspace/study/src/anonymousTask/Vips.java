package anonymousTask;

//빕스 본사
public class Vips {
//	지점들이 처음에 한 번 등록할 때
//	본사에서 정해놓은 양식을 작성하여 form으로 전달한다.
	public void register(Form form) {
//		지점에서 메뉴판 검사
		String[] menus = form.getMenu();
		for (int i = 0; i < menus.length; i++) {
			System.out.println(menus[i]);
		}
		
//		무료나눔매장 검사
		if(form instanceof FormAdapter) {
			System.out.println("무료 나눔 행사");
			return;
		}
		form.sell("샐러드바");
	}
}
