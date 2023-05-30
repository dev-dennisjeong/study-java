package nikeTask;


public class Building {
	public static void main(String[] args) {
		Nike gangnam = new Nike() ;
		Nike jamsil = new Nike();
		
		gangnam.resgister(new FormAdapter(){
			
			@Override
			public String[] getCollection() {
				return new String[] {"티"};			
				}
		
		});
		
		jamsil.resgister(new Form() {
			
			@Override
			public void sell(String collection) {
				String[] collections = getCollection();
				for (int i = 0; i < collections.length; i++) {
					if(collections[i].equals(collection)) {
						System.out.println(collection + " 판매완료");
					}
				}
			}
			
			@Override
			public String[] getCollection() {
				return new String[] {"신발", "츄리닝"};			
				}

		});
			
			
			
	
	}
}
