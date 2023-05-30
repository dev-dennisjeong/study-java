package arrayListTask;

import java.util.ArrayList;
import java.util.Collections;

public class DateApp {
	public static ArrayList<Love> loves = DBConnecter.loves;
	
	//����ڰ� ���ϴ� ������ �̻��� ��� ��ȸ
	public ArrayList<Love> findLove(int age) {
		ArrayList<Love> result = new ArrayList<Love>();
		for (Love love : loves) {
			if(love.getAge() == age) {
				result.add(love);
			}
		}
		return result;
	}
	
	//�̻����� ���� ����
	public void changeAge(Love love) {
		for (Love loveInDataBase : loves) {
			if(loveInDataBase.getAge() == love.getAge()) {
				loveInDataBase.setAge(love.getAge());				
			}
		}
	}
	
	//�̻��� ���� �� ����
	public  ArrayList<Love> ageSort() {
		ArrayList<Love> results = new ArrayList<Love>();
		ArrayList<Integer> ages = new ArrayList<Integer>();
		Love love = null;
		
//		loves���� ���̸� �����ͼ� ages�� �߰�
		for (Love loveInDB : loves) {
			ages.add(love.getAge());
		}
		
//		������������ ����
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







