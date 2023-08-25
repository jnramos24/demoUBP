package com.ubp.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ubp.demo.models.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
	
	public Boolean existsByNombreUsuario(String nombreUsuario);
	
	public Boolean existsByEmail(String Email);
}
