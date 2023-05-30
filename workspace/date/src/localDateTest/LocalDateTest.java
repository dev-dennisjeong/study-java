package localDateTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
//      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");

		System.out.println(LocalDateTime.parse("2023년 12월 04일 12시 00분 00초",
				DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")));

		System.out.println(now);
		System.out.println(localDateTime.format(dateTimeFormatter));
	}
}