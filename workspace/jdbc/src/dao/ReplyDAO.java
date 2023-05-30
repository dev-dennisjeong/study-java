package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.ReplyDTO;
import domain.ReplyVO;

public class ReplyDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
//	��� �߰�
//	�Խñۿ� ����� �߰��Ѵ�
	public void insert(ReplyVO replyVO) {
		//reply ���̺� reply�� ��� ������ ��´�
		//replyId�� replyGroup�� ���� ��
		//��) boardId�� 1���� �Խñۿ� replyId�� 1���� ����� �޾Ҵٸ� �� ����� 1�� group�̴�.
		//�׷��� replyId�� seq_reply.nextval�� �ߺ����� 1�� ������Ű�鼭
		//groupId�� replyId�� ���� ��ȣ�� seq_reply.currval�� �״�� ����ش�
		//���� replyGroup���� seq_reply.nextval�� ���� 2�� ��
		//depth�� ������� �������� Ȯ�ν����ش�.
		//���� depth�� 0�̶�� ���� ����� ���� ���̰�, 1�̶�� �� ����� ���� ����� 1�� �ִٴ� ��
		//����� ������ ���� ����̱� ������ depth�� ������ 0�̴�.
		String query = "INSERT INTO TBL_REPLY"
				+ "(REPLY_ID, REPLY_CONTENT, USER_ID, BOARD_ID, REPLY_GROUP, REPLY_DEPTH) "
				+ "VALUES(SEQ_REPLY.NEXTVAL, ?, ?, ?, SEQ_REPLY.CURRVAL, 0)";
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, replyVO.getReplyContent());
			preparedStatement.setLong(2, UserDAO.userId);
			preparedStatement.setLong(3, replyVO.getBoardId());
			
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
	
