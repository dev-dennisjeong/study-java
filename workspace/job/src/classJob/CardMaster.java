package classJob;

import java.util.Random;
import java.util.Scanner;

// 타짜
// 족보(끗, 땡, 광땡)
// 나랑 상대방과 대결
// 계급: 호구, 물주, 마담, 타짜
// 패를 받을 시 추가 금액 설정
// 같은 패가 나올 시 다음 판 두 배
// 다이, 고 결정
// 판돈: 1억
// 연속 3회 승리 시 계급 상승
// 연속 3회 패배 시 계급 하락

public class CardMaster {
	// 이름
   String nickname;
   // 돈
   int money;
   // 계급
   int level;
   // 패
   int deck;
   // 승리 횟수
   int winCount;
   
   public CardMaster() {;}

   public CardMaster(String nickname, int money) {
	   //생성자를 닉네임, 머니로 받음
      this.nickname = nickname;
      this.money = money;
   }
   
//   판돈 및 추가 금액을 받는다.
   void deposit(int money) {
      this.money -= money;
   }
   
//   끗(60) 땡(30) 광땡(10) 확률, 광땡이면 무조건 이김
   void getMyDeck() {
      Random random = new Random();
      int[] rating = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2};
      this.deck = rating[random.nextInt(rating.length)];
   }
   
   int battle(CardMaster cardMaster) {
//      -1 : draw, 0 : lose, 1 : win
      boolean draw = this.deck == cardMaster.deck;
      boolean lose = this.deck > cardMaster.deck;
      boolean win = this.deck < cardMaster.deck;
      boolean[] status = {draw, lose, win};
      int result = 0;
      //draw는 -1이므로 +1씩 해서
      //0번째 인덱스부터 차례로 draw, lose, win을 받는다
      for (int i = -1; i < 2; i++) {
         if(status[1 + i]) {
            result = i;
            break;
         }
      }
      return result;
    
   }
   public static void main(String[] args) {
	      String[] arLevel = {"호구", "물주", "마담", "타짜"}; //계급
	      String[] decks = {"광땡", "땡", "끗"}; //패
	      String[] status = {"무승부", "패배", "승리"}; //승패결과
	      String message = "1. 대결하기\n2. 나의 정보\n3. 나가기";
	      String depositMessage = "1. 추가금액 입력\n2. 추가금액 없이 진행";
	      int choice = 0, money = 0, defaultMoney = 10_000, result = 0, depositMoney = 0;
	      //10_000 자바에서는 쉼표 대신 _를 많이 쓴다
	      Scanner sc = new Scanner(System.in);
	      CardMaster me = new CardMaster("고니", 0);
	    //사용자인 나를 me라는 객체명으로 CardMaster 클래스를 받는다
	      CardMaster computer = new CardMaster("아귀", 0);
	    //상대방 computer라는 객체명
	      String title = null;
	      
	      while (true) {
	         title = "[" + arLevel[me.level] + "]" + me.nickname + " 자산: " + me.money + "\n"
	               + "[" + arLevel[computer.level] + "]" + computer.nickname + " 자산: " + computer.money;
	         System.out.println(title);
	         System.out.println(message);
	         choice = sc.nextInt();
	         
	         if(choice == 3) {break;} //만약 3인 나가기를 누르면 반복문 탈출하면서 종료
	         
	         switch(choice) {
	         case 1: // 대결하기
	            me.deposit(defaultMoney); //베팅 금액은 defaultMoney(10000원으로 설정했음)
	            computer.deposit(defaultMoney); //상대방의 베팅금액(나와 같은 값)
	            money += defaultMoney * 2; //나와 상대방 2명이 베팅했으므로 money에 2를 곱해준다
	            me.getMyDeck(); //나의 패를 받아온다
	            computer.getMyDeck(); //상대방의 패를 받아온다
	            System.out.println("나의 패: " +decks[me.deck]);  //나의 패 확인
	            
	            System.out.println(depositMessage);
	            choice = sc.nextInt();
	            
	            if(choice == 1) {  //추가 금액 선택 시 금액 입력
	               System.out.print("추가 금액: ");
	               depositMoney = sc.nextInt();
	               me.deposit(depositMoney);
	               computer.deposit(depositMoney); // 내가 입력한 금액 만큼 상대방도 똑같이 베팅
	               money += depositMoney * 2; // 내 베팅금액 + 상대방 베팅금액
	            }
	            
	            result = me.battle(computer);
	            System.out.println(status[result + 1]);
	            
	            switch(result) {
	            case -1: // 무승부
	               while(result == -1) {
	                  money *= 2; //묻고 더블로
	                  System.out.println(money);
	                  me.getMyDeck();
	                  System.out.println("나의 패: " +decks[me.deck]);
	                  System.out.println("1. 고\n2. 다이"); //내 패보고 고 다이 선택
	                  choice = sc.nextInt();
	                  if(choice == 2) { //다이했을 때
	                     result = 0; 
	                     System.out.println(status[result + 1]); //첫번째 인덱스인 패배
	                     computer.money += money; //컴퓨터에 돈 추가
	                     me.money -= money - defaultMoney - depositMoney;
	                     // 내 돈 - 더블 금액(전 판에 베팅한 돈 * 2) - 판돈 - 추가 금액 입력한 금액
	                     me.winCount = 0; //이긴 횟수 초기화
	                     computer.winCount++; //winCount 1증가
	                     break;
	                  }
	                  result = me.battle(computer);
	                  System.out.println(status[result + 1]);
	                  //result는 -1, 0, 1 status에서 인덱스를 가져오기 위해 +1을 해줘야 한다
	                  
	                  switch(result) {
	                  case 0: // 패배
	                     computer.money += money; //컴퓨터에 금액 추가
	                     me.money -= money - defaultMoney - depositMoney;
	                     // 내 돈 - 베팅 금액 - 판돈 -추가 금액 입력한 금액
	                     me.winCount = 0; //이긴 횟수 초기화
	                     computer.winCount++;
	                     break;
	                  case 1: // 승리
	                     me.money += money;
	                     computer.money -= money - defaultMoney - depositMoney;
	                     me.winCount++;
	                     computer.winCount = 0;
	                     break;
	                  }
	               }
	               break;
	            case 0: // 패배
	               computer.money += money;
	               me.money -= money - defaultMoney - depositMoney;
	               me.winCount = 0;
	               computer.winCount++;
	               break;
	            case 1: // 승리
	               System.out.println(money);
	               me.money += money;
	               computer.money -= money - defaultMoney - depositMoney;
	               me.winCount++;
	               computer.winCount = 0;
	               break;
	            }
	            
	            //계급이 타짜 미만이면서 3회 승리시 계급 상승
	            if(me.winCount > 2 && me.level < 3) {
	               me.level++;
	            }
	            if(computer.winCount > 2 && computer.level < 3) {
	               computer.level++;
	            }
	            break;
	         case 2: // 나의 정보
	            break;
	         default: // 그 외
	            System.out.println("다시 입력해주세요.");
	            break;
	         }
	      }
	      
	      
   }
	

   
   
   
   
} 
   

