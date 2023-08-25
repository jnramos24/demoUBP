package com.ubp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ubp.demo.models.Publicacion;


public interface PublicacionRepositorio extends JpaRepository<Publicacion, Integer> {

}
