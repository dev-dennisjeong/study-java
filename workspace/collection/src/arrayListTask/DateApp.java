package arrayListTask;

import java.util.ArrayList;
import java.util.Collections;

public class DateApp {
	public static ArrayList<Love> loves = DBConnecter.loves;
	
	//사용자가 원하는 나이의 이상형 목록 조회
	public ArrayList<Love> findLove(int age) {
		ArrayList<Love> result = new ArrayList<Love>();
		for (Love love : loves) {
			if(love.getAge() == age) {
				result.add(love);
			}
		}
		return result;
	}
	
	//이상형의 나이 수정
	public void changeAge(Love love) {
		for (Love loveInDataBase : loves) {
			if(loveInDataBase.getAge() == love.getAge()) {
				loveInDataBase.setAge(love.getAge());				
			}
		}
	}
	
	//이상형 나이 순 정렬
	public  ArrayList<Love> ageSort() {
		ArrayList<Love> results = new ArrayList<Love>();
		ArrayList<Integer> ages = new ArrayList<Integer>();
		Love love = null;
		
//		loves에서 나이를 가져와서 ages에 추가
		for (Love loveInDB : loves) {
			ages.add(love.getAge());
		}
		
//		오름차순으로 정렬
		Collections.sort(ages);
		
		for (Integer integer : ages) {
			for(Love loveInDB : loves) {
				if(loveInDB.getAge() == integer) {
					results.add(love);
				}
			}
			loves.remove(love);
		} 
		return results;	
	}
	
	
	
}







