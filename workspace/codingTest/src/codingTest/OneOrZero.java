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
		
		System.out.println("0과 1을 입력하시오.\n예)110111");
		s = sc.next();
		String[] temp = s.split("0"); 
		//s를 0을 기준으로 나눠 temp 문자열 배열에 담는다
		int maxOne = 0;
		//1이 몇 번 연속됐는지 구한다 -> 1의 길이를 구한다이므로
		//1의 길이을 받을 maxOne변수를 int 타입으로 선언
		
		try {maxOne = temp[0].length();} 
		catch (ArrayIndexOutOfBoundsException e) {;}
		//길이 비교를 위해 temp의 0번째 문자열의 길이를 담아놓는다
		//문자열에 0 밖에 없어서 문자열의 길이를 담을 수 없는 경우가 있으므로 예외처리를 해준다		
		
		for (int i = 0; i < temp.length; i++) {
//			System.out.println(i + temp[i]);
			if(maxOne < temp[i].length()) {maxOne = temp[i].length();}
			//temp[0]의 길이보다 긴 문자열이 나온다면 그 문자열의 길이를 담아준다
			result = maxOne;
		}		
		
		System.out.println(result);
		//1이 최대로 나온 횟수를 출력한다
		
		
//		String s = "00000";
//	    System.out.println(new OneOrZero().solution(s));

		
	}
}
