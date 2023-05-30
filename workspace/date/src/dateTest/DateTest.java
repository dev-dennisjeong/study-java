package dateTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		System.out.println(simpleDateFormat.format(now));
//		getTime() : 두 날짜의 차이를 구할 때 사용, 밀리초 리턴
		System.out.println(now.getTime());
	}
}
