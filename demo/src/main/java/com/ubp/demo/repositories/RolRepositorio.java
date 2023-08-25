package com.ubp.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ubp.demo.models.Rol;


public interface RolRepositorio extends JpaRepository<Rol, Long>{
	
	public Optional<Rol> findByNombre(String nombre);

}
