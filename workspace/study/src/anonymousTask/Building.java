package anonymousTask;

public class Building {
	public static void main(String[] args) {
		Vips gangnam = new Vips();
		Vips jamsil = new Vips();
		
//		Form 인터페이스에 필드가 구현되지 않았기 때문에,
//		익명 클래스를 열고 Form인터페이스를 지정하며 구현시킨다.
//		구현된 익명 클래스의 필드가 메모리에 할당된 뒤 인터페이스 타입의
//		객체로 up casting된다.
//		일회성이다.
		gangnam.register(new Form() {

			@Override
			public String[] getMenu() {
				return new String[] { "샐러드바", "스테이크" };
			}

			@Override
			public void sell(String menu) {
				String[] menus = getMenu();
				for (int i = 0; i < menus.length; i++) {
					if (menus[i].equals(menu)) {
						System.out.println(menu + " 판매완료");
					}
				}
			}
		});

//		잠실점은 판매방식이 따로 없고 무료나눔행사 중이다.
//		그래서 FormAdapter를 지정하며 구현시킨다.
		jamsil.register(new FormAdapter() {

			@Override
			public String[] getMenu() {
				return new String[] { "빵" };
			}
		});
	}
}
