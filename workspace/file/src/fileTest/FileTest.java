package fileTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {

	public void test() throws InterruptedException {
		Thread.sleep(1000);
	}

	public static void main(String[] args) throws IOException {

//		���� ���: ��𿡼� �ۼ��ص� ã�ư� �� �ִ� ���, c:/a/b/
//		��� ���: ���� ��ġ�� ���� ����Ǵ� ���, ../a/b

		// �ڹٿ��� ���� ���(./)�� project ������(./ ���� ����)
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/test.txt", true));
//		bufferedWriter.write("�ȳ�"); 
//		bufferedWriter.close();
		
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader("src/test.txt"));
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		bufferedReader.close();
		
//		���� ���� �� �پ��� ������ �߻��Ͽ�, 9���� ���� ���ʹ� ������� ���� close()�� ����!
//		System.gc();
//		System.runFinalization();

//		File file = new File("src/test.txt");
//		if (file.exists()) {
//			file.delete();
//		}
	}
}
