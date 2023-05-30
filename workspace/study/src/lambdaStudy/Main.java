package lambdaStudy;

import java.util.Scanner;

public class Main {

	public static Calc calculator(String oper) {
		Calc calc = null;
		switch(oper) {
		case "+":
			calc = (n1, n2) -> n1 + n2;
			break;
		case "-":
			calc = (n1, n2) -> n1 - n2;
			break;
		}
		return calc;
	}
	
	public static void main(String[] args) {
		OperCheck operCheck = e -> {
			String temp = "";
			for (int i = 0; i < e.length(); i++) {
				char c = e.charAt(i);
				if(c == '+' || c== '-') {
					temp += c;
				}
			}
			return temp.split("");
		};
		
		String message = "식을 입력하세요.";
		String expression = null;
		String[] opers = null, numbers = null;
		
		int firstNumber = 0, secondNumber = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(message);
		expression = sc.nextLine();
		
		opers = operCheck.getOpers(expression);
		numbers = expression.split("\\+|\\-");
		
		firstNumber = Integer.parseInt(numbers[0].equals("") ? opers[0] + numbers[1] : numbers[0]);
		
		for (int i = 0; i < opers.length; i++) {
			if(i == 0 && firstNumber <0) {continue;}
			secondNumber = Integer.parseInt(numbers[i + 1]);
			firstNumber = Main.calculator(opers[i]).calc(firstNumber, secondNumber);
		}
		
		System.out.println(firstNumber);
	}
}
