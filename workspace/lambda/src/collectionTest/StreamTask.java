package collectionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class StreamTask {
	public static void main(String[] args) {
//      10~1까지 ArrayList에 담고 출력
//		ArrayList<Integer> datas = new ArrayList<Integer>();
//		IntStream.rangeClosed(0, 9).forEach(data -> datas.add(10 - data));
//		System.out.println(datas);

//      1~10까지 ArrayList에 담고 출력
//		IntStream.rangeClosed(1, 10).forEach(data -> datas.add(data));
//		System.out.println(datas);

//      ABCDEF를 각 문자별로 출력
		ArrayList<String> datas = new ArrayList<String>();
//		"ABCDEF".chars().forEach(c -> System.out.println((char)c));
//		IntStream.rangeClosed('A', 'F').forEach(c -> System.out.println((char)c));
		"ABCDEF".chars().forEach(c -> System.out.println((char)c));
		

//      1~100까지 중 홀수만 ArrayList에 담고 출력
//		IntStream.rangeClosed(1, 50).map(data -> data * 2 - 1).forEach(data -> System.out.println(data));

//      A~F까지 중 D제외하고 ArrayList에 담고 출력
//		IntStream.range('A', 'F').map(c -> c > 67 ? c+1 : c).forEach(c -> System.out.println((char)c));

//      5개의 문자열을 모두 소문자로 변경(Black, WHITE, reD, yeLLow, PINk), toLowerCase()
//		ArrayList<String> datas = new ArrayList<String>(Arrays.asList("Black", "WHITE", "reD", "yeLLow", "PINk"));
//		ArrayList<String> results = (ArrayList<String>)
//		datas.stream().map(v -> v.toLowerCase()).collect(Collectors.toList());
//		System.out.println(results);
		
//      1~100까지 중 짝수만 ArrayList에 담고 출력
//      IntStream.range(1, 101).filter(data -> data % 2 = 0).forEach(datas::add);
//      System.out.println(datas);

//      Apple, banana, Melon 중 첫번째 문자가 대문자인 문자열 출력
//		ArrayList<String> datas = new ArrayList<String>(Arrays.asList("Apple", "banana", "Melon"));
//		datas.stream().filter(c -> c.charAt(0) >= 65).filter(c -> c.charAt(0) <= 90).forEach(c -> System.out.println(c));

//      한글을 정수로 변경
		String hangle = "공일이삼사오육칠팔구";
//		"일공이사".chars().map(c -> hangle.indexOf(c)).forEach(c -> System.out.print(c));
		
		
//      정수를 한글로 변경
//		String number = "0123456789";
//		"1024".chars().map(c -> c - 48).forEach(c -> System.out.print(hangle.charAt(c)));
	}
}











