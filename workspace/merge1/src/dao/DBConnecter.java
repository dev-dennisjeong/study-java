package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBConnecter {
	 public static final String BOYPATH = "boy.txt";
	 public static final String GIRLPATH = "girl.txt";
	 public static final String CHILDPATH = "child.txt";
	 
	   
	   public static BufferedWriter getWriter(String path) throws IOException {
	      return new BufferedWriter(new FileWriter(path));
	   }
	   
//	   public static BufferedWriter getGirlWriter() throws IOException {
//		      return new BufferedWriter(new FileWriter(GIRLPATH));
//		   }
//	   
//	   public static BufferedWriter getChildWriter() throws IOException {
//		      return new BufferedWriter(new FileWriter(GIRLPATH));
//		   }
	   
	   public static BufferedWriter getAppend(String path) throws IOException {
	      return new BufferedWriter(new FileWriter(path, true));
	   }
	   
//	   public static BufferedWriter getGirlAppend() throws IOException {
//		      return new BufferedWriter(new FileWriter(GIRLPATH, true));
//		   }
//	   
//	   public static BufferedWriter getChildAppend() throws IOException {
//		      return new BufferedWriter(new FileWriter(CHILDPATH, true));
//		   }
	   
	   public static BufferedReader getReader(String path) throws IOException {
	      return new BufferedReader(new FileReader(path));
	   }
	   
//	   public static BufferedReader getGirlReader() throws IOException {
//		      return new BufferedReader(new FileReader(GIRLPATH));
//		   }
//	   
//	   public static BufferedReader getChildReader() throws IOException {
//		      return new BufferedReader(new FileReader(CHILDPATH));
//		   }
}
