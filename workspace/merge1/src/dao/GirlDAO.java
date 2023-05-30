package dao;

import java.io.IOException;

import domain.GirlVO;

public class GirlDAO {
	public GirlVO setObject(String girl) throws IOException{
		GirlVO girlVO = new GirlVO();
		String[] arGirlData = null;
		arGirlData = girl.split("\t");
		
		girlVO.setName(arGirlData[0]);
		girlVO.setRanking(Integer.valueOf(arGirlData[1]));
		girlVO.setNumber(Integer.valueOf(arGirlData[2].replaceAll(",", "")));
		
		return girlVO;
	}
}