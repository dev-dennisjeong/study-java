package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import domain.ChildVO;

public class ChildDAO {

   public ArrayList<ChildVO> merge() throws IOException {
      BufferedReader bufferedReader = null;

      ArrayList<ChildVO> children = new ArrayList<ChildVO>();
      
      BoyDAO boyDAO = new BoyDAO();
      GirlDAO girlDAO = new GirlDAO();

      String line = "";

      bufferedReader = DBConnecter.getReader(DBConnecter.BOYPATH);

      while ((line = bufferedReader.readLine()) != null) {
         children.add(boyDAO.setObject(line));
      }

      bufferedReader = DBConnecter.getReader(DBConnecter.GIRLPATH);

      while ((line = bufferedReader.readLine()) != null) {
         children.add(girlDAO.setObject(line));
      }

      bufferedReader.close();
      
      
      return children;
   }
   
   public ArrayList<Integer> sortAmount(ArrayList<ChildVO> children) {
	   ArrayList<Integer> temp = null;
	   HashSet<Integer> dataHash = new HashSet<Integer>();
	   children.stream().map(v -> v.getNumber()).forEach(dataHash::add);
	   
	   temp = new ArrayList<Integer>(dataHash);
	   
	   temp.sort(Collections.reverseOrder());
	   
	   return temp;
   }

   public ArrayList<ChildVO> getMergedArray() throws IOException {
      ArrayList<Integer> temp;
      ArrayList<ChildVO> children = merge();
      ArrayList<ChildVO> result = new ArrayList<ChildVO>();
      
      
      
      temp = sortAmount(children);

      int index = 1;
      int count = 0;

      for (int i : temp) {

         for (int j = 0; j < children.size(); j++) {

            if (i == children.get(j).getNumber()) {
               children.get(j).setRanking(index - count);
               result.add(children.get(j));
               
               count++;
               index++;
               
               children.remove(j);
            }

         }

         count = 0;
      }
      
      return result;
   }


   public void createFile(ArrayList<ChildVO> children) throws IOException {
      BufferedWriter bw = DBConnecter.getWriter(DBConnecter.CHILDPATH);

      String temp = "";
      
      for (ChildVO childVO : children) {
         temp += childVO.toString() + "\n";
      }
      
      bw.write(temp);

      if (bw != null) {
         bw.close();
      }
   }
}