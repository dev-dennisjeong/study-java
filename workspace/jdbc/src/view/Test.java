package view;

import dao.UserDAO;
import domain.UserVO;

public class Test {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		UserVO userVO = new UserVO();

//		아이디 중복 확인
//		userDAO.checkId는 boolean 타입
//		if문에서 ID의 중복을 확인
//		만약 중복이라면 false를 리턴, 중복이 아니라면 true
//		if (userDAO.checkId("hds1234")) {
//			System.out.println("사용가능한 아이디");
//		} else {
//			System.out.println("중복된 아이디");
//		}

//		회원가입
//		아이디 중복이 없으면 회원 가입 진행
//		userVO에 userVO의 모든 정보를 담아준다.
//		단, DEFAULT 값이 있거나 NOT NULL이 아니면 안넣어줘도 되긴함
//		userVO.setUserIdentification("lss1234");
//		userVO.setUserName("이순신");
//		userVO.setUserPassword("1234");
//		userVO.setUserAddress("경기도 파주시");
//		userVO.setUserBirth("1545-04-08");
//		userVO.setUserEmail("lss1234@naver.com");
//		userVO.setUserPhone("01099998888");
//		userVO.setUserRecommenderId("hds1234");
//		userDAO.insert(userVO);
//		userVO.setUserIdentification("jbg1234");
//		userVO.setUserName("장보고");
//		userVO.setUserPassword("9999");
//		userVO.setUserAddress("서울특별시 관악구");
//		userVO.setUserBirth("2000-12-04");
//		userVO.setUserEmail("jbg1234@gmail.com");
//		userVO.setUserPhone("01099991234");
//		userDAO.insert(userVO);

//		로그인
		if (userDAO.login("lss1234", "5555")) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}

//		아이디 찾기
//		System.out.println(userDAO.findIdentification("01099998888"));
		
//		비밀번호 변경
//		userDAO.updateUserPassword("6666");
		
//		로그인으로 확인
		if (userDAO.login("lss1234", "6666")) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		

	}
}
