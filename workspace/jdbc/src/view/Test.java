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
		
//		ID�ߺ��˻�
//		�Է��� ���̵� �ߺ��� ������ true -> ��� ����
//		�ߺ������� false -> ��� �Ұ�
//		if(userDAO.checkId("hds1234")) {
//			System.out.println("��� ������ ���̵�");
//			return;
//		}
//		System.out.println("�ߺ��� ���̵�");
		//��¹����� Ȯ��
		
//		System.out.println(userDAO.select(1L));
		
//		1 hds1234
//		19 lss1234
//		20 jbg1234
//		21 phgs1234
		
//		[�ǽ�] �޼ҵ� ��ü ����
//		ȸ������
//		userVO�� ������ ���� �� �ִ� ��� ������ ����ش�.
		UserVO userVO = new UserVO();
//		userVO.setUserIdentification("lss1234");
//		userVO.setUserName("�̼���");
//		userVO.setUserPassword("1234");
//		userVO.setUserAddress("��⵵ ���ֽ�");
//		userVO.setUserBirth("1545-04-08");
//		userVO.setUserEmail("lss1234@naver.com");
//		userVO.setUserPhone("01099998888");
//		userVO.setUserRecommenderId(null);
//		userVO.setUserIdentification("jbg1234");
//		userVO.setUserName("�庸��");
//		userVO.setUserPassword("9999");
//		userVO.setUserAddress("����Ư���� ���Ǳ�");
//		userVO.setUserBirth("2000-12-04");
//		userVO.setUserEmail("jbg1234@gmail.com");
//		userVO.setUserPhone("01099991234");
//		userVO.setUserRecommenderId("lss1234");
//		userVO.setUserIdentification("phgs1234");
//		userVO.setUserName("�����ż�");
//		userVO.setUserPassword("7890");
//		userVO.setUserAddress("������ ������");
//		userVO.setUserBirth("2010-12-04");
//		userVO.setUserEmail("phgs1234@gmail.com");
//		userVO.setUserPhone("01032291234");
//		userVO.setUserRecommenderId("lss1234");
//		userVO.setUserIdentification("aaa1234");
//		userVO.setUserName("�̿���");
//		userVO.setUserPassword("1234");
//		userVO.setUserAddress("�����");
//		userVO.setUserBirth("1999-09-09");
//		userVO.setUserEmail("aaa134@naver.com");
//		userVO.setUserPhone("01043214321");
//		userVO.setUserRecommenderId(null);
//		userDAO.insert(userVO);
		
//		ID�ߺ��˻縦 ���ؼ� ȸ�������� �������� üũ
//		�ߺ��� ������ ȸ������
//		if(userDAO.checkId(userVO.getUserIdentification())) {
//			userDAO.insert(userVO);
//			System.out.println("ȸ������ ����");
//		}else {
//			System.out.println("�ߺ��� ���̵�");
//		}
		
//		�α���
		if(userDAO.login("aaa1234", "1234")) {
			System.out.println("�α��� ����");
			System.out.println(UserDAO.userId);
		}else {
			System.out.println("�α��� ����");
		}
//		�ȷο�
//		userDAO.follow(1L);
		System.out.println(userDAO.countFollower());
		
//		ȸ��Ż��(������Ʈ)
//		userDAO.withdrawal();
		
		//��¹����� Ȯ��
		
//		���̵� ã��
//		userId�� ����ִ� �޴��� ��ȣ�� �� userId�� ���̵� ã�´�
//		System.out.println(userDAO.findIdentification("01099991234"));
		
//		��й�ȣ ����
//		�α����� ���¿��� ������ ��й�ȣ�� �Է��Ѵ�
//		userDAO.updateUserPassword("3333");
		
//		�α������� ��й�ȣ ����Ǿ����� Ȯ��
//		if(userDAO.login("lss1234", "3333")) {
//			System.out.println("�α��� ����");
//			System.out.println(UserDAO.userId);
//		}else {
//			System.out.println("�α��� ����");
//		}
		
