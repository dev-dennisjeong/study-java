package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


import dao.DBConnecter;
import dao.SalaryDAO;
import domain.SalaryVO;

public class Test {
   public static void main(String[] args) throws IOException{
      SalaryVO salaryVO = new SalaryVO();
      SalaryDAO salaryDAO = new SalaryDAO();
//
      salaryVO.setSalary(13800);
//      salaryVO.setIncomeTax(1000000);
//      salaryVO.setNetPay(9_000_000);
//      
//      salaryDAO.insert(salaryVO);
      
//      salaryDAO.update(salaryVO);
      
//      salaryDAO.delete(salaryVO);
//      String content = salaryDAO.showList(salaryVO);
//      String temp = null;
//      System.out.println(content);
//      temp = content.charAt(content.length() - 1) == '\n' ? "" : "\n";
//      System.out.println(temp);
//      temp += salaryVO.toString();
//      System.out.println(temp);
          
    
   }
}
