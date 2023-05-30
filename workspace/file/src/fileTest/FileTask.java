package fileTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTask {
   public static void main(String[] args) throws IOException{
      String[] games = {"배틀그라운드", "롤", "오버워치", "로스트아크", "메이플스토리", "스타크래프트"};
      
//      배열로 파일에 출력하고 전체 내용을 가져와서 콘솔에 출력하기
//      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/game.txt", true));
//      for (String game : games) {
//         bufferedWriter.write(game + "\n");
//      }
//      bufferedWriter.close();
      
//      BufferedReader bufferedReader = null;
//      String line = null;
//      try {
//         bufferedReader = new BufferedReader(new FileReader("src/game.txt"));
//         while((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
//         }
//      } catch (FileNotFoundException e) {
//         System.out.println("잘못된 경로");
//         e.printStackTrace();
//      } finally {
//         if(bufferedReader != null) {
//            bufferedReader.close();
//         }
//      }
      
//      로스트 아크를 피파 2002로 변경
//      BufferedReader bufferedReader = null;
//      String line = null, temp = "";
//      final String TARGET = "로스트아크";
//      
//      try {
//         bufferedReader = new BufferedReader(new FileReader("src/game.txt"));
//         while((line = bufferedReader.readLine()) != null) {
//            if(line.equals(TARGET)) {
//               temp += "피파2002\n";
//               continue;
//            }
//            temp += line + "\n";
//         }
//         
//         BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/game.txt"));
//         bufferedWriter.write(temp);
//         bufferedWriter.close();
//         
//      } catch (FileNotFoundException e) {
//         System.out.println("잘못된 경로");
//         e.printStackTrace();
//      } finally {
//         if(bufferedReader != null) {
//            bufferedReader.close();
//         }
//      }
      
//      피파2002 삭제
      BufferedReader bufferedReader = null;
      String line = null, temp = "";
      final String TARGET = "피파2002";
      
      try {
         bufferedReader = new BufferedReader(new FileReader("src/game.txt"));
         while((line = bufferedReader.readLine()) != null) {
            if(line.equals(TARGET)) {
               continue;
            }
            temp += line + "\n";
         }
         
         BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/game.txt"));
         bufferedWriter.write(temp);
         bufferedWriter.close();
         
      } catch (FileNotFoundException e) {
         System.out.println("잘못된 경로");
         e.printStackTrace();
      } finally {
         if(bufferedReader != null) {
            bufferedReader.close();
         }
      }
      
      
   }
}
















