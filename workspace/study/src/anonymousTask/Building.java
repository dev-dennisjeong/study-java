package anonymousTask;

public class Building {
	public static void main(String[] args) {
		Vips gangnam = new Vips();
		Vips jamsil = new Vips();
		
//		Form �������̽��� �ʵ尡 �������� �ʾұ� ������,
//		�͸� Ŭ������ ���� Form�������̽��� �����ϸ� ������Ų��.
//		������ �͸� Ŭ������ �ʵ尡 �޸𸮿� �Ҵ�� �� �������̽� Ÿ����
//		��ü�� up casting�ȴ�.
//		��ȸ���̴�.
		gangnam.register(new Form() {

			@Override
			public String[] getMenu() {
				return new String[] { "�������", "������ũ" };
			}

			@Override
			public void sell(String menu) {
				String[] menus = getMenu();
				for (int i = 0; i < menus.length; i++) {
					if (menus[i].equals(menu)) {
						System.out.println(menu + " �ǸſϷ�");
					}
				}
			}
		});

//		������� �ǸŹ���� ���� ���� ���ᳪ����� ���̴�.
//		�׷��� FormAdapter�� �����ϸ� ������Ų��.
		jamsil.register(new FormAdapter() {

			@Override
			public String[] getMenu() {
				return new String[] { "��" };
			}
		});
	}
}
