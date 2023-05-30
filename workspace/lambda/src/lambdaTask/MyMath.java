package lambdaTask;

import java.util.Scanner;

public class MyMath {
   public static Calc calculator(String oper) { //Calc 인터페이스 타입의 calculator를 static 메소드로 선언
	   // 매개변수로 String 타입의 oper를 받는다 -> oper는 이제 수식(+, -)
      Calc calc = null; //Calc 타입의 calc 객체를 null로 초기화
      switch(oper) { //switch 문으로 oper 값 비교?
      case "+": //oper가 + 라면
         calc = (n1, n2) -> n1 + n2; //람다식 -> 숫자1 + 숫자2 해라
         break; // 반복문 탈출
      case "-": //oper가 - 면
         calc = (n1, n2) -> n1 - n2; //빼라
         break; //탈출
      }
      return calc; //리턴 값 calc 즉 +면 두 수 더한 값, -면 뺀 값
   }
   
   public static void main(String[] args) { //메인메소드
      OperCheck operCheck = e -> { //얘도 람다식 OperCheck 인터페이스 타입의 operCheck라는 객체에 e를 담는다
    	  //수식 체크
         String temp = ""; //임시변수 -> 문자 넣는거 아니고 더할거라 빈 문자열로 초기화
         for (int i = 0; i < e.length(); i++) { //e의 길이만큼 반복돌린다 -> 정수 개수 모르니까
            char c = e.charAt(i); //e의 i번째 방에 있는 값을 문자로 형변환해서 c라는 변수에 담는다
            if(c == '-' || c == '+') { //c가 +거나 -면 temp에 담는다
               temp += c;
            }
         }
         System.out.println(temp);
         System.out.println(e);
         return temp.split(""); //temp를 문자열로 쪼개줌 예) "1+2+3" -> "1", "+", "2", "+", "3"
      };
      
      String message = "정수의 덧셈, 뺄셈에 대한 식을 입력하세요.";
      String exampleMessage = "예)9+7-5";
      String expression = null;
      String[] opers = null, numbers = null;
      
      int number1 = 0, number2 = 0;
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println(message);
      System.out.println(exampleMessage);
      expression = sc.nextLine(); //예) 1 + 2 - 4 이런 식으로 적어라니까 문자열로 입력받는다
//      expression = expression.trim(); // 앞 뒤 공백 제거
//      expression = expression.replaceAll(" ", ""); // 사이 공백 제거
      //이렇게 하면 띄어쓰기 무시 ->1+2+3 , 1 + 2 + 3 어떻게 써도 상관업씀
      
      opers = operCheck.getOpers(expression); //식 가져와서 체크해라
      numbers = expression.split("\\+|\\-"); //식에 +나-를 구분점으로 쪼개라
      
      number1 = Integer.parseInt(numbers[0].equals("") ? opers[0] + numbers[1] : numbers[0]);
      //삼항연산자
      //numbers(입력한 숫자 -> 식에서 + - 구분점으로 나눈 수들)의 0번째 인덱스가 비어있다면?
      //opers의 0번째 값(첫번째 수식이 +면 +, -면 -)과 첫번째 인덱스의 숫자를 더해줘라
      //그게 아니라면(number의 0번째 값이 비어있지 않다면) 그냥 그대로
      //이게 음수 때문에 만든 식임
      //처음에 -1 + 2 이런식으로 할 때 처음 -도 계산해야돼서
      //이거 안쓰면 첫 숫자 음수면 오류남
      
      for (int i = 0; i < opers.length; i++) {
         if(i == 0 && number1 < 0) {continue;}
         number2 = Integer.parseInt(numbers[i + 1]);
         number1= MyMath.calculator(opers[i]).calc(number1, number2);
      }
      //이거 설명, 결합성을 이용하여 짠 식
      //만약 3 + 4 + 5 라고 하면 3 + 4, (3+4) - 5 이렇게 나눈다
      //number2는 4고 number1은 number1인 3과 number2인 4를 더해준 값을 넣어준다.
      //그럼 number1은 7이 되고, 다시 number2는 5니까 number1인 7과 number2 5를 더해주고
      //12를 number1에 넣어준다. 그럼 최종값은 number1이 된당
      
      System.out.println(number1);
   }
}

















