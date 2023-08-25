package com.ubp.demo.services;

import com.ubp.demo.dto.PublicacionDTO;
import com.ubp.demo.dto.PublicacionRespuestaDTO;

public interface PublicacionServicio {
	
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDto);
	
	public PublicacionRespuestaDTO listarPublicaciones(int numeroDePagina, int TamañoDePagina);
	
	public PublicacionDTO buscarPublicacionPorId(Integer id);
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDto, Integer id);
	
	public void eliminarPublicacion(Integer id);

}
