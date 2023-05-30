package dao;

import java.io.IOException;

import domain.BoyVO;

public class BoyDAO {
	public BoyVO setObject(String boy) throws IOException{
		BoyVO boyVO = new BoyVO();
		String[] arBoyData = null;
		arBoyData = boy.split("\t");
		
		boyVO.setName(arBoyData[0]);
		boyVO.setRanking(Integer.valueOf(arBoyData[1]));
		boyVO.setNumber(Integer.valueOf(arBoyData[2].replaceAll(",", "")));
		
		return boyVO;
	}
//	public static void main(String[] args) throws IOException{
//		BoyDAO boyDAO = new BoyDAO();
//		System.out.println(boyDAO.setObject("Áö¿í	1	1000"));
//	}
}