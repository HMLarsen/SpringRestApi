package com.hugo.larsen.api.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hugo.larsen.api.security.jwt.JwtTokenFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.headers().frameOptions().disable().and()
			// gerenciamento stateless da sessão
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// exceção padrão para rotas não autorizadas
			.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
			}).and()
			// endpoints
			.authorizeRequests()
			.antMatchers("/public/**").permitAll()
			.antMatchers("/auth/**").authenticated().and()
			// filtro JWT
			.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

}