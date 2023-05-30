package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.BoardDTO;
import domain.BoardVO;

public class BoardDAO {
	public Connection connection; //연결 객체
	public PreparedStatement preparedStatement; //쿼리 관리 객체
	public ResultSet resultSet; //결과 테이블 객체
	
//	게시글 추가, 수정, 삭제는 로그인한 상태에서만 가능!!
	
//	게시글 추가
//	게시글에 추가할 수 있는 모든 정보를 입력할 수 있어야함
// 	게시판의 모든 정보를 한번에 받아오기 위해서 VO객체를 사용
	public void insert(BoardVO boardVO) {
		//게시글을 추가한 순간 등록과 동시에 업데이트가 되는 것이므로
		//두 컬럼의 값으로 현재 시간인 SYSDATE를 넣어준다.
		String query = "INSERT INTO TBL_BOARD"
				+ "(BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, BOARD_UPDATE_DATE, USER_ID) "
				+ "VALUES(SEQ_BOARD.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?)";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, boardVO.getBoardTitle());
			preparedStatement.setString(2, boardVO.getBoardContent());
			//화면에서 로그인 되어 있는 user의 아이디를 받아와 담아준다
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
	
//	게시글 조회
//	BoardDTO 타입으로 해주는 이유
//	원래 Board 테이블과 User테이블은 하나의 테이블
//	이상현상을 없애기 위해 정규화를 통해 테이블을 분리
//	User 테이블의 PK 값을 외래키로 참조한 Board테이블을 따로 분리해서 관리
//	게시글 상세보기를 들어갔을때 게시글의 정보와 게시글을 작성한 user가 있어야함
//	user 부분에서 어떤 정보를 쓸 지 모르기 때문에 user의 정보도 다 갖고와야함
//	때문에 user의 두 테이블의 모든 정보를 가져오기 위해 User테이블과 Board 테이블을 조인해서 조회
//	BoardVO에서는 user의 정보를 가져올 수 없으므로 여기서 BoardDTO 객체를 domain에서 만들어서 써야한다
//	※DTO - 화면에 전달하는 목적으로만 쓸 때 사용한다
//	※VO는 무조건 테이블과 컬럼이 1대1로 되어있어야함
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
			//boardId가 있다면(게시글이 있다면)
			//DTO에 모든 정보를 담아준다.
			if(resultSet.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setBoardId(resultSet.getLong(++index)); //숫자 매기기 귀찮아서 ++index
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
//	게시글 수정
	public void update(BoardVO boardVO) {
		//수정할 수 있는 컬럼들은 게시글 제목과 내용
		//갱신 날짜는 현재 날짜이므로 SYSDATE
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
	
//	게시글 삭제
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
	
//	게시글 전체 조회
//	게시글이 여러 개일수도 있으니 ArrayList
//	게시글 조회랑 비슷
//	게시글의 모든 정보와 user의 모든 정보 가져와야하니까 BoardDTO 타입
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
			
			//게시글이 몇 개일지 모르니 몇 번 반복을 돌려야 할지도 모른다
			//그래서 while문으로 false(게시글이 더이상 없을 때)까지 반복
			while(resultSet.next()) {
				//user가 작성한 게시글이 있다면 BoardDTO에 그 user와 그 게시글의 모든 정보를 담아준다
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




















