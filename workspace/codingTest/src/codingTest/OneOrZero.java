package codingTest;

import java.util.Scanner;

public class OneOrZero {
//	 public int solution(String s) {
//	      int result = 0;
//	      
//	      List<Integer> results = new ArrayList<String>(Arrays.asList(s.split("0")))
//	            .stream().map(String::length).sorted(Collections.reverseOrder())
//	            .collect(Collectors.toList());
//	      try {
//	         result = new ArrayList<String>(Arrays.asList(s.split("0")))
//	               .stream().map(String::length).sorted(Collections.reverseOrder())
//	               .collect(Collectors.toList()).get(0);
//	      } catch (Exception e) {
//	         result = 0;
//	      }
//	      return results.size() == 0 ? 0 : results.get(0);
//	   }

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = null;
		int result = 0;
		
		System.out.println("0�� 1�� �Է��Ͻÿ�.\n��)110111");
		s = sc.next();
		String[] temp = s.split("0"); 
		//s�� 0�� �������� ���� temp ���ڿ� �迭�� ��´�
		int maxOne = 0;
		//1�� �� �� ���ӵƴ��� ���Ѵ� -> 1�� ���̸� ���Ѵ��̹Ƿ�
		//1�� ������ ���� maxOne������ int Ÿ������ ����
		
		try {maxOne = temp[0].length();} 
		catch (ArrayIndexOutOfBoundsException e) {;}
		//���� �񱳸� ���� temp�� 0��° ���ڿ��� ���̸� ��Ƴ��´�
		//���ڿ��� 0 �ۿ� ��� ���ڿ��� ���̸� ���� �� ���� ��찡 �����Ƿ� ����ó���� ���ش�		
		
		for (int i = 0; i < temp.length; i++) {
//			System.out.println(i + temp[i]);
			if(maxOne < temp[i].length()) {maxOne = temp[i].length();}
			//temp[0]�� ���̺��� �� ���ڿ��� ���´ٸ� �� ���ڿ��� ���̸� ����ش�
			result = maxOne;
		}		
		
		System.out.println(result);
		//1�� �ִ�� ���� Ƚ���� ����Ѵ�
		
		
//		String s = "00000";
//	    System.out.println(new OneOrZero().solution(s));

		
	}
}
