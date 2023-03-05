package com.cosblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cosblog.config.auth.PrincipalDetailService;

//빈 등록:스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈등록(Ioc관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class securityConfig {

	@Autowired
	private PrincipalDetailService principalDetailService;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
					.authorizeRequests()
					.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**") // 허용하는 페이지
					.permitAll().anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/auth/loginForm")
					.loginProcessingUrl("/auth/loginProc")
					.defaultSuccessUrl("/");// 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인
																				// 해준다.
		return http.build();
	}
}
