package com.cosblog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class member {


	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
