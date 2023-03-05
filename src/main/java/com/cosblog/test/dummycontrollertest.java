package com.cosblog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cosblog.model.RoleType;
import com.cosblog.model.User;
import com.cosblog.repository.UserRepository;

@RestController
public class dummycontrollertest {

	@Autowired//의존성 주입
	private UserRepository userRepository;
	
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요
	// email,password
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}

		
		return "삭제되었습니다.id:"+id ;
	}
	
	
	@Transactional //세이브 문항을 안적어도 이게 자동으로 세이브해줌 //함수 종료시에 자동 commint이 됨.<- 데이터베이스에 저장
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User user=userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하엿습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
	//	userRepository.save(user);
		
	//더티 체킹	
		return user;
	} 
	
	@GetMapping("/dummy/users")
	public  List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser=userRepository.findAll(pageable);
		
		List<User> users=pagingUser.getContent();
		return pagingUser;
	}
	//http://localhost:800/blog/dummy/join (요청)
	//http의  body에 username,password,emial 데이터를 가지고 (요청)

	
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		
     // 람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다.");
		});
		
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 사용자가 없습니다.");
//			}
//		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value (약속된 규칙)
		
		System.out.println("id:"+user.getId());
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user); 
		
	
		return "회원가입이 완료되었습니다.";
	}
}
