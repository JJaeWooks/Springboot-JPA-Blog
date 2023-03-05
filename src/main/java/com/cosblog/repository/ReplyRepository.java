package com.cosblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosblog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
