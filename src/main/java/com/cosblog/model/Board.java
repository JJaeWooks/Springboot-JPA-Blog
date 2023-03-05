package com.cosblog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false,length=100)
	private String title;
	
	@Lob //대용량 데이터 사용
	private String content;//섬머노트 라이브러리 <html>태그가  섞여서 디자인 됨
	
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER)  // Many=Board, user = one 한명에 유저는 여러개의 게시글을 쓸수있다.
	@JoinColumn(name="userid")
	private User user; //DB는 오브젝트를 저장할 수 없다.FK,자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)  //mappedBy는 연관관계의 주인이 아니다 (난 외래키가 아니에요) DB에 컬럼을 만들지 마세요
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
