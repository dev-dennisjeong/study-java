package ArrayListStudy;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListStudy1 {
	public static void main(String[] args) {
		// Integer 타입의 ArrayList 생성
		ArrayList<Integer> datas = new ArrayList<Integer>();
		final int SIZE = 10;

//  C(생성)
		// 1 11 21 31 41 51 61 71 81 91을 담는다
		// 인덱스가 10개이므로 위에서 상수로 선언
		for (int i = 0; i < SIZE; i++) {
			if (i > 0) {
				datas.add(i * 10 + 1);
				System.out.println(datas.size());
				continue;
			}
			datas.add(i + 1);
			System.out.println(datas.size());
		}

//  R(읽기)
		for (int i = 0; i < SIZE; i++) {
			System.out.println(datas.get(i));
		}

//  빠른 for문(forEach, 향상된 for문)
//  for(자료형 변수명 : 순서가 있는 무언가){}
//  변수에 하나씩 값이 순서대로 들어간다!
		for (int data : datas) {
			System.out.println(data);
		}

//  추가(삽입)
//  50 뒤에 500 삽입
		if (datas.contains(51)) {
			datas.add(datas.indexOf(51) + 1, 501);
		}
		System.out.println(datas);

//  U(수정)
//  90을 9로 수정
		int prev = 0;
		if (datas.contains(91)) {
			prev = datas.set(datas.indexOf(91), 9);
		}
		System.out.println(datas);

//  D(삭제)
//  80 삭제
//	1. 인덱스 번호로 삭제
//		if(datas.contains(81)) {
//			datas.remove(datas.indexOf(81));
//		}
//		System.out.println(datas);
		
//  2. 값으로 삭제
		datas.remove(Integer.valueOf(81));
		System.out.println(datas);
	}
}






