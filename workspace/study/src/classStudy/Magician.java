package classStudy;

import java.util.Random;
import java.util.Scanner;

public class Magician {
//	직업, 마법사
//	이름, 등급, 전투, 시험
//	시작 스텟 -> 1레벨, 브론즈, 체력 +5, 지력 +5 돈 +1000
//	결투 시 참가비 필요
//	승리 시 +돈 +레벨 패배 시 -돈 무승부 시 변동없음
//	레벨5이상 일 때 등급시험 참가비 지력 필요
//	시험보면 등급++ 
//	돈 없으면 전투 못함
//	사냥터 만들기
//	결투 상대 만들기
//	공부하기 +지력
//	스킬 만들기(브론즈 1 실버 2 골드 3 다이아 4)
//	전투력 및 체력 만들기
//	먹기 만들기
//	아침 점심 저녁 각 1회씩 활동 가능
//	체력 없으면 잠자기 밖에 못함
//	돈 없으면 결투 못함

	// 이름
	String name;
	// 레벨
	int level;
	// 체력
	int hp;
	// 지력
	int intellect;
	// 돈
	int money;
	// 결투상대
	int enemy;
	// 등급
	String tier;
	//시험 확률
	int pass;
	// 합격 횟수
	int count;


	public Magician() {;}

	public Magician(String name, int level, String tier, int hp, int intellect, int money) {
		this.name = name;
		this.level = level;
		this.tier = tier;
		this.hp = hp;
		this.intellect = intellect;
		this.money = money;
	}

	//잠을 잔다
	void sleep() {
		this.hp++;
	}

	//밥을 먹는다
	void eat() {
		this.hp++;
		this.money -= 100;
	}

	// 사냥을 한다
	void hunt() {
		this.money += 100;
		this.hp--;
		this.level++;
	}

	// 공부를 한다
	void study() {
		this.intellect++;
		this.hp--;
	}

	// 결투를 한다
	void battle() {
		this.money -= 1000;
	}

	// 결투 상대를 가져온다
	// 나보다 약한 상대(이길 확률) 60프로
	// 나보다 강한 상대(질 확률) 30프로
	// 나랑 비슷한 상대(무승부일 확률) 10프로
	void setEnemy() {
		Random random = new Random();
		int[] rating = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 2 };
		this.enemy = rating[random.nextInt(rating.length)];
	}

	// 시험을 본다
	void DoTest() {
		this.money -= 5000;
		this.hp -= 3;
	}
	
	//시험 확률(반반)
	int probTest() {
		Random random = new Random();
		int[] rating = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
		this.pass = rating[random.nextInt(rating.length)];
		return this.pass;
	}

	// 등급 확인
	String checkTier(String[] arTier) {
		String result = null;
		if (level <= 5) {
			result = arTier[0];
		} else if (level <= 10) {
			if (count >= 1) {
				result = arTier[1];
			}
		} else if (level <= 15) {
			if (count >= 2) {
				result = arTier[2];
			}
		} else {
			if (count >= 3) {
				result = arTier[3];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String[] arTier = { "Bronze", "Silver", "Gold", "Diamond" };
		String[] enemies = { "약한 상대", "비슷한 상대", "강한 상대" };
		String title = "의 마법사 성장기";
		String message = "\n1. 오늘 뭐하지\n2. 정보 확인\n3. 은퇴\n선택 : ";
		String choiceMessage = "\nQ.무엇을 하시겠습니까?\n1. 사냥하기\n2. 공부하기\n3. 결투하기\n" + "4. 시험보기\n5. 잠자기\n6. 밥먹기\n선택 : ";
		String battleMessage = "결투하시겠습니까?\n1.결투한다.\n2.도망간다.";
		String errorMessage = "잘못 입력했습니다.";
		int choice = 0;

		Magician magician = new Magician("에이스", 1, "Bronze", 5, 5, 1000);

		Scanner sc = new Scanner(System.in);


		System.out.println(magician.name + title);

		while (true) {
			System.out.print(message);
			choice = sc.nextInt();

			if (choice == 3) { //종료
				break;
			}

			switch (choice) {
			case 1: 
				System.out.println(choiceMessage);
				choice = sc.nextInt();

				switch (choice) {
				case 1: //사냥을 한다. (체력 차감, 레벨 +1, 돈 +100)
					if (magician.hp < 1) {
						System.out.println("체력이 부족합니다.");
					} else {
						magician.hunt();
						System.out.println("사냥하였습니다.");
					}
					break;
				case 2: //공부를 한다. (체력 차감, 지력 +1)
					if (magician.hp < 1) {
						System.out.println("체력이 부족합니다.");
					} else {
						magician.study();
						System.out.println("공부하였습니다.");
					}
					break;
				case 3: //결투를 한다. (참가비 -1000)
					magician.setEnemy(); //결투 상대 랜덤 결정
					System.out.println(enemies[magician.enemy]);
					System.out.println(battleMessage); //결투 or 도망 선택
					choice = sc.nextInt();
					if (choice == 1) { //결투 선택
						magician.battle();
						if (magician.enemy == 0) { //승리시 돈 +2000, 레벨+1
							System.out.println("승리하였습니다.");
							magician.money += 2000;
							magician.level++;
							magician.hp--;

							break;
						}
						if (magician.enemy == 1) { //무승부시 변동 없음
							System.out.println("무승부입니다.");
							magician.hp--;

							break;
						}

						if (magician.enemy == 2) { //패배시 체력이 더 많이 깎인다.
							System.out.println("패배하였습니다.");
							magician.hp -= 2;

							break;
						}

						break;
					}
					if (choice == 2) { //도망 선택
						break;
					}
				case 4: //등급 시험을 본다.
					if (magician.hp > 2) { //충분한 체력이 필요
						if (magician.money >= 5000) { //돈 필요
							if (magician.intellect >= 10) { //지력 필요
								if (magician.level >= 5) { //레벨 필요
									magician.DoTest(); //시험비 차감
									magician.probTest(); //시험 붙을 확률
									if(magician.pass == 0 && magician.count < 3) {
									System.out.println("시험에 합격했습니다.");
									magician.count++;
									magician.intellect += 3;
									}else if(magician.pass == 0 && magician.count >= 3) {
										//더이상 오를 등급이 없을 시
										System.out.println("최고 등급입니다.");
									}
									if(magician.pass == 1) {
										System.out.println("시험에 불합격했습니다.");
									}
								} else {
									System.out.println("레벨 5부터 등급 시험을 볼 수 있습니다.");
								}
							} else {
								System.out.println("지능이 모자랍니다.");
							}
						} else {
							System.out.println("돈이 부족합니다.");
						}
					} else {
						System.out.println("충분한 hp가 필요합니다.");
					}
					break;

				case 5:
					magician.sleep(); //잠을 잔다 (체력 충전)
					System.out.println("잠을 잤습니다.");
					break;

				case 6:
					magician.eat(); //밥을 먹는다 (체력 충전)
					System.out.println("밥을 먹었습니다.");
					break;
				}
				break;
			case 2:
				System.out.println( //정보 출력
						"\n이름 : " + magician.name + " 레벨 : " + magician.level + " 등급 : " + arTier[magician.count]
								+ " 지력 : " + magician.intellect + " 체력 : " + magician.hp + " 돈 : " + magician.money);
				break;
			default: //잘못입력했을 시
				System.out.println(errorMessage);
				break;
			}
		}
	}
}