//		ȸ������ ��ȸ
//		userVO�� ����ִ� ȸ���� ��ü ���� ��ȸ
//		UserVO user = userDAO.select();
		
//		�α����� ���¿��� ȸ������ ����
//		user.setUserNickname("�źϿ�");
//		userDAO.update(user);
		
//		��õ ��
//		System.out.println(userDAO.getCountOfRecommenderId("jbg1234"));
		
//		���� ��õ�� ���
//		ArrayList�̹Ƿ� hashMap���� ��ȸ
//		userDAO.selectRecommenders().stream().map(UserVO::toString).forEach(System.out::println);
		
//		���� ��õ�� ���
		
//		if(userDAO.login("jbg1234", "9999")) {
//			System.out.println("�α��� ����");
//			System.out.println(UserDAO.userId);
//		}else {
//			System.out.println("�α��� ����");
//		}
		
//		�α����� userId(��)��  ��õ�� ����� user�� ����ش�
//		UserVO user = userDAO.getMyRecommender();
//		��õ�� ����� �ִ��� �˻�
//		if(user != null) {
//			System.out.println(user);
			//������ �״�� user�� ������ ���
//			
//		}else {
//			System.out.println("��õ�� ����� �����ϴ�.");
//		}
		
//		ȸ�� Ż��
//		�α����� ���¿��� ȸ���� ��� ������ �����Ѵ�.
//		userDAO.delete();
//		==========================================================================
//		UserDAO userDAO = new UserDAO();
//		BoardDAO boardDAO = new BoardDAO();
//		BoardVO boardVO = new BoardVO();
		
//		������� �׽�Ʈ �غ���
		
//		�Խñ� �߰�
		
//		�α���
//		if(userDAO.login("phgs1234", "7890")) {
//			System.out.println("�α��� ����");
//			System.out.println(UserDAO.userId); 
//			//�α����� ������ userId Ȯ�ο�
//		}else {
//			System.out.println("�α��� ����");
//		}
		
//		�α��� ������ �Խñ� �߰�
//		�Խñ��� ����
//		boardVO.setBoardTitle("�˿��� �¾");
//		�Խñ��� ����
//		boardVO.setBoardContent("������ �����ϴ�.");
//		�α����� ������ userId
//		boardVO.setUserId(UserDAO.userId);
//		�Խñ� �߰�
//		boardDAO.insert(boardVO);
		
//		�Խñ� ��ȸ
//		�Խñ� �߰��� �ߵǾ������� ���� Ȯ���Ѵ�.
//		boardId�� 1�� �Խñ� ��ȸ
//		System.out.println(boardDAO.select(1L));
		
//		boardDTO�� boardId�� 1�� ������ �Խñ��� ��ü ������ ����ش�
//		BoardDTO boardDTO = boardDAO.select(2L);
		
//		�Խñ� ����
//		�α����� userId�� �Խñ��� userId�� ��ġ�ؾ߸� ���� ����
//		�Խñ� ������ BoardVOŸ������ �Ű������� �޾ƾ� �ϱ� ������ �ٲ������
//		if(UserDAO.userId == boardDTO.getUserId()) {
//			boardDTO.setBoardTitle("�ι�° ������ ����");		
//			boardDAO.update(boardDTO.toBoardVO());
//			System.out.println("���� �Ϸ�"); //Ȯ�ο�
//		}else {
//			System.out.println("�ۼ��ڸ� ������ �����մϴ�.");
//		}
		
//		�Խñ��� �� �����Ǿ����� ��ȸ�ؼ� Ȯ��
//		System.out.println(boardDAO.select(1L));
		
//		�Խñ� ����
//		�α����� userId�� �Խñ��� userId�� ������ ���� ���
//		if(UserDAO.userId == boardDTO.getUserId()) {
//			boardDAO.delete(boardDTO.getBoardId());
//		}else {
//			System.out.println("�ۼ��ڸ� ������ �����մϴ�.");
//		}
		
