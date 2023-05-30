package samsungTask;

public class Building {
	public static void main(String[] args) {
		Samsung gangnam =  new Samsung();
		Samsung jamsil = new Samsung();
		gangnam.register(new Form() {

			@Override
			public String[] getService() {
				return new String[] {"액정 수리", "배터리 교체", "리퍼"};		
			}

			@Override
			public void paidService(String service) {
				String[] services = getService();
				for (int i = 0; i < services.length; i++) {
					if(services[i].equals(service)) {
						System.out.println(service + " 유료 서비스 완료");
					}
				}
			}	
		}
		);
		
		jamsil.register(new FormAdapter() {
			
			@Override
			public String[] getService() {
				return new String[] {"필름교체", "공장 초기화", "바이러스 검사"};
					
			}
		});
		
		
	}

}






