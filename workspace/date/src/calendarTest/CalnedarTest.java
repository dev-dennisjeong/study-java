package calendarTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalnedarTest {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		System.out.println(simpleDateFormat.format(calendar.getTime()));
		try {
			System.out.println(simpleDateFormat.parse("2023-01-15"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
