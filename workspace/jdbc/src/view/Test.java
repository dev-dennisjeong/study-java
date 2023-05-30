package view;

import dao.BoardDAO;
import dao.ReplyDAO;
import dao.UserDAO;
import domain.BoardDTO;
import domain.BoardVO;
import domain.ReplyDTO;
import domain.ReplyVO;
import domain.UserVO;

public class Test {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		
//		ID중복검사
//		입력한 아이디에 중복이 없으면 true -> 사용 가능
//		중복있으면 false -> 사용 불가
//		if(userDAO.checkId("hds1234")) {
//			System.out.println("사용 가능한 아이디");
//			return;
//		}
//		System.out.println("중복된 아이디");
		//출력문으로 확인
		
//		System.out.println(userDAO.select(1L));
		
//		1 hds1234
//		19 lss1234
//		20 jbg1234
//		21 phgs1234
		
//		[실습] 메소드 전체 검증
//		회원가입
//		userVO에 유저의 담을 수 있는 모든 정보를 담아준다.
		UserVO userVO = new UserVO();
//		userVO.setUserIdentification("lss1234");
//		userVO.setUserName("이순신");
//		userVO.setUserPassword("1234");
//		userVO.setUserAddress("경기도 파주시");
//		userVO.setUserBirth("1545-04-08");
//		userVO.setUserEmail("lss1234@naver.com");
//		userVO.setUserPhone("01099998888");
//		userVO.setUserRecommenderId(null);
//		userVO.setUserIdentification("jbg1234");
//		userVO.setUserName("장보고");
//		userVO.setUserPassword("9999");
//		userVO.setUserAddress("서울특별시 관악구");
//		userVO.setUserBirth("2000-12-04");
//		userVO.setUserEmail("jbg1234@gmail.com");
//		userVO.setUserPhone("01099991234");
//		userVO.setUserRecommenderId("lss1234");
//		userVO.setUserIdentification("phgs1234");
//		userVO.setUserName("박혁거세");
//		userVO.setUserPassword("7890");
//		userVO.setUserAddress("강원도 강릉시");
//		userVO.setUserBirth("2010-12-04");
//		userVO.setUserEmail("phgs1234@gmail.com");
//		userVO.setUserPhone("01032291234");
//		userVO.setUserRecommenderId("lss1234");
//		userVO.setUserIdentification("aaa1234");
//		userVO.setUserName("이에이");
//		userVO.setUserPassword("1234");
//		userVO.setUserAddress("서울시");
//		userVO.setUserBirth("1999-09-09");
//		userVO.setUserEmail("aaa134@naver.com");
//		userVO.setUserPhone("01043214321");
//		userVO.setUserRecommenderId(null);
//		userDAO.insert(userVO);
		
//		ID중복검사를 통해서 회원가입이 가능한지 체크
//		중복이 없으면 회원가입
//		if(userDAO.checkId(userVO.getUserIdentification())) {
//			userDAO.insert(userVO);
//			System.out.println("회원가입 성공");
//		}else {
//			System.out.println("중복된 아이디");
//		}
		
//		로그인
		if(userDAO.login("aaa1234", "1234")) {
			System.out.println("로그인 성공");
			System.out.println(UserDAO.userId);
		}else {
			System.out.println("로그인 실패");
		}
//		팔로우
//		userDAO.follow(1L);
		System.out.println(userDAO.countFollower());
		
//		회원탈퇴(업데이트)
//		userDAO.withdrawal();
		
