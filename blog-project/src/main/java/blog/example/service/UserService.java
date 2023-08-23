package blog.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.UserDao;
import blog.example.model.entity.UserEntity;



@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//유저 정보 보존 처리
	public boolean createAccount(String userName, String userEmail, String userPassword,String profileImageName,String blogImageName,String profile) {
		//RegisterController에서 전해받는 유저 이름 정보를 조건으로 DB에서 검색
		UserEntity userEntity = userDao.findByUserName(userName);
		//RegisterController에서 전해받는 유저 이름, 비밀번호 정보를 조건으로 DB에서 검색
		//없으면 보존
		if(userEntity == null) {
			userDao.save(new UserEntity(userName, userEmail, userPassword,profileImageName,blogImageName,profile));
			return true;
		}else {
			return false;
		}
	}
	//로그인 처리
	public UserEntity chackLogin(String username, String userPassword) {
		UserEntity userEntity = userDao.findByUserNameAndUserPassword(username, userPassword);
		if(userEntity == null) {
			return null;
		}else {
			return userEntity;
		}
	}
	
	public UserEntity findUser(Long userId) {
		return userDao.findByUserId(userId);
	}
	
	public boolean updateBlogImage(Long userId,String blogImageName) {
		//RegisterController에서 전해받는 유저 이름 정보를 조건으로 DB에서 검색
		UserEntity userEntity = userDao.findByUserId(userId);
		//RegisterController에서 전해받는 유저 이름, 비밀번호 정보를 조건으로 DB에서 검색
		//없으면 보존
	    if(userEntity == null) {
	    	return false;
	    }else {
	    	 userEntity.setBlogImage(blogImageName);
				userDao.save(userEntity);
				return true;
			
	    }
		   
	}
	
	public boolean updateProfileImage(Long userId,String profileImageName) {
		//RegisterController에서 전해받는 유저 이름 정보를 조건으로 DB에서 검색
		UserEntity userEntity = userDao.findByUserId(userId);
		//RegisterController에서 전해받는 유저 이름, 비밀번호 정보를 조건으로 DB에서 검색
		//없으면 보존
		if(userEntity == null) {
			return false;
		}else {
			
			userEntity.setUserId(userId);
			userEntity.setProfileImage(profileImageName);
			userDao.save(userEntity);
			return true;
		}
	}
	
	public boolean updateProfile(Long userId,String profile) {
		//RegisterController에서 전해받는 유저 이름 정보를 조건으로 DB에서 검색
		UserEntity userEntity = userDao.findByUserId(userId);
		//RegisterController에서 전해받는 유저 이름, 비밀번호 정보를 조건으로 DB에서 검색
		//없으면 보존
		if(userEntity == null) {
			return false;
		}else {
			
			userEntity.setUserId(userId);
			userEntity.setProfile(profile);
			userDao.save(userEntity);
			return true;
		}
	}

}
