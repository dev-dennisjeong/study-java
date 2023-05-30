package threadTest;

public class Thread2 implements Runnable{ //Runnable �������̽� ���
	//�������� �ֱ� ������ �ݵ�� run�� ������ �ؾ��Ѵ�
	//Runnable�� run �ۿ� ����. 
	//Runnable�� �Լ��� �������̽� -> run ������ �� ���� �� �� ����
	
	@Override
	public void run() {
		//�� �ڿ��� ������ ��ü�� �̸��� 10�� ���
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName());
			//currentThread -> Thread���� �� �ڿ��� ������ static �޼ҵ带 ������ �� �ִ�
			//Thread.currentThread�� Thread ��ü
			//Thread ��ü�� �� ������ ���� �̸��� �� �� ����
			//-> Thread���� �̸��� �����ϴ� �����ڰ� �ִ�
			//�� �����ڴ� super()�� �����ϸ� �ȴ�
			//�׷��� Runnable�� Thread�� ��ӹ��� ���� ����(Thread�� �ڽ��� �ƴ�)
			//�� �ڿ��� ������ ������ ��ü�� �̸��� getName()���� �����;��Ѵ�
			try {Thread.sleep(500);} catch (InterruptedException e) {;}
			//Thread�� ���� ���� �����Ƿ� Thread.sleep���� �������
		}
	}
}
