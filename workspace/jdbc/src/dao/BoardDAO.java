package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.BoardDTO;
import domain.BoardVO;

public class BoardDAO {
	public Connection connection; //���� ��ü
	public PreparedStatement preparedStatement; //���� ���� ��ü
	public ResultSet resultSet; //��� ���̺� ��ü
	
//	�Խñ� �߰�, ����, ������ �α����� ���¿����� ����!!
	
//	�Խñ� �߰�
//	�Խñۿ� �߰��� �� �ִ� ��� ������ �Է��� �� �־����
// 	�Խ����� ��� ������ �ѹ��� �޾ƿ��� ���ؼ� VO��ü�� ���
	public void insert(BoardVO boardVO) {
		//�Խñ��� �߰��� ���� ��ϰ� ���ÿ� ������Ʈ�� �Ǵ� ���̹Ƿ�
		//�� �÷��� ������ ���� �ð��� SYSDATE�� �־��ش�.
		String query = "INSERT INTO TBL_BOARD"
				+ "(BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, BOARD_UPDATE_DATE, USER_ID) "
				+ "VALUES(SEQ_BOARD.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?)";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, boardVO.getBoardTitle());
			preparedStatement.setString(2, boardVO.getBoardContent());
			//ȭ�鿡�� �α��� �Ǿ� �ִ� user�� ���̵� �޾ƿ� ����ش�
			preparedStatement.setLong(3, boardVO.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
//	�Խñ� ��ȸ
//	BoardDTO Ÿ������ ���ִ� ����
//	���� Board ���̺�� User���̺��� �ϳ��� ���̺�
//	�̻������� ���ֱ� ���� ����ȭ�� ���� ���̺��� �и�
//	User ���̺��� PK ���� �ܷ�Ű�� ������ Board���̺��� ���� �и��ؼ� ����
//	�Խñ� �󼼺��⸦ ������ �Խñ��� ������ �Խñ��� �ۼ��� user�� �־����
//	user �κп��� � ������ �� �� �𸣱� ������ user�� ������ �� ����;���
//	������ user�� �� ���̺��� ��� ������ �������� ���� User���̺�� Board ���̺��� �����ؼ� ��ȸ
//	BoardVO������ user�� ������ ������ �� �����Ƿ� ���⼭ BoardDTO ��ü�� domain���� ���� ����Ѵ�
//	��DTO - ȭ�鿡 �����ϴ� �������θ� �� �� ����Ѵ�
//	��VO�� ������ ���̺�� �÷��� 1��1�� �Ǿ��־����
	public BoardDTO select(Long boardId) {
		String query = "SELECT BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, "
				+ " BOARD_UPDATE_DATE, U.USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, "
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, USER_GENDER, "
				+ " USER_RECOMMENDER_ID "
				+ "FROM TBL_USER U JOIN TBL_BOARD B "
				+ "ON U.USER_ID = B.USER_ID AND BOARD_ID = ?";
		
		BoardDTO boardDTO = null;
		int index = 0;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, boardId);
			resultSet = preparedStatement.executeQuery();
			//boardId�� �ִٸ�(�Խñ��� �ִٸ�)
			//DTO�� ��� ������ ����ش�.
			if(resultSet.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setBoardId(resultSet.getLong(++index)); //���� �ű�� �����Ƽ� ++index
				boardDTO.setBoardTitle(resultSet.getString(++index));
				boardDTO.setBoardContent(resultSet.getString(++index));
				boardDTO.setBoardRegisterDate(resultSet.getString(++index));
				boardDTO.setBoardUpdateDate(resultSet.getString(++index));
				boardDTO.setUserId(resultSet.getLong(++index));
				boardDTO.setUserIdentification(resultSet.getString(++index));
				boardDTO.setUserName(resultSet.getString(++index));
				boardDTO.setUserPassword(resultSet.getString(++index));
				boardDTO.setUserPhone(resultSet.getString(++index));
				boardDTO.setUserNickname(resultSet.getString(++index));
				boardDTO.setUserEmail(resultSet.getString(++index));
				boardDTO.setUserAddress(resultSet.getString(++index));
				boardDTO.setUserBirth(resultSet.getString(++index));
				boardDTO.setUserGender(resultSet.getString(++index));
				boardDTO.setUserRecommenderId(resultSet.getString(++index));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return boardDTO;
	}
//	�Խñ� ����
	public void update(BoardVO boardVO) {
		//������ �� �ִ� �÷����� �Խñ� ����� ����
		//���� ��¥�� ���� ��¥�̹Ƿ� SYSDATE
		String query = "UPDATE TBL_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_UPDATE_DATE = SYSDATE "
				+ "WHERE BOARD_ID = ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, boardVO.getBoardTitle());
			preparedStatement.setString(2, boardVO.getBoardContent());
			preparedStatement.setLong(3, boardVO.getBoardId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
//	�Խñ� ����
	public void delete(Long boardId) {
		String query = "DELETE FROM TBL_BOARD WHERE BOARD_ID = ?";
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, boardId);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
//	�Խñ� ��ü ��ȸ
//	�Խñ��� ���� ���ϼ��� ������ ArrayList
//	�Խñ� ��ȸ�� ���
//	�Խñ��� ��� ������ user�� ��� ���� �����;��ϴϱ� BoardDTO Ÿ��
	public ArrayList<BoardDTO> selectAll(){
		String query = "SELECT BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, "
				+ " BOARD_UPDATE_DATE, U.USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, "
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, USER_GENDER, "
				+ " USER_RECOMMENDER_ID "
				+ "FROM TBL_USER U JOIN TBL_BOARD B "
				+ "ON U.USER_ID = B.USER_ID";
		
		
		BoardDTO boardDTO = null;
		ArrayList<BoardDTO> boards = new ArrayList<BoardDTO>();
		int index = 0;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			//�Խñ��� �� ������ �𸣴� �� �� �ݺ��� ������ ������ �𸥴�
			//�׷��� while������ false(�Խñ��� ���̻� ���� ��)���� �ݺ�
			while(resultSet.next()) {
				//user�� �ۼ��� �Խñ��� �ִٸ� BoardDTO�� �� user�� �� �Խñ��� ��� ������ ����ش�
				index = 0;
				boardDTO = new BoardDTO();
				boardDTO.setBoardId(resultSet.getLong(++index));
				boardDTO.setBoardTitle(resultSet.getString(++index));
				boardDTO.setBoardContent(resultSet.getString(++index));
				boardDTO.setBoardRegisterDate(resultSet.getString(++index));
				boardDTO.setBoardUpdateDate(resultSet.getString(++index));
				boardDTO.setUserId(resultSet.getLong(++index));
				boardDTO.setUserIdentification(resultSet.getString(++index));
				boardDTO.setUserName(resultSet.getString(++index));
				boardDTO.setUserPassword(resultSet.getString(++index));
				boardDTO.setUserPhone(resultSet.getString(++index));
				boardDTO.setUserNickname(resultSet.getString(++index));
				boardDTO.setUserEmail(resultSet.getString(++index));
				boardDTO.setUserAddress(resultSet.getString(++index));
				boardDTO.setUserBirth(resultSet.getString(++index));
				boardDTO.setUserGender(resultSet.getString(++index));
				boardDTO.setUserRecommenderId(resultSet.getString(++index));
				boards.add(boardDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return boards;
	}
}




















