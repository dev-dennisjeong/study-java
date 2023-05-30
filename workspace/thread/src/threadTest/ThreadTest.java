package threadTest;

public class ThreadTest {
	public static void main(String[] args) {
		
		//�ڿ��� �ϳ�, ���� ���� ������
		//�ڿ��� ���� run�̶�� �޼ҵ尡 �����ǵ� ��ü�� ����
		//Thread2 thread = new Thread2(); //run�� �����ǵ� ��ü -> Thread2 �ϳ� -> �ڿ�
		//Runnable thread = new Thread2(); ���� ����
		//�θ�Ÿ��							�ڽİ� -> ��ĳ����
		//thread�� Ÿ���� 3���� Thread2, Runnable, Object
		
		//Thread2ó�� Runnable �������̽��� �������� �ʾƵ�
		//�̷��� �͸�޼ҵ带 ��� ���ٽ����� ���� ������ �� �ִ�
		Runnable target = () -> { // () -> {} ������ �ʵ��� �ּҰ�
		// ()�� run()�ε� �̸��� �ϳ� �ۿ� �����ϱ� �͸�޼ҵ�� ���� ����
		// ()->{} ()�� �޼ҵ带 {}���⼭ �����ϴ°Ŵϱ�
		// ()�� �޼ҵ尡 ������ �Ǹ� {}���� �ȿ� �ִ� �ڵ尡 ������ �ȴ�
		// () -> {} - �Ҵ��(������) �ʵ��� �ּҰ�
		//���� �� �� ���� �̷��� �ؾ���
		//�� ���� �� ���� Thread2 thread = () -> {}; �̷��� �ص� ��
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName());
				try {Thread.sleep(500);} catch (InterruptedException e) {;}
			}
		};
		
		//Thread�� ��ӹ��� ���� ���� ������ start �� ��
		//�׷��� Thread ��ü�� ���� ��üȭ ���ش�
//		Thread t1 = new Thread(target, "?");
//		Thread t2 = new Thread(target, "!");
		//currentThread�� ������ ������ �̸����� ����� �ȴ�
		
		//��Ƽ������� 2���� �ڿ��� ����
//		t2.start();
		
//		try {t2.join();} catch (InterruptedException e) {;}
		//t2�� ���� �����Ų��(�ٸ� �ڿ��� t2 ���� ������ ���� x)
		//ó�� ��ΰ� ������ �Ʒ����̱� ������ start�� ���� �ΰ� join�� �ɸ� �Ȱɸ���
		//t2.start() t2.join() t1.start() ������ �ؾ� t2�� ���� ������ t1�� wait ����	
		
//		t1.start();
		//t2�� join�� �ɾ�����Ƿ� t1�� ������ t2�� ����ȴ�
		
		//Thread�� ���ξ����带 �켱���� ó���Ѵ�
		//�׷��� �� �켱������ �������� ���̱� ������ ������ �������� �ʴ�
		System.out.println("�ȳ�");
		
		//2���� ������� �޸𸮿� �Ҵ�
		Thread1 t1 = new Thread1("��");
		Thread1 t2 = new Thread1("��");
		//t1�� t2���� �����ǵ� �޼ҵ� run�� �ִ�
		//���� ��Ƽ������� �̷��� �ϴ°� �ƴ� -> Thread ��ü �ϳ��� ��Ƽ�����带 �����ؾ���
		//�׷��߸� Thread ��ü �ϳ��� �ִ� run�� ���� ��ü�� �����ؼ� ��Ƽ�����带 Ȯ���� ������ �� ����
		
		//start()�� Thread�� ��ü
		//Thread�� �ִ� ���� �������߱� ������ t1�� t2 ��ü�� �ִ� run��
		//�츮�� ���� run���� start�� �ȴ�
		t1.start();
		t2.start();
		//�ϳ��ϳ� ���� ����ȴ� -> ��Ƽ������
		//�׷��� �ӵ��� �ʹ� ���� ������ ��µȴ�.
		//�̷��� Thread.sleep���� �ӵ��� ���缭 Ȯ���� �� �ִ�(try-catch ��������)
		//���� �ڢ� ���� �ڢ� �ڢ� -> �׷��� ������ �������� �ʴ�
		
//		t1.run();
//		t2.run();
	}
}














