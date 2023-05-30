package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import domain.UserVO;

public class UserDAO {

	public static Long userId;
	public Connection connection; //연결 객체
	public PreparedStatement preparedStatement; //쿼리 관리 객체
	public ResultSet resultSet; //결과 테이블 객체
	
//	아이디 중복검사
//	유저의 identification으로 중복을 검사한다.
	public boolean checkId(String userIdentification) {
		//유저의 identification의 수를 조회한다 -> 1이면 이미 존재하는 아이디
		String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_IDENTIFICATION = ?";
		boolean result = false; //result 변수를 boolean 타입으로 선언
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next(); 
			//resultSet.getInt(1)이 0이면 true
			//result가 true면 중복이 없다는 뜻
			result = resultSet.getInt(1) == 0;
			
		} catch (SQLException e) {
			System.out.println("checkId(String) SQL문 오류");
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
		return result;
	}
	
//	회원가입
	public void insert(UserVO userVO) {
		 String queryForJoin = "INSERT INTO TBL_USER"
		            + "(USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, USER_PHONE, USER_BIRTH, USER_EMAIL, USER_ADDRESS, USER_RECOMMENDER_ID) "
		            + "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
		 
//		 기존 테이블에서 추천수 컬럼을 추가하여, 회원 정보 조회 시 추천 수도 같이 가져오도록 설계
//		 String queryForUpdateRecommendCount = 
//				 "UPDATE TBL_USER SET RECOMMEND_COUNT = RECOMMEND_COUNT + 1 WHERE USER_ID = ?";
		 
//		 만약 추천 수 조회가 잦을 경우, 테이블을 따로 분리하여 서브키를 설정한 뒤 조회
//		 테이블이 분리되어서 회원가입 시 TBL_USER에 INSERT 후 TBL_RECOMMEND도 같이 INSERT가 필요하다.
//		 이 때 TRIGGER를 사용하여 TBL_USER에 INSERT이벤트 발생 시 TBL_RECOMMEND에도 자동으로 INSERT 되도록 설계
		 String queryForUpdateRecommendCount = 
				 "UPDATE TBL_RECOMMEND SET RECOMMEND_COUNT = RECOMMEND_COUNT + 1 WHERE USER_ID = ?";
		 
		 String queryForGetUserId = "SELECT USER_ID FROM TBL_USER WHERE USER_IDENTIFICATION = ?";
		 
		 connection = DBConnecter.getConnection();
		 try {
			preparedStatement = connection.prepareStatement(queryForJoin);
			preparedStatement.setString(1, userVO.getUserIdentification());
			preparedStatement.setString(2, userVO.getUserName());
			//암호화한 비밀번호를 담아준다 -> encrypt 메소드 아래 따로 만들어놨음
			preparedStatement.setString(3, encrypt(userVO.getUserPassword()));
			preparedStatement.setString(4, userVO.getUserPhone());
			preparedStatement.setString(5, userVO.getUserBirth());
			preparedStatement.setString(6, userVO.getUserEmail());
			preparedStatement.setString(7, userVO.getUserAddress());
			preparedStatement.setString(8, userVO.getUserRecommenderId());
			preparedStatement.executeUpdate();
			
			//유저에 추천인 아이디가 있는지 검사, 있으면 if문으로 들어옴
			if(userVO.getUserRecommenderId() != null) {
				preparedStatement = connection.prepareStatement(queryForGetUserId);
				preparedStatement.setString(1, userVO.getUserRecommenderId());
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) { // 입력한 추천인 아이디가 존재할 경우
					preparedStatement = connection.prepareStatement(queryForUpdateRecommendCount);
					//그 userId를 담아줌
					preparedStatement.setLong(1, resultSet.getLong(1));
					preparedStatement.executeUpdate();
				}
			}

		} catch (SQLException e) {
			System.out.println("insert(UserVO) SQL문 오류");
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
	}
//	로그인
//	아이디 비밀번호로 로그인
	public boolean login(String userIdentification, String userPassword) {
		String query = "SELECT USER_ID FROM TBL_USER WHERE USER_IDENTIFICATION = ? AND USER_PASSWORD = ?";
		boolean check = false;
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			preparedStatement.setString(2, encrypt(userPassword));
			
			//user 테이블에 아이디와 비밀번호가 일치하여 userId가 조회된다면
			//resultSet은 true
			resultSet = preparedStatement.executeQuery();
			
			//resultSet을 검사해서 true면 userId를 담고 check에 true를 담아서 리턴
			//userId는 위에 선언한 static 메소드 userId에 담아준다
			if(resultSet.next()) {
				userId = resultSet.getLong(1);
				check = true;
			}
			
		} catch (SQLException e) {
			System.out.println("login(String, String) SQL문 오류");
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
		return check;
	}
	
//	회원탈퇴
//	**회원에 컬럼을 추가한뒤 회원정보를 삭제하지 않고 '삭제상태'로 변경할 수 있게끔 해보기. DELETE가 아닌 UPDATE로 진행.
//	로그인한 상태에서 진행
	public void delete() {
		//userId 찾아서 전체 정보 삭제
		String query = "DELETE FROM TBL_USER WHERE USER_ID = ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete() SQL문 오류");
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
	
//	회원탈퇴
//	**회원에 컬럼을 추가한뒤 회원정보를 삭제하지 않고 '삭제상태'로 변경할 수 있게끔 해보기. DELETE가 아닌 UPDATE로 진행.
//	로그인한 상태에서 진행
//	user 테이블에 userStatus라는 컬럼 추가
//	default 값은 '회원'
//	회원탈퇴를 누르면 userStatus를 '삭제'로 변경
	public void withdrawal() {
		String query = "UPDATE TBL_USER "
				+ "SET USER_STATUS = '삭제' "
				+ "WHERE USER_ID= ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete() SQL문 오류");
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
	
	
	
//	아이디 찾기
//	**휴대폰 번호는 중복이 허용됨으로 다른 중복없는 컬럼과 함께 조회해야 함
//	휴대폰 번호로 아이디를 찾는다
	public String findIdentification(String userPhone) {
		String query = "SELECT USER_IDENTIFICATION FROM TBL_USER WHERE USER_PHONE = ?";
		String userIdentification = null;
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);

