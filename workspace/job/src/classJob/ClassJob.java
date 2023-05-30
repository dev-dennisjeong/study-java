package classJob;

import java.util.Random;
import java.util.Scanner;

//	직업, 마법사
//	이름, 등급, 전투, 시험, 속성
//	승리 시 +money, +level 패배시 -money
//	레벨5이상 일 때 등급시험 후 등급 업
//	5->브론즈 10-> 실버 15-> 골드 20 -> 다이아
//	단, 순차적으로 승급해야함
//	돈 없으면 전투 못함

	class Magician{
		Random random = new Random();
		//이름
		String name;
		//레벨
		int level;
		//속성
		String type;
		//자산
		int asset;
		//결투
		int battle;
		//정보
		String data;
		//적
		int enemy;
		
//		수입
//		int income;
		//싸움결과
		String fightResult;
		//시험결과
		String TestResult;
		//등급
		String[] arTier;
		//합격 횟수
		int count;
		
		
		{
			level = 1;
			asset = 1000;
		}

		public Magician() {;}
		
		public Magician(String name, int level, String type) {
			this.name = name;
			this.level = level;
			this.type = type;
		}

		public Magician(String name, int level) {
			this.name = name;
			this.level = level;
		}
		
		// 돈을 번다
		void income(int asset) {
			this.asset += asset;
		}
		
		//결투를 한다
		//나보다 약한 상대(이길 확률) 60프로 
		//나보다 강한 상대(질 확률) 30프로 
		//나랑 비슷한 상대(무승부일 확률) 10프로
		void setEnemy() {
			Random random = new Random();
			int[] rating = {0,0,0,0,0,0,1,1,1,2,};
			this.enemy = rating[random.nextInt(rating.length)];
		}
		
		//결투 : 이길 확률 50% 승리시 +500, 레벨 1 상승 패배시 -300
		String fight() {
			int[] rating = new int[10];
			int data = 70;
			if(asset>=500) {
				
				for (int i = 0; i < data / 10; i++) {
					rating[i] = 1;
				}
				
				if(rating[random.nextInt(10)] == 1) {
					fightResult = "승리하였습니다.\n레벨1이 상승하였습니다.\n돈을 500원 벌었습니다.";
					this.asset += 500;
					this.level++;
				}else {
					fightResult = "패배하였습니다.\n돈을 300원 잃었습니다.";
					this.asset -= 300;
				}
				
			}else {
				fightResult = "돈이 모자랍니다.";
			}
			return fightResult;
		}
		
		
		
		//등급시험보기 : 레벨 5이상 되면 등급 시험 가능, 티어 상승
		String DoTest() {
				if(level >= 5) {
					arTier =  new String[4];
					for (int i = 0; i < arTier.length; i++) {
						String result = i < 3 ? arTier[i+1] : arTier[3];
					}
					TestResult = "시험에 합격했습니다.";
					count++;
				}
				else {
					TestResult = "아직 시험을 볼 수 없습니다.";
				}
				return TestResult;
			}	
		
		
		//확률
//		void checkProbibility() {
//			int[] rating = new int[10];
//			int data = 30;
//			
//			for (int i = 0; i < data / 10; i++) {
//				rating[i] = 1;
//			}
//			
//			if(rating[random.nextInt(10)] == 1) {
//				System.out.println("합격");
//			}else {
//				System.out.println("불합격");
//			}
//		}
		
		//자산 확인
		int checkMoney() {
			return asset;
		}
		
		//정보 확인
		String checkData() {
			data = "이름 : " + name + "\n레벨 : " + level + "\n속성 : " + type;
			return data;
		}
		
		//등급 확인
		String checkTier() {
			String[] arTier = {"Bronze", "Silver", "Gold", "Diamond"};
			String result = null;
			if(level<=5) {
				result = arTier[0];
			}else if(level<=10) {
				if(count>=1) {
					result = arTier[1];
				}
			}else if(level<=15) {
				if(count>=2) {
					result = arTier[2];
				}
			}else {
				if(count>=3) {
					result = arTier[3];
				}
			}
			return result;
	}
		
}


public class ClassJob {
	public static void main(String[] args) {
		Magician Magician = new Magician("아오키지", 1);
//		Magician FireMagician = new Magician("에이스", 1, "불");
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		int secondChoice = 0;
		int DoChoice = 0;
		boolean flag = false;
		String[] arTier = {"Bronze", "Silver", "Gold", "Diamond"};
		String title = "마법사 성장기";
		String message = "\n1. 오늘 뭐하지\n2. 스텟 확인\n3. 자산 확인\n4. 등급 확인\n5. 은퇴\n선택 : ";
		String typeMessage = "\"타입 선택을 선택하세요.\n1. 얼음\n2.불\n선택: ";
		String choiceMessage = "\nQ.무엇을 하시겠습니까?\n1. 결투 신청\n2. 승급 시험\n3. 집가기\n선택 : ";
		String errorMessage = "잘못 입력했습니다.";
		
		System.out.println(title);
		System.out.print(typeMessage);
		secondChoice = sc.nextInt();
		
		if(secondChoice == 1) {
			Magician.type = "얼음";
		}else if(secondChoice == 2) {
			Magician.type = "불";
		}else {
			System.out.println(errorMessage);
		}
		
		System.out.println(Magician.type + " 속성을 선택하셨습니다!");
		
		while(true) {
			System.out.print(message);
			choice = sc.nextInt();
			
			if(choice>5 || choice<0) {
				System.out.println(errorMessage);
				continue;
			}
			
			if(choice==2) {
				System.out.println("\n - 정보 - \n" + Magician.checkData());
//				System.out.println("\n - 정보 - \n" + FireMagician.checkData());
				continue;
			}
			
			if(choice==3) {
				System.out.println("\n - 자산 -  \n" + "현재 " + Magician.name + "님의 자산은 " 
			+ Magician.checkMoney() + "원 입니다.");
//				System.out.println("\n - 자산 -  \n" + "현재 " + FireMagician.name + "님의 자산은 " 
//						+ FireMagician.checkMoney() + "원 입니다.");
				continue;
			}
			if(choice==4) {
				System.out.println("\n - 등급 - \n" + Magician.name + "님의 등급은 " + Magician.checkTier()
				+ " 입니다.");
//				System.out.println("\n - 등급 - \n" + FireMagician.name + "님의 등급은 " + FireMagician.checkTier()
//				+ " 입니다.");
				continue;
			}
			if(choice==5) {
				break;
				}
			
			while(true) {
				System.out.print(choiceMessage);
				DoChoice = sc.nextInt();
				
				switch(DoChoice) {
				case 1:
					System.out.println(Magician.fight());
//					System.out.println(FireMagician.fight());
					break;
				case 2:
					System.out.println(Magician.DoTest());
//					System.out.println(FireMagician.DoTest());
					break;
				case 3:
					break;
				default:
					System.out.println(errorMessage);
					continue;
				}
				
				break;
			}
			

		}
		
		
	}
}



















