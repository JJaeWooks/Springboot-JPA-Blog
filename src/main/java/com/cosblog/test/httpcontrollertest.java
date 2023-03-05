package com.cosblog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class httpcontrollertest {
	
	private static final String TAG="httpcontrollertest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		member m=member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter:" + m.getUsername());
		m.setUsername("Cos");
		System.out.println(TAG+"setter:" + m.getUsername());
		return "lombok test 완료";
		
	}
	//인터넷 브라우저 요청은 get밖에 할수없다.
	//http://localhost:8080/http/get select
	@GetMapping("/http/get")
	public String getTest(member m) {
		return "get 요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
		
	}
	
	//insert
	@PostMapping("/http/post") //text/palin <<평문을 보냇다,application/json
	public String PostTest(@RequestBody member m) {
		return "post 요청 :"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//update
	@PutMapping("/http/put")
	public String putTest(@RequestBody member m) {
		return "put 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
			
		
		
}