//	���� �߰�
//	������ ��۰� �޸� depth�� �׷��� �˾ƾ��Ѵ�
	public void insert(ReplyVO replyVO, Long target) {
		//������ � ��ۿ� �޾Ҵ��� �˾ƾ��Ѵ�.
		//������ ���� ��۰� group�� ���ƾ��Ѵ�(�翬�� replyId�� �޶����)
		//���� ������ ����� ��������, ������ �������� �˾ƾ� �Ѵ�.
		//�׷��� ������ depth�� ���� ����� depth�� 1�� ������ ���� ���� depth�� �ȴ�.
		//��) ����� �����̸� ����� depth�� 0�̹Ƿ� ������ depth�� 1�� ��
		//�Ű����� target -> �� ���� ����� group�� depth�� �������� ����
		//������ ���� ��۰� group�� ����, depth�� +1�̴�.
		String query = "INSERT INTO TBL_REPLY"
				+ "(REPLY_ID, REPLY_CONTENT, USER_ID, BOARD_ID, REPLY_GROUP, REPLY_DEPTH) "
				+ "VALUES(SEQ_REPLY.NEXTVAL, ?, ?, ?, ?, (SELECT REPLY_DEPTH + 1 FROM TBL_REPLY WHERE REPLY_ID = ?))";
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, replyVO.getReplyContent());
			preparedStatement.setLong(2, UserDAO.userId);
			preparedStatement.setLong(3, replyVO.getBoardId());
			preparedStatement.setLong(4, target);
			preparedStatement.setLong(5, target);
			
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
	
	
//	��� ��ü ��ȸ
//	����� �� ������ �� �� �����Ƿ� ArrayList
//	��۰� �� ����� �� ������ ������ �����;� �ϹǷ� replyDTO(reply ���̺�� user ���̺� ����)
//	replyDTO���� ����� ��� ������ ������ ��� ������ ����ִ�	
//	��� �󼼺��⸦ ������ ����� ������ ����� �ۼ��� user�� �־����
	public ArrayList<ReplyDTO> selectAll(){
		String query = "SELECT NVL(REPLY_COUNT, 0) REPLY_COUNT, REPLY_ID, REPLY_CONTENT, R2.USER_ID, BOARD_ID, REPLY_REGISTER_DATE, REPLY_UPDATE_DATE, " 
				+ "R2.REPLY_GROUP, REPLY_DEPTH, " 
				+ "USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, " 
				+ "USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, " 
				+ "USER_GENDER, USER_RECOMMENDER_ID " 
				+ "FROM (SELECT REPLY_GROUP, COUNT(REPLY_ID) - 1 REPLY_COUNT FROM TBL_REPLY GROUP BY REPLY_GROUP) R1 RIGHT OUTER JOIN VIEW_REPLY_USER R2 "
				+ "ON R1.REPLY_GROUP = R2.REPLY_GROUP AND R1.REPLY_GROUP = R2.REPLY_ID";
//		VIEW_REPLY_USER ��� reply ���̺�� user ���̺��� �����Ͽ� ���� ������ ���̺� (�������� �ʹ� ���������� �����)
//		replyCount�� ����� ���� ��. ��) ��Ʃ�� ��� �Ʒ��� �ִ� ��ۼ�
//		From ���� SELECT REPLY_GROUP, COUNT(REPLY_ID) - 1 REPLY_COUNT FROM TBL_REPLY GROUP BY REPLY_GROUP
//		�� ������������ replyGroup�� replyCount(������ ����)�� ��ȸ�ϱ� ����
//		�̸� reply�� user�� �����Ͽ� ���� ��� �ܺ�����
//		�ܺ������� �ϴ� ���� - ��۰� ���۱��� ��� ����� ��ȸ�ϱ� ���� ���������ϸ� ����� ��� ������ ��ȸ��
//		NVL(REPLY_COUNT, 0) - replyCount�� Long Ÿ���̱� ������ null�� �� �����Ƿ� �̸� 0���� �ٲ��ش�
		
		ReplyDTO replyDTO = null;
		int index = 0;
		ArrayList<ReplyDTO> replies = new ArrayList<ReplyDTO>();
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				index = 0;
				replyDTO = new ReplyDTO();
				replyDTO.setReplyCount(resultSet.getLong(++index));
				replyDTO.setReplyId(resultSet.getLong(++index));
				replyDTO.setReplyContent(resultSet.getString(++index));
				replyDTO.setUserId(resultSet.getLong(++index));
				replyDTO.setBoardId(resultSet.getLong(++index));
				replyDTO.setReplyRegisterDate(resultSet.getString(++index));
				replyDTO.setReplyUpdateDate(resultSet.getString(++index));
				replyDTO.setReplyGroup(resultSet.getLong(++index));
				replyDTO.setReplyDepth(resultSet.getLong(++index));
				replyDTO.setUserIdentification(resultSet.getString(++index));
				replyDTO.setUserName(resultSet.getString(++index));
				replyDTO.setUserPassword(resultSet.getString(++index));
				replyDTO.setUserPhone(resultSet.getString(++index));
				replyDTO.setUserNickname(resultSet.getString(++index));
				replyDTO.setUserEmail(resultSet.getString(++index));
				replyDTO.setUserAddress(resultSet.getString(++index));
				replyDTO.setUserBirth(resultSet.getString(++index));
				replyDTO.setUserGender(resultSet.getString(++index));
				replyDTO.setUserRecommenderId(resultSet.getString(++index));
				replies.add(replyDTO);
			}
			
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
		return replies;
	}
	
//	��� ����(��ۿ� �ִ� ���۱��� ��� ����)
//	���� �׷����� �����ִ� ��� ����� ����
	public void deleteReply(Long groupId) {
		String query = "DELETE FROM TBL_REPLY WHERE REPLY_GROUP = ?";
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, groupId);
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

//	���� ����
//	replyId�� ��ȸ�ؼ� �ش� ��� �ϳ��� ����
	public void deleteReReply(Long replyId) {
		String query = "DELETE FROM TBL_REPLY WHERE REPLY_ID = ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, replyId);
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
	
	
//	��� ����
	public void update(ReplyVO replyVO) {
		//������ ������ ����� ���� �ۿ� �����Ƿ� rpelyId ã�Ƽ� �ش� ��� ���� ����
		String query = "UPDATE TBL_REPLY SET REPLY_CONTENT = ?, REPLY_UPDATE_DATE = SYSDATE WHERE REPLY_ID = ?";
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, replyVO.getReplyContent());
			preparedStatement.setLong(2, replyVO.getReplyId());
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
}































