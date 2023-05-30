package fileTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {

	public void test() throws InterruptedException {
		Thread.sleep(1000);
	}

	public static void main(String[] args) throws IOException {

//		절대 경로: 어디에서 작성해도 찾아갈 수 있는 경로, c:/a/b/
//		상대 경로: 현재 위치에 따라 변경되는 경로, ../a/b

		// 자바에서 현재 경로(./)는 project 폴더다(./ 생략 가능)
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/test.txt", true));
//		bufferedWriter.write("안녕"); 
//		bufferedWriter.close();
		
		BufferedReader bufferedReader = null;
		bufferedReader = new BufferedReader(new FileReader("src/test.txt"));
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		bufferedReader.close();
		
//		성능 저하 및 다양한 결함이 발생하여, 9버전 이후 부터는 사용하지 말고 close()를 쓰자!
//		System.gc();
//		System.runFinalization();

//		File file = new File("src/test.txt");
//		if (file.exists()) {
//			file.delete();
//		}
	}
}
