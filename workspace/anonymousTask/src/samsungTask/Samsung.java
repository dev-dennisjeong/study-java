package samsungTask;

public class Samsung {
	public void register(Form form) {
		String[] services = form.getService();
		for (int i = 0; i < services.length; i++) {
				System.out.println(services[i]);
			
			}
		if(form instanceof FormAdapter) {
			System.out.println("무료 서비스 제공");
			return;
		}
		form.paidService("액정 수리");
	}
	
}
