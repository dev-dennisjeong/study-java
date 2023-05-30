package threadTask;

public class Animal {

//   ������ �ڿ��� animal�� ����
	public void makeSounds(Runnable animal) {
		String[] sounds = { "����", "�ſ�", "����" };
		Thread[] threads = new Thread[sounds.length];

//      �� �����忡 ������ �ڿ��� �����ϵ��� �����ϰ�, �� �̸��� �����Ҹ��� �����Ѵ�.
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(animal, sounds[i]);
		}

//         �� �����带 �����ٸ� �� �� threads[0]�� threads[1] 2���� �����尡 ���ÿ� start �ǰ�, 
//			������ �������� threads[2]�� threads[1]�� join�� �ɷ��ֱ� ������ threads[1]�� ������ ����ȴ�.
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
			if (i != 0) {
				try {threads[i].join();} catch (InterruptedException e) {;}
			}
		}
	}

}