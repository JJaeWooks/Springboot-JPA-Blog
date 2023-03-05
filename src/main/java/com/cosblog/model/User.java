package com.cosblog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//빌더패턴
@Entity  //User 클래스가 MySQL에 테이블이 생성이 된다  
//@DynamicInsert //insert시에 null인 필드를 제외시켜준다
public class User {
	
	@Id //primary(키본키) key 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;// 시퀸스,auto_increment
	
	@Column(nullable=false,length=100,unique=true) //빈값이 올수없고 길이는 30
	private String username;//아이디
	
	@Column(nullable=false,length=100) //빈값이 올수없고 길이는 100
	private String password;
	
	@Column(nullable=false,length=50)
	private String email;
	
	//@ColumnDefault("user")
	//DB는 roleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;// Enum을 쓰는게 좋다. // admin,user,manager 권한을 줄수있다
	
	private String oauth; // kakao,google
	
	@CreationTimestamp // 시간이 자동으로 입력
	private Timestamp createDate; //가입시간
	
	
	
}
