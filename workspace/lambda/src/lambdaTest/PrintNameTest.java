package lambdaTest;

public class PrintNameTest {
	public String printFullName(PrintName printName, String firstName, String lastName) {
		return printName.getFullName(firstName, lastName);
	}

	public static void main(String[] args) {
		PrintName printName = (firstName, lastName) -> firstName + " " + lastName;
		System.out.println(new PrintNameTest().printFullName(printName, "정", "지욱"));

	}

}
