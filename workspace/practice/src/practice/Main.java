package practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/test.txt", true));
		bufferedWriter.write("안녕"); 
		bufferedWriter.close();
	}
}