			resultSet = preparedStatement.executeQuery();
			
			//만약 휴대폰번호를 검색해서 유저 identification이 조회된다면
			//그 userIdenfication을 담아서 리턴
			if(resultSet.next()) {
				userIdentification = resultSet.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("findIdentification(String) SQL문 오류");
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
		
		return userIdentification;
	}
	
//	비밀번호 변경
//	로그인한 상태에서 변경
	public void updateUserPassword(String userPassword) {
		String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			//입력한 패스워드로 변경
			preparedStatement.setString(1, encrypt(userPassword));
			//userId에 담겨 있는
			preparedStatement.setLong(2, userId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateUserPassword(String) SQL문 오류");
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
	
//	회원정보 수정
//	로그인한 상태(마이페이지)
	public void update(UserVO userVO) {
		//뭘 변경할지 모르니까 수정할 수 있는 모든 정보를 쿼리문에 다 담아줌
		// **화면에 변경 버튼을 모든 칸에 만들순 없기 때문에
		// **유저 정보를 한꺼번에 받아오기 위해서 VO객체를 사용하는 이유도 있다.
		String query = "UPDATE TBL_USER SET USER_NICKNAME = ?, USER_GENDER = ?, USER_BIRTH = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') WHERE USER_ID = ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserNickname());
			preparedStatement.setString(2, userVO.getUserGender());
			preparedStatement.setString(3, userVO.getUserBirth());
			preparedStatement.setLong(4, userId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("update(UserVO) SQL문 오류");
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
	
//	회원정보 조회
//	유저용
	public UserVO select(/* Long userId */) {
		String query = 
				"SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID "
				+ "FROM TBL_USER WHERE USER_ID = ?";
		UserVO userVO = null;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				userVO = new UserVO();
				userVO.setUserId(resultSet.getLong(1));
				userVO.setUserIdentification(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserPassword(resultSet.getString(4));
				userVO.setUserPhone(resultSet.getString(5));
				userVO.setUserNickname(resultSet.getString(6));
				userVO.setUserEmail(resultSet.getString(7));
				userVO.setUserAddress(resultSet.getString(8));
				userVO.setUserBirth(resultSet.getString(9));
				userVO.setUserGender(resultSet.getString(10));
				userVO.setUserRecommenderId(resultSet.getString(11));
			}
			
		} catch (SQLException e) {
			System.out.println("select(Long) SQL문 오류");
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
		
		return userVO;
	}
	
//	관리자 검색 및 내부 로직용
	public UserVO select(Long userId) {
		String query = 
				"SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
						+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
						+ "USER_GENDER, USER_RECOMMENDER_ID "
						+ "FROM TBL_USER WHERE USER_ID = ?";
		UserVO userVO = null;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				userVO = new UserVO();
				userVO.setUserId(resultSet.getLong(1));
				userVO.setUserIdentification(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserPassword(resultSet.getString(4));
				userVO.setUserPhone(resultSet.getString(5));
				userVO.setUserNickname(resultSet.getString(6));
				userVO.setUserEmail(resultSet.getString(7));
				userVO.setUserAddress(resultSet.getString(8));
				userVO.setUserBirth(resultSet.getString(9));
				userVO.setUserGender(resultSet.getString(10));
				userVO.setUserRecommenderId(resultSet.getString(11));
			}
			
		} catch (SQLException e) {
			System.out.println("select(Long) SQL문 오류");
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
		
		return userVO;
	}
	
//	user의 identification으로도 조회할 수 있게
	public UserVO select(String userIdentification) {
		String query = 
				"SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
						+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
						+ "USER_GENDER, USER_RECOMMENDER_ID "
						+ "FROM TBL_USER WHERE USER_IDENTIFICATION = ?";
		UserVO userVO = null;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				userVO = new UserVO();
				userVO.setUserId(resultSet.getLong(1));
				userVO.setUserIdentification(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserPassword(resultSet.getString(4));
				userVO.setUserPhone(resultSet.getString(5));
				userVO.setUserNickname(resultSet.getString(6));
				userVO.setUserEmail(resultSet.getString(7));
				userVO.setUserAddress(resultSet.getString(8));
				userVO.setUserBirth(resultSet.getString(9));
				userVO.setUserGender(resultSet.getString(10));
				userVO.setUserRecommenderId(resultSet.getString(11));
			}
			
		} catch (SQLException e) {
			System.out.println("select(Long) SQL문 오류");
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
		
		return userVO;
	}
	
//	추천수
	public Long getCountOfRecommenderId(String userIdentification) {
//		String query = "SELECT RECOMMEND_COUNT FROM TBL_USER WHERE USER_ID = ?";
		//userId에서 추천수를 조회
		String query = "SELECT RECOMMEND_COUNT FROM TBL_RECOMMEND WHERE USER_ID = ?";
		Long recommendCount = 0L;
		//입력한 유저의 identification에서 userId를 가져와 담아준다
		Long userId = select(userIdentification).getUserId(); 
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			//추천수가 있는지 검사하고 담아서 리턴
			if(resultSet.next()) { 
				//추천수가 없다면 0을 리턴한다
				recommendCount = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("getCountOfRecommenderId(Long) SQL문 오류");
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
		return recommendCount;
	}
	
//	나를 추천한 사람
//	나를 추천한 사람은 여러 명일 수 있으므로 여러 유저의 정보를 담을 수 있게 ArrayList로
	public ArrayList<UserVO> selectRecommenders(){
		//추천인 아이디의 모든 정보를 조회
		String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
		//userId에서 identification가져와서 담는다
		String userIdentification = select(userId).getUserIdentification();
		//userVO에 null로 담는 이유
		//나를 추천한 유저가 없으면 null로 리턴하려고
		UserVO userVO = null;
		
		//담아줄 변수 ArrayList로 객체화
		ArrayList<UserVO> users = new ArrayList<UserVO>();
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			
			resultSet = preparedStatement.executeQuery();
			
			//정보가 있는지 검사
			//나를 추천한 유저가 몇명인지 모르므로 while문
			//유저의 정보가 더이상 없으면 false를 리턴하고 반복문 종료
			while(resultSet.next()) {
				userVO = new UserVO();
				userVO.setUserId(resultSet.getLong(1));
				userVO.setUserIdentification(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserPassword(resultSet.getString(4));
				userVO.setUserPhone(resultSet.getString(5));
				userVO.setUserNickname(resultSet.getString(6));
				userVO.setUserEmail(resultSet.getString(7));
				userVO.setUserAddress(resultSet.getString(8));
				userVO.setUserBirth(resultSet.getString(9));
				userVO.setUserGender(resultSet.getString(10));
				userVO.setUserRecommenderId(resultSet.getString(11));
				users.add(userVO);
			}
			
		} catch (SQLException e) {
			System.out.println("selectRecommenders() SQL문 오류");
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
		return users;
	}
	
//	내가 추천한 사람
	public UserVO getMyRecommender() {
		//세션(static 메소드)에 userId에 담겨있는 userId의 추천인 아이디와 
		//일치하는 유저 identification 찾아서 전체 정보 조회
		String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_IDENTIFICATION = "
				+ "("
				+ "SELECT USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_ID = ?"
				+ ")";
		//찾는 userVO 없으면 null로 리턴
		UserVO userVO = null; 
		
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			
			resultSet = preparedStatement.executeQuery();
			
			//내가 추천한 사람이 있는지 검사하고
			//있으면 userVO에 그 사람의 모든 정보를 담는다
			if(resultSet.next()) {
				userVO = new UserVO();
				userVO.setUserId(resultSet.getLong(1));
				userVO.setUserIdentification(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserPassword(resultSet.getString(4));
				userVO.setUserPhone(resultSet.getString(5));
				userVO.setUserNickname(resultSet.getString(6));
				userVO.setUserEmail(resultSet.getString(7));
				userVO.setUserAddress(resultSet.getString(8));
				userVO.setUserBirth(resultSet.getString(9));
				userVO.setUserGender(resultSet.getString(10));
				userVO.setUserRecommenderId(resultSet.getString(11));
			}
			
		} catch (SQLException e) {
			System.out.println("getMyRecommender() SQL문 오류");
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
		return userVO;
	}
	
// 팔로우하기
	public void follow(Long followerId) {
		String queryForFollowingInsert = "INSERT INTO TBL_FOLLOW"
				+ "(FOLLOW_ID, USER_ID, FOLLOWER_ID, FOLLOW_DATE)"
				+ "VALUES(SEQ_FOLLOW.NEXTVAL, ?, ?, SYSDATE)";
		
		connection = DBConnecter.getConnection();
			try {
				preparedStatement = connection.prepareStatement(queryForFollowingInsert);
				preparedStatement.setLong(1, userId);
				preparedStatement.setLong(2, followerId);
				preparedStatement.executeUpdate();
				
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
	}
	
	
//	내가 팔로우한 사람의 수 조회
	public Long countFollower() {
		String queryForMyFollow = "SELECT COUNT(FOLLOW_ID) FROM TBL_FOLLOW F JOIN TBL_USER U  "
				+ "ON F.USER_ID = U.USER_ID AND  F.USER_ID = ?";
		connection = DBConnecter.getConnection();
		Long count = 0L;
		
		try {
			preparedStatement = connection.prepareStatement(queryForMyFollow);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				count = resultSet.getLong(1);
				
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
		return count;
	}
	
	
//	암호화
	public String encrypt(String password) {
		return new String(Base64.getEncoder().encode(password.getBytes()));
	}
	
}





















