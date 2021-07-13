package com.example.securingweb.config;

import com.example.securingweb.SimpleResponseDTO;
import com.example.securingweb.auth.OurUserDetailsService;
import com.example.securingweb.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private  OurUserDetailsService ourUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable();
		http.authorizeRequests().antMatchers("/","/api/login","/api/logout").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
		http.exceptionHandling().authenticationEntryPoint(new JsonHttp403ForbiddenEntryPoint());

		http.authorizeRequests().antMatchers("/**").authenticated();

	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	@Override
	public UserDetailsService userDetailsService() {
		return ourUserDetailsService;
	}

	class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint{

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
				// output JSON message
				// for now just print string
			String ajaxJson = AjaxUtils.convertToString(
						SimpleResponseDTO
								.builder()
								.success(true)
								.message("Forbidden")
								.build()
			);
			response.getWriter().println(ajaxJson);
			response.setContentType("application/json");
			response.getWriter().println(ajaxJson);
		}
	}
}