//		�Խñ��� �� �����Ǿ����� ��ȸ�ؼ� Ȯ��
//		System.out.println(boardDAO.select(1L));
		
//		�Խñ� ��ü ��ȸ
//		HashMap
//		ArrayList�� ��� ��� ������ ������ ��� �Խñ� ���� ���
//		stream�� �̿��Ͽ� ArrayList�� ���ٽ����� �����ϰ� ó�� ����
//		map���� BoardDTO�� toString �������� ��ȯ
//		forEach���� print���� ���� ��� ������ ���
//		boardDAO.selectAll().stream().map(dto -> dto.toString()).forEach(dto -> System.out.println(dto));
//		���⼭ dto�� boardDAO.selectAll()
//		�Ʒ�ó�� �� �����ϰ� ���� �� �ִ�
//		boardDAO.selectAll().stream().map(BoardDTO::toString).forEach(System.out::println);
//		������ �������� ���� ����
//		==========================================================================
//		UserDAO userDAO = new UserDAO();
//		BoardDAO boardDAO = new BoardDAO();
//		ReplyDAO replyDAO = new ReplyDAO();
//		
//		if(userDAO.login("phgs1234", "7890")) {
//			System.out.println("�α��� ����");
//		}else {
//			System.out.println("�α��� ����");
//		}
		
//		�Խñ� Ȯ��
//		BoardDTO boardDTO = boardDAO.select(3L);
//		System.out.println(boardDTO);
		
//		��� �߰�
//		ReplyVO replyVO = new ReplyVO();
//		replyVO.setBoardId(boardDTO.getBoardId());
//		replyVO.setReplyContent("�ι�° ���");
//		
//		replyDAO.insert(replyVO);
		
//		���� �߰�
//		ReplyVO replyVO = new ReplyVO();
//		replyVO.setBoardId(boardDTO.getBoardId());
//		replyVO.setReplyContent("ù��° ����� ù��° ����");
//		replyVO.setReplyContent("ù��° ����� �ι�° ����");
//		replyVO.setReplyContent("�ι�° ����� ù��° ����");
//		replyVO.setReplyContent("�ι�° ����� �ι�° ����");
		
//		replyDAO.insert(replyVO, 10L);
		
//		��� ��ü ��ȸ
//		replyDAO.selectAll().stream().map(ReplyDTO::toString).forEach(System.out::println);
		
//		��� �ϳ� ��ȸ(���۱���)
//		replyDAO.selectAll().stream().filter(dto -> dto.getReplyGroup() == 9).map(ReplyDTO::toString).forEach(System.out::println);
		
//		���� ����� ��۸� ��ü ��ȸ
//		replyDAO.selectAll().stream().filter(dto -> dto.getReplyCount() != 0).map(ReplyDTO::toString).forEach(System.out::println);
		
//		���� ����
//		replyDAO.deleteReReply(11L);
		
		
//		��� ����(���۱��� ����)
//		replyDAO.deleteReply(10L);
		
//		��� ����
//		����� ��ȸ�ؼ� ������ ����� replyId�� ����
//		replyVOŸ������ �ٲ���(��� ������ replyVO�� �Ű������� �ޱ� ����)
//		forEach��
//		������ ������ 2���̹Ƿ� {}�� �����ش�
//		vo.setReplyContent()�� ������ ����� ������ �Է��Ѵ�
//		vo�� ��� ������ ����� update�޼ҵ带 ���� �������ش�.
		
//		replyDAO.selectAll().stream().filter(dto -> dto.getReplyId() == 9).map(ReplyDTO::toReplyVO).forEach(vo -> {
//			vo.setReplyContent("������ ù��° ���");
//			replyDAO.update(vo);
//			System.out.println("������ �Ϸ�Ǿ����ϴ�."); -> Ȯ�ο�
//		});
		
		
	}
}































