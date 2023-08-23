package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.example.model.entity.UserEntity;


public interface UserDao extends JpaRepository<UserEntity, Long> {
	//UserService에서 받은 유저 정보를 DB에 보존
	UserEntity save(UserEntity userentity);
	
	//Userservice에서 받은 유저 정보(유저 이름)을 조건으로 DB에서 검색
	UserEntity findByUserName(String userName);
	
	UserEntity findByUserNameAndUserPassword(String userName, String userPassword);
	
	//유저 정보 일람 습득
	List<UserEntity> findAll();
}
