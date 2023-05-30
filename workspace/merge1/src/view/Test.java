package view;

import java.io.IOException;

import dao.ChildDAO;
import domain.ChildVO;

public class Test {
	public static void main(String[] args) throws IOException{
		ChildDAO childDAO = new ChildDAO();
		
		childDAO.createFile(childDAO.getMergedArray());
	}
}
