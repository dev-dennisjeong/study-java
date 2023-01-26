package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import domain.UserVO;

public class UserDAO {

	public static Long userId; // null 값을 검사하기 위해서 클래스 타입의 long으로
	public Connection connection; // 연결 객체
	public PreparedStatement preparedStatement; // 쿼리 관리 객체
	public ResultSet resultSet; // 결과 테이블 객체

//   아이디 중복검사
//   userIdentification 매개변수 -> 유저의 아이디로 중복검사를 하기 때문
	public boolean checkId(String userIdentification) {
//		USER_ID의 개수를 조회 -> 만약 1이라면 아이디가 중복이라는 뜻
		String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_IDENTIFICATION = ?";
		boolean result = false;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			resultSet = preparedStatement.executeQuery(); // select면 executeQuery

			// resultSet.next()가 true면 중복이 있으니 1, 없으면 0
			resultSet.next();
			// result에 담아줌
			result = resultSet.getInt(1) == 0;

		} catch (SQLException e) {
			System.out.println("checkId(String) SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// resultSet, preapredStatement, connection을 순서대로 닫아줘야함
			// resultSet 안썼으면 닫아줄 필요 x
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		// 중복이면 false를 리턴, 중복이 없으면 true를 리턴
		return result;
	}

//   회원가입
	public void insert(UserVO userVO) {
		String queryForJoin = "INSERT INTO TBL_USER"
				+ "(USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, USER_PHONE, USER_EMAIL, USER_ADDRESS, USER_RECOMMENDER_ID) "
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

//       기존 테이블에서 추천수 컬럼을 추가하여, 회원 정보 조회 시 추천 수도 같이 가져오도록 설계
//       String queryForUpdateRecommendCount = 
//             "UPDATE TBL_USER SET RECOMMEND_COUNT = RECOMMEND_COUNT + 1 WHERE USER_ID = ?";

//       만약 추천 수 조회가 잦을 경우, 테이블을 따로 분리하여 서브키를 설정한 뒤 조회
//       테이블이 분리되어서 회원가입 시 TBL_USER에 INSERT 후 TBL_RECOMMEND도 같이 INSERT가 필요하다.
//       이 때 TRIGGER를 사용하여 TBL_USER에 INSERT이벤트 발생 시 TBL_RECOMMEND에도 자동으로 INSERT 되도록 설계
		String queryForUpdateRecommendCount = "UPDATE TBL_RECOMMEND SET RECOMMEND_COUNT = RECOMMEND_COUNT + 1 WHERE USER_ID = ?";
		String queryForGetUserId = "SELECT USER_ID FROM TBL_USER WHERE USER_IDENTIFICATION = ?";

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(queryForJoin);
			//query문에서 추가해야할 행의 개수(?의 개수)가 7개이므로 7개 set 다 해줘야함
			//순서 지켜야함
			preparedStatement.setString(1, userVO.getUserIdentification());
			preparedStatement.setString(2, userVO.getUserName());
			preparedStatement.setString(3, encrypt(userVO.getUserPassword()));
			preparedStatement.setString(4, userVO.getUserPhone());
			preparedStatement.setString(5, userVO.getUserEmail());
			preparedStatement.setString(6, userVO.getUserAddress());
			preparedStatement.setString(7, userVO.getUserRecommenderId());
			preparedStatement.executeUpdate(); // select이외는 executeUpdate, ex)update, insert, delete

			// 추천인 아이디가 있는지 검사한다 -> NOT NULL이 아니기 때문에 없을 수도 있음
			if (userVO.getUserRecommenderId() != null) {
				// 추천인 아이디가 null이 아니면(추천인 아이디가 있으면)
				// 아래 실행문으로
				// 유저의 identification을 통해 userId를 조회한다
				connection.prepareStatement(queryForGetUserId);
				// 유저의 추천인 아이디를 userVO에 담아준다
				preparedStatement.setString(1, userVO.getUserRecommenderId());
				// resultSet에 select문의 결과를 가져온다
				resultSet = preparedStatement.executeQuery();

				// resultSet.next()에 값이 있다면 true
				if (resultSet.next()) { // 입력한 추천인 아이디가 존재할 경우
					preparedStatement = connection.prepareStatement(queryForUpdateRecommendCount);
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
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println("회원가입 성공");
	}

//   로그인
	public boolean login(String userIdentification, String userPassword) {
		String query = "SELECT USER_ID FROM TBL_USER WHERE USER_IDENTIFICATION = ? AND USER_PASSWORD = ?";
		boolean check = false;

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			//암호화한 패스워드를 입력한다
			//encrypt 메소드 아래 만들어져있음
			preparedStatement.setString(2, encrypt(userPassword));

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				// static 변수인 userId에 resultSet 결과 테이블의 1번 행을 담아준다
				// 1번행은 userId이므로 Long타입으로 가져와야한다.
				userId = resultSet.getLong(1);
				// 만약 있다면 check에 true를 담는다 -> 로그인 가능
				check = true;
			}

		} catch (SQLException e) {
			System.out.println("login(String, String) SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return check;
	}

//   회원탈퇴
//	로그인 한 상태에서만 가능
//	Primary Key인 userId에 담겨 있는 정보를 삭제한다
	public void delete() {
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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

//   아이디 찾기
//	휴대폰 번호로 아이디를 찾는다 -> 아이디만 조회하는거니까 String 타입으로 리턴
	public String findIdentification(String userPhone) {
		//폰 번호로 유저의 identification만 조회
		String query = "SELECT USER_IDENTIFICATION FROM TBL_USER WHERE USER_PHONE = ?";
		String userIdentification = null;

		connection = DBConnecter.getConnection();
		try {
			//query문에서 where절에 폰 번호 넣고 그 폰 번호를 가진 user의 identification을 찾는다
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) { //true면 1번 행(유저의 identification)을 userIdentification에 담아준다
				userIdentification = resultSet.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("findIdentification(String) SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return userIdentification; //유저의 identification을 리턴한다.
		//만약 없으면 null을 리턴
	}

//   비밀번호 변경
	public void updateUserPassword(String userPassword) {
		//한 유저의 비밀번호를 변경해야하므로
		//입력한 userId의 새로운 비밀번호를 입력해야함
		//쿼리문에 ? 개수만큼 prepared.set을 해줘야함
		String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			//암호화한 새로운 패스워드를 담아준다
			preparedStatement.setString(1, encrypt(userPassword));
			preparedStatement.setLong(2, userId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateUserPassword(String) SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

//   회원정보 수정
	public void update(UserVO userVO) {
		//수정할 수 있는 user의 컬럼을 모두 쿼리문에 때려넣는다
		//(pk, uk가 아니거나 CHECK가 없거나 NOT NULL 조건이 없는 컬럼)
		//뭘 수정할지 모르기 때문
		String query = "UPDATE TBL_USER SET USER_NICKNAME = ?, USER_GENDER = ?, USER_BIRTH = ? WHERE USER_ID = ?";
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
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

//   회원정보 조회
//	세션에 저장되어있는 회원번호를 불러온다. (유저용)
	public UserVO select(/*Long userId*/) {
		String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID " + "FROM TBL_USER WHERE USER_ID = ?";
		UserVO userVO = null;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				//만약 user가 존재한다면 회원의 모든 정보를 담아준다
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
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return userVO; //user가 존재하지 않으면 null을 리턴
	}
	
//	userId를 관리자 페이지에서 직접 검색(관리자용)
	public UserVO select(Long userId) {
		String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID " + "FROM TBL_USER WHERE USER_ID = ?";
		UserVO userVO = null;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
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
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		return userVO;
	}

//   추천수
	public Long getCountOfRecommenderId(Long userId) {
//      String query = "SELECT RECOMMEND_COUNT FROM TBL_USER WHERE USER_ID = ?";
		//TBL_RECOMMEND는 USER_ID와 RECOMMED_COUNT를 담고 있는 테이블
		//TBL_USER의 PK인 USER_ID를 외래키로 갖는다.
		//추천수를 TBL_RECOMMEND에 담고 이 테이블에서 추천수를 검사한다.
		String query = "SELECT RECOMMEND_COUNT FROM TBL_RECOMMEND WHERE USER_ID = ?";
		Long recommendCount = 0L;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();

			//recommend 테이블에 입력한 userId가 있다면 recommendCount에 담아준다
			//첫번째 행(userId)의 개수만큼 담아주는 것
			if (resultSet.next()) { 
				recommendCount = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("getCountOfRecommenderId(Long) SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return recommendCount;
	}

//   나를 추천한 사람
	//나를 추천한 사람은 여러 명일 수 있다.
	//그래서 ArrayList에 여러 유저의 정보를 담을 수 있게 한다.
	public ArrayList<UserVO> selectRecommenders() {
		String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
		UserVO userVO = null;
		ArrayList<UserVO> users = new ArrayList<UserVO>();

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, select(userId).getUserIdentification());

			resultSet = preparedStatement.executeQuery();

			//user가 몇 명인지 모르니 몇 번 반복을 할 지 모른다
			//그래서 while문으로 반복 돌린다(더이상 userRecommendId가 없으면 반복종료)
			//userVO에 유저의 정보를 담아준다
			while (resultSet.next()) {
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
				//users에 추가
				users.add(userVO);
			}

		} catch (SQLException e) {
			System.out.println("selectRecommenders() SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return users;
	}

//   내가 추천한 사람
	//내가 추천한 사람은 한명
	//user의 정보를 리턴하기 위해 userVO 타입
	//전체 정보를 가져오므로 매개변수를 받을 게 없음
	public UserVO getMyRecommender() {
		//query문에서 where절에 서브쿼리
		//user 테이블에서 입력한 userId의 recommenderId가 userId와 같은 사람의 정보 
		String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
				+ " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
				+ "USER_GENDER, USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_ID = " + "("
				+ "SELECT USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_ID = ?" + ")";
		UserVO userVO = null;

		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
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
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return userVO;
	}

	public String encrypt(String password) {
		return new String(Base64.getEncoder().encode(password.getBytes()));
	}

}
