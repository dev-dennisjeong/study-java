package lambdaStudy;

public class PlusNumberTest {
	public int printPlusNumber(PlusNumber plus, int number1, int number2) {
		return plus.plusNumber(number1, number2);
	}
	
	public static void main(String[] args) {
		PlusNumber plus = (int number1, int number2) -> number1 +number2;
		System.out.println(plus.plusNumber(10, 5));
	}
}
