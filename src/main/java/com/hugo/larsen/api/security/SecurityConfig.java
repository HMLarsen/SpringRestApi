package com.hugo.larsen.api.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hugo.larsen.api.security.jwt.JwtTokenFilter;

/**
 * Configuração global de segurança da aplicação.
 * 
 * @author hugo
 */
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
		return http.build();
	}

}