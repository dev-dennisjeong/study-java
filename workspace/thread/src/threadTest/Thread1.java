package threadTest;

//�ϳ��� �����͸� �ܺο��� �޾Ƽ� 2���� ��ü�� ������ ���� ��ü ���� ����ؾ��� �����͸�
//����� �� �ְ� ��Ƽ�� ������.

//Thread Ŭ���� ���(�ǹ����� �߾Ⱦ�)
public class Thread1 extends Thread{
	//�޾ƾ� �� ������
	public String data;
	//�⺻ ������
	public Thread1() {;}
	//�ʱ�ȭ ������
	public Thread1(String data) {
						//�ܺο��� ���޹��� �� �����͸� 10�� ���
						//�̰��� �ϳ��� ������
		super();
		this.data = data;
	}
	
	//Thread �ȿ� �ִ� run �޼ҵ� ������
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {//data�� 10�� ����ϴ� �ݺ���
			System.out.println(data);
			try {sleep(1000);} catch (InterruptedException e) {;}
			//Thread ����� �޾Ƽ� ������ �� �ʵ忡 �ִ� �޼ҵ��̱� ������
			//���� Thread.sleep�̶�� �� �ʿ����(�θ��ʵ带 �״�� ������)
		}
	}
}
//Thread1�̶�� ��ü �ϳ��� �ϳ��� �����尡 �ȴ�.
//run�̶�� �޼ҵ尡 �����ǵ� �ʵ带 ���� �ִ� ��ü�� Thread�̰�,
//Thread1�� Thread�� ��ü�� �Ǿ���.
