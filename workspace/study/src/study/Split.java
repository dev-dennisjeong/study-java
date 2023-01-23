package study;

import java.util.Iterator;

public class Split {
	public static void main(String[] args) {
		String string = null;
		String[] temp = null;
		string = "¾È³ç + ÇÏ + ¼¼¿ä?";
		temp = string.split("");
		for (int i = 0; i < temp.length; i++) {
			System.out.print(temp[i]);
		}
		System.out.println();
		System.out.println(temp[0]);
		System.out.println(string);
	}
}
