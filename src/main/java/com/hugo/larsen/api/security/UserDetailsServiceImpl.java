package com.hugo.larsen.api.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hugo.larsen.api.domain.model.Usuario;
import com.hugo.larsen.api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByUsuario(username);
		if (user == null)
			throw new UsernameNotFoundException("Usuário não encontrado!");
		return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());
	}

}
