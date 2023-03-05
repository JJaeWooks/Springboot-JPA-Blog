package com.cosblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosblog.model.User;


//DAO
//자동으로 bean등록이 된다
//@repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

	//SELECT * FROM user WHERE usernmame=1?
	Optional<User> findByUsername(String username);
}
//JPA Naming 쿼리
//select * FROM user WHERE username=?밑에 유저가 들어옴 AND password = 밑에 패스워드가 들어옴
//	User findByUsernameAndPassword(String username,String password);
	
//	@Query(value="SELECT * FROM user WHERE username=?1 AND password = ?2",nativeQuery=true)
//	User login(String username,String password);