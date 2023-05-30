package starbucksTask;

public class Building {
	public static void main(String[] args) {
		Starbucks gangnam = new Starbucks() ;
		Starbucks jamsil = new Starbucks();
		
		gangnam.resgister(new Form() {
			
			@Override
			public void sell(String menu) {
				String[] menus = getMenu();
				for (int i = 0; i < menus.length; i++) {
					if(menus[i].equals(menu)) {
						System.out.println(menu + " 판매완료");
					}
				}
			}
			
			@Override
			public String[] getMenu() {
				return new String[] {"아메리카노", "카페라떼"};			
				}
		
		
		});
		
		jamsil.resgister(new FormAdapter() {
			
			
			@Override
			public String[] getMenu() {
				return new String[] {"물"};			
			}

		});
			
			
			
	
	}
}
