package com.cosblog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tempcontrollertest {
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		return "/home.html";
	}
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix:/WEB-INF/views/
		//suffix:jsp
		//풀네임: /WEB-INF/views/test.jsp
		
		return "test";
	}
	

}
