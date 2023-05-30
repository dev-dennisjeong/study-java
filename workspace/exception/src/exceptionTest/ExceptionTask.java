package exceptionTest;

import java.util.Scanner;

public class ExceptionTask {
   public static void main(String[] args) {
//      5개의 정수만 입력받기
//      - 무한 입력 상태로 구현
//      - q 입력 시 나가기
//      - 각 정수는 배열에 담기
//      - if문은 딱 한 번만 사용하기
      Scanner sc = new Scanner(System.in);
      int[] arData = new int[5];
      String temp = null;
      
      for(int i=0;; i++) { //for문으로 무한반복
         System.out.print(i + 1 + "번째 정수[q: 나가기]: ");
         temp = sc.next(); //temp라는 String 임시 변수로 입력 문자 받기
         temp = temp.toLowerCase(); //만약 대문자 입력하면 소문자로 바꿔준다
         if(temp.equals("q")) { //temp에 q를 입력하면
            break; //반복문 탈출 -> 실행문 종료
         }
         
         try {
            arData[i] = Integer.parseInt(temp); //오류가 발생할거 같은 문장
            
         } catch (NumberFormatException e) { //예외이름
            System.out.println("정수만 입력해주세요."); //정수 외 다른 문자 입력 시 출력
            i--; // i가 반복문을 돌면서 1 증가하였으므로 1을 빼주고 다시 돌린다
            
         } catch (ArrayIndexOutOfBoundsException e) { //예외이름
            System.out.println("5개의 정수만 입력이 가능합니다."); //길이가 5를 넘어가면 출력
            for (int j = 0; j < arData.length; j++) { //arData의 길이만큼 반복을 돌린다
               System.out.print(arData[j] + " "); //앞서 입력받은 5개의 정수 출력
            }
            break; //실행문 종료
            
         } catch (Exception e) {
            System.out.println("다시 시도해주세요."); //위의 오류가 아닐 시 출력
            i--;
         }
      }
   }
}















