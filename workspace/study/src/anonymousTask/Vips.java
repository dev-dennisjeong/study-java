package anonymousTask;

//���� ����
public class Vips {
//	�������� ó���� �� �� ����� ��
//	���翡�� ���س��� ����� �ۼ��Ͽ� form���� �����Ѵ�.
	public void register(Form form) {
//		�������� �޴��� �˻�
		String[] menus = form.getMenu();
		for (int i = 0; i < menus.length; i++) {
			System.out.println(menus[i]);
		}
		
//		���ᳪ������ �˻�
		if(form instanceof FormAdapter) {
			System.out.println("���� ���� ���");
			return;
		}
		form.sell("�������");
	}
}
