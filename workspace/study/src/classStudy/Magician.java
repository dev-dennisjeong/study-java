package classStudy;

import java.util.Random;
import java.util.Scanner;

public class Magician {
//	����, ������
//	�̸�, ���, ����, ����
//	���� ���� -> 1����, �����, ü�� +5, ���� +5 �� +1000
//	���� �� ������ �ʿ�
//	�¸� �� +�� +���� �й� �� -�� ���º� �� ��������
//	����5�̻� �� �� ��޽��� ������ ���� �ʿ�
//	���躸�� ���++ 
//	�� ������ ���� ����
//	����� �����
//	���� ��� �����
//	�����ϱ� +����
//	��ų �����(����� 1 �ǹ� 2 ��� 3 ���̾� 4)
//	������ �� ü�� �����
//	�Ա� �����
//	��ħ ���� ���� �� 1ȸ�� Ȱ�� ����
//	ü�� ������ ���ڱ� �ۿ� ����
//	�� ������ ���� ����

	// �̸�
	String name;
	// ����
	int level;
	// ü��
	int hp;
	// ����
	int intellect;
	// ��
	int money;
	// �������
	int enemy;
	// ���
	String tier;
	//���� Ȯ��
	int pass;
	// �հ� Ƚ��
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

	//���� �ܴ�
	void sleep() {
		this.hp++;
	}

	//���� �Դ´�
	void eat() {
		this.hp++;
		this.money -= 100;
	}

	// ����� �Ѵ�
	void hunt() {
		this.money += 100;
		this.hp--;
		this.level++;
	}

	// ���θ� �Ѵ�
	void study() {
		this.intellect++;
		this.hp--;
	}

	// ������ �Ѵ�
	void battle() {
		this.money -= 1000;
	}

	// ���� ��븦 �����´�
	// ������ ���� ���(�̱� Ȯ��) 60����
	// ������ ���� ���(�� Ȯ��) 30����
	// ���� ����� ���(���º��� Ȯ��) 10����
	void setEnemy() {
		Random random = new Random();
		int[] rating = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 2 };
		this.enemy = rating[random.nextInt(rating.length)];
	}

	// ������ ����
	void DoTest() {
		this.money -= 5000;
		this.hp -= 3;
	}
	
	//���� Ȯ��(�ݹ�)
	int probTest() {
		Random random = new Random();
		int[] rating = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
		this.pass = rating[random.nextInt(rating.length)];
		return this.pass;
	}

	// ��� Ȯ��
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
		String[] enemies = { "���� ���", "����� ���", "���� ���" };
		String title = "�� ������ �����";
		String message = "\n1. ���� ������\n2. ���� Ȯ��\n3. ����\n���� : ";
		String choiceMessage = "\nQ.������ �Ͻðڽ��ϱ�?\n1. ����ϱ�\n2. �����ϱ�\n3. �����ϱ�\n" + "4. ���躸��\n5. ���ڱ�\n6. ��Ա�\n���� : ";
		String battleMessage = "�����Ͻðڽ��ϱ�?\n1.�����Ѵ�.\n2.��������.";
		String errorMessage = "�߸� �Է��߽��ϴ�.";
		int choice = 0;

		Magician magician = new Magician("���̽�", 1, "Bronze", 5, 5, 1000);

		Scanner sc = new Scanner(System.in);


		System.out.println(magician.name + title);

		while (true) {
			System.out.print(message);
			choice = sc.nextInt();

			if (choice == 3) { //����
				break;
			}

			switch (choice) {
			case 1: 
				System.out.println(choiceMessage);
				choice = sc.nextInt();

				switch (choice) {
				case 1: //����� �Ѵ�. (ü�� ����, ���� +1, �� +100)
					if (magician.hp < 1) {
						System.out.println("ü���� �����մϴ�.");
					} else {
						magician.hunt();
						System.out.println("����Ͽ����ϴ�.");
					}
					break;
				case 2: //���θ� �Ѵ�. (ü�� ����, ���� +1)
					if (magician.hp < 1) {
						System.out.println("ü���� �����մϴ�.");
					} else {
						magician.study();
						System.out.println("�����Ͽ����ϴ�.");
					}
					break;
				case 3: //������ �Ѵ�. (������ -1000)
					magician.setEnemy(); //���� ��� ���� ����
					System.out.println(enemies[magician.enemy]);
					System.out.println(battleMessage); //���� or ���� ����
					choice = sc.nextInt();
					if (choice == 1) { //���� ����
						magician.battle();
						if (magician.enemy == 0) { //�¸��� �� +2000, ����+1
							System.out.println("�¸��Ͽ����ϴ�.");
							magician.money += 2000;
							magician.level++;
							magician.hp--;

							break;
						}
						if (magician.enemy == 1) { //���ºν� ���� ����
							System.out.println("���º��Դϴ�.");
							magician.hp--;

							break;
						}

						if (magician.enemy == 2) { //�й�� ü���� �� ���� ���δ�.
							System.out.println("�й��Ͽ����ϴ�.");
							magician.hp -= 2;

							break;
						}

						break;
					}
					if (choice == 2) { //���� ����
						break;
					}
				case 4: //��� ������ ����.
					if (magician.hp > 2) { //����� ü���� �ʿ�
						if (magician.money >= 5000) { //�� �ʿ�
							if (magician.intellect >= 10) { //���� �ʿ�
								if (magician.level >= 5) { //���� �ʿ�
									magician.DoTest(); //����� ����
									magician.probTest(); //���� ���� Ȯ��
									if(magician.pass == 0 && magician.count < 3) {
									System.out.println("���迡 �հ��߽��ϴ�.");
									magician.count++;
									magician.intellect += 3;
									}else if(magician.pass == 0 && magician.count >= 3) {
										//���̻� ���� ����� ���� ��
										System.out.println("�ְ� ����Դϴ�.");
									}
									if(magician.pass == 1) {
										System.out.println("���迡 ���հ��߽��ϴ�.");
									}
								} else {
									System.out.println("���� 5���� ��� ������ �� �� �ֽ��ϴ�.");
								}
							} else {
								System.out.println("������ ���ڶ��ϴ�.");
							}
						} else {
							System.out.println("���� �����մϴ�.");
						}
					} else {
						System.out.println("����� hp�� �ʿ��մϴ�.");
					}
					break;

				case 5:
					magician.sleep(); //���� �ܴ� (ü�� ����)
					System.out.println("���� ����ϴ�.");
					break;

				case 6:
					magician.eat(); //���� �Դ´� (ü�� ����)
					System.out.println("���� �Ծ����ϴ�.");
					break;
				}
				break;
			case 2:
				System.out.println( //���� ���
						"\n�̸� : " + magician.name + " ���� : " + magician.level + " ��� : " + arTier[magician.count]
								+ " ���� : " + magician.intellect + " ü�� : " + magician.hp + " �� : " + magician.money);
				break;
			default: //�߸��Է����� ��
				System.out.println(errorMessage);
				break;
			}
		}
	}
}
