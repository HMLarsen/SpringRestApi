package com.hugo.larsen.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo.larsen.api.domain.model.Usuario;

/**
 * Repositório para {@link Usuario}.
 * 
 * @author hugo
 */
public interface UserRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsuario(String usuario);

}
