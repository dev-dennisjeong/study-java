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
	
//	댓글 추가
//	게시글에 댓글을 추가한다
	public void insert(ReplyVO replyVO) {
		//reply 테이블에 reply의 모든 정보를 담는다
		//replyId와 replyGroup은 같은 값
		//예) boardId가 1번인 게시글에 replyId가 1번인 댓글을 달았다면 그 댓글은 1번 group이다.
		//그래서 replyId를 seq_reply.nextval로 중복없이 1씩 증가시키면서
		//groupId는 replyId의 현재 번호를 seq_reply.currval로 그대로 담아준다
		//만약 replyGroup에도 seq_reply.nextval를 쓰면 2가 됨
		//depth는 댓글인지 대댓글인지 확인시켜준다.
		//만약 depth가 0이라면 상위 댓글이 없는 것이고, 1이라면 그 댓글의 상위 댓글이 1개 있다는 뜻
		//댓글은 무조건 상위 댓글이기 때문에 depth는 무조건 0이다.
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
	
//	대댓글 추가
//	대댓글은 댓글과 달리 depth와 그룹을 알아야한다
	public void insert(ReplyVO replyVO, Long target) {
		//대댓글은 어떤 댓글에 달았는지 알아야한다.
		//대댓글은 상위 댓글과 group이 같아야한다(당연히 replyId는 달라야함)
		//또한 대댓글이 댓글의 대댓글인지, 대댓글의 대댓글인지 알아야 한다.
		//그래서 대댓글의 depth는 상위 댓글의 depth에 1을 더해준 값이 나의 depth가 된다.
		//예) 댓글의 대댓글이면 댓글의 depth는 0이므로 대댓글의 depth는 1이 됨
		//매개변수 target -> 내 상위 댓글의 group과 depth를 가져오기 위함
		//대댓글은 상위 댓글과 group이 같고, depth는 +1이다.
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
	
	
//	댓글 전체 조회
//	댓글이 몇 개인지 알 수 없으므로 ArrayList
//	댓글과 그 댓글을 단 유저의 정보를 가져와야 하므로 replyDTO(reply 테이블과 user 테이블 조인)
//	replyDTO에는 댓글의 모든 정보와 유저의 모든 정보가 담겨있다	
//	댓글 상세보기를 들어갔을때 댓글의 정보와 댓글을 작성한 user가 있어야함
	public ArrayList<ReplyDTO> selectAll(){
		String query = "SELECT NVL(REPLY_COUNT, 0) REPLY_COUNT, REPLY_ID, REPLY_CONTENT, R2.USER_ID, BOARD_ID, REPLY_REGISTER_DATE, REPLY_UPDATE_DATE, " 
				+ "R2.REPLY_GROUP, REPLY_DEPTH, " 
				+ "USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, " 
				+ "USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, " 
				+ "USER_GENDER, USER_RECOMMENDER_ID " 
				+ "FROM (SELECT REPLY_GROUP, COUNT(REPLY_ID) - 1 REPLY_COUNT FROM TBL_REPLY GROUP BY REPLY_GROUP) R1 RIGHT OUTER JOIN VIEW_REPLY_USER R2 "
				+ "ON R1.REPLY_GROUP = R2.REPLY_GROUP AND R1.REPLY_GROUP = R2.REPLY_ID";
//		VIEW_REPLY_USER 뷰는 reply 테이블과 user 테이블을 조인하여 만든 가상의 테이블 (쿼리문이 너무 복잡해져서 만든것)
//		replyCount는 댓글의 대댓글 수. 예) 유튜브 댓글 아래에 있는 답글수
//		From 절에 SELECT REPLY_GROUP, COUNT(REPLY_ID) - 1 REPLY_COUNT FROM TBL_REPLY GROUP BY REPLY_GROUP
//		이 서브쿼리문은 replyGroup의 replyCount(대댓글의 갯수)를 조회하기 위함
//		이를 reply와 user를 조인하여 만든 뷰와 외부조인
//		외부조인을 하는 이유 - 댓글과 대댓글까지 모든 댓글을 조회하기 위함 내부조인하면 댓글의 모든 정보만 조회됨
//		NVL(REPLY_COUNT, 0) - replyCount는 Long 타입이기 때문에 null일 수 있으므로 이를 0으로 바꿔준다
		
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
	
//	댓글 삭제(댓글에 있는 대댓글까지 모두 삭제)
//	같은 그룹으로 묶여있는 모든 댓글을 삭제
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

//	대댓글 삭제
//	replyId를 조회해서 해당 댓글 하나만 삭제
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
	
	
//	댓글 수정
	public void update(ReplyVO replyVO) {
		//수정할 정보는 댓글의 내용 밖에 없으므로 rpelyId 찾아서 해당 댓글 내용 수정
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































