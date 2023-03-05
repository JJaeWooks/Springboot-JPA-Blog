package com.cosblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosblog.model.Board;


//DAO
//자동으로 bean등록이 된다
//@repository 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{

	
}