		//출력문으로 확인
		
//		아이디 찾기
//		userId에 담겨있는 휴대폰 번호로 그 userId의 아이디를 찾는다
//		System.out.println(userDAO.findIdentification("01099991234"));
		
//		비밀번호 변경
//		로그인한 상태에서 변경할 비밀번호를 입력한다
//		userDAO.updateUserPassword("3333");
		
//		로그인으로 비밀번호 변경되었는지 확인
//		if(userDAO.login("lss1234", "3333")) {
//			System.out.println("로그인 성공");
//			System.out.println(UserDAO.userId);
//		}else {
//			System.out.println("로그인 실패");
//		}
		
//		회원정보 조회
//		userVO에 담겨있는 회원의 전체 정보 조회
//		UserVO user = userDAO.select();
		
//		로그인한 상태에서 회원정보 수정
//		user.setUserNickname("거북왕");
//		userDAO.update(user);
		
//		추천 수
//		System.out.println(userDAO.getCountOfRecommenderId("jbg1234"));
		
//		나를 추천한 사람
//		ArrayList이므로 hashMap으로 조회
//		userDAO.selectRecommenders().stream().map(UserVO::toString).forEach(System.out::println);
		
//		내가 추천한 사람
		
//		if(userDAO.login("jbg1234", "9999")) {
//			System.out.println("로그인 성공");
//			System.out.println(UserDAO.userId);
//		}else {
//			System.out.println("로그인 실패");
//		}
		
//		로그인한 userId(나)가  추천한 사람을 user에 담아준다
//		UserVO user = userDAO.getMyRecommender();
//		추천한 사람이 있는지 검사
//		if(user != null) {
//			System.out.println(user);
			//있으면 그대로 user의 정보를 출력
//			
//		}else {
//			System.out.println("추천한 사람이 없습니다.");
//		}
		
//		회원 탈퇴
//		로그인한 상태에서 회원의 모든 정보를 삭제한다.
//		userDAO.delete();
//		==========================================================================
//		UserDAO userDAO = new UserDAO();
//		BoardDAO boardDAO = new BoardDAO();
//		BoardVO boardVO = new BoardVO();
		
//		순서대로 테스트 해보기
		
//		게시글 추가
		
//		로그인
//		if(userDAO.login("phgs1234", "7890")) {
//			System.out.println("로그인 성공");
//			System.out.println(UserDAO.userId); 
//			//로그인한 유저의 userId 확인용
//		}else {
//			System.out.println("로그인 실패");
//		}
		
//		로그인 성공시 게시글 추가
//		게시글의 제목
//		boardVO.setBoardTitle("알에서 태어나");
//		게시글의 내용
//		boardVO.setBoardContent("왕으로 강림하다.");
//		로그인한 유저의 userId
//		boardVO.setUserId(UserDAO.userId);
//		게시글 추가
//		boardDAO.insert(boardVO);
		
//		게시글 조회
//		게시글 추가가 잘되었는지도 같이 확인한다.
//		boardId가 1인 게시글 조회
//		System.out.println(boardDAO.select(1L));
		
//		boardDTO에 boardId가 1인 유저와 게시글의 전체 정보를 담아준다
//		BoardDTO boardDTO = boardDAO.select(2L);
		
//		게시글 수정
//		로그인한 userId와 게시글의 userId가 일치해야만 수정 가능
//		게시글 수정은 BoardVO타입으로 매개변수를 받아야 하기 때문에 바꿔줘야함
//		if(UserDAO.userId == boardDTO.getUserId()) {
//			boardDTO.setBoardTitle("두번째 수정된 제목");		
//			boardDAO.update(boardDTO.toBoardVO());
//			System.out.println("수정 완료"); //확인용
//		}else {
//			System.out.println("작성자만 수정이 가능합니다.");
//		}
		
//		게시글이 잘 수정되었는지 조회해서 확인
//		System.out.println(boardDAO.select(1L));
		
//		게시글 삭제
//		로그인한 userId와 게시글의 userId가 같으면 삭제 사능
//		if(UserDAO.userId == boardDTO.getUserId()) {
//			boardDAO.delete(boardDTO.getBoardId());
//		}else {
//			System.out.println("작성자만 삭제가 가능합니다.");
//		}
		
//		게시글이 잘 삭제되었는지 조회해서 확인
//		System.out.println(boardDAO.select(1L));
		
//		게시글 전체 조회
//		HashMap
//		ArrayList에 담긴 모든 유저의 정보와 모든 게시글 정보 출력
//		stream을 이용하여 ArrayList를 람다식으로 간결하게 처리 가능
//		map으로 BoardDTO를 toString 형식으로 변환
//		forEach문에 print문을 통해 모든 정보를 출력
//		boardDAO.selectAll().stream().map(dto -> dto.toString()).forEach(dto -> System.out.println(dto));
//		여기서 dto는 boardDAO.selectAll()
//		아래처럼 더 간단하게 줄일 수 있다
//		boardDAO.selectAll().stream().map(BoardDTO::toString).forEach(System.out::println);
//		어려우면 위에꺼로 먼저 연습
//		==========================================================================
//		UserDAO userDAO = new UserDAO();
//		BoardDAO boardDAO = new BoardDAO();
//		ReplyDAO replyDAO = new ReplyDAO();
//		
//		if(userDAO.login("phgs1234", "7890")) {
//			System.out.println("로그인 성공");
//		}else {
//			System.out.println("로그인 실패");
//		}
		
//		게시글 확인
//		BoardDTO boardDTO = boardDAO.select(3L);
//		System.out.println(boardDTO);
		
//		댓글 추가
//		ReplyVO replyVO = new ReplyVO();
//		replyVO.setBoardId(boardDTO.getBoardId());
//		replyVO.setReplyContent("두번째 댓글");
//		
//		replyDAO.insert(replyVO);
		
//		대댓글 추가
//		ReplyVO replyVO = new ReplyVO();
//		replyVO.setBoardId(boardDTO.getBoardId());
//		replyVO.setReplyContent("첫번째 댓글의 첫번째 대댓글");
//		replyVO.setReplyContent("첫번째 댓글의 두번째 대댓글");
//		replyVO.setReplyContent("두번째 댓글의 첫번째 대댓글");
//		replyVO.setReplyContent("두번째 댓글의 두번째 대댓글");
		
//		replyDAO.insert(replyVO, 10L);
		
//		댓글 전체 조회
//		replyDAO.selectAll().stream().map(ReplyDTO::toString).forEach(System.out::println);
		
//		댓글 하나 조회(대댓글까지)
//		replyDAO.selectAll().stream().filter(dto -> dto.getReplyGroup() == 9).map(ReplyDTO::toString).forEach(System.out::println);
		
//		대댓글 숨기고 댓글만 전체 조회
//		replyDAO.selectAll().stream().filter(dto -> dto.getReplyCount() != 0).map(ReplyDTO::toString).forEach(System.out::println);
		
//		대댓글 삭제
//		replyDAO.deleteReReply(11L);
		
		
//		댓글 삭제(대댓글까지 삭제)
//		replyDAO.deleteReply(10L);
		
//		댓글 수정
//		댓글을 조회해서 수정할 댓글의 replyId를 지정
//		replyVO타입으로 바꿔줌(댓글 수정은 replyVO을 매개변수로 받기 때문)
//		forEach문
//		실행할 문장이 2개이므로 {}를 씌여준다
//		vo.setReplyContent()로 수정할 댓글이 내용을 입력한다
//		vo에 담긴 수정된 댓글을 update메소드를 통해 수정해준다.
		
//		replyDAO.selectAll().stream().filter(dto -> dto.getReplyId() == 9).map(ReplyDTO::toReplyVO).forEach(vo -> {
//			vo.setReplyContent("수정된 첫번째 댓글");
//			replyDAO.update(vo);
//			System.out.println("수정이 완료되었습니다."); -> 확인용
//		});
		
		
	}
}































