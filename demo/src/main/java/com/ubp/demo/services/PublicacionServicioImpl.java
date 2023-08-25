package com.ubp.demo.services;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubp.demo.dto.PublicacionDTO;
import com.ubp.demo.dto.PublicacionRespuestaDTO;
import com.ubp.demo.excepciones.ResourceNotFoundException;
import com.ubp.demo.models.Publicacion;
import com.ubp.demo.repositories.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	private Publicacion mapearDto(PublicacionDTO publicacionDto) {
		Publicacion publicacion = new Publicacion();

		publicacion.setTitulo(publicacionDto.getTitulo());
		publicacion.setDescripcion(publicacionDto.getDescripcion());
		publicacion.setContenido(publicacionDto.getContenido());

		return publicacion;
	}

	private PublicacionDTO mapearEntidad(Publicacion publicacion) {
		PublicacionDTO publicacionRespuesta = new PublicacionDTO();

		publicacionRespuesta.setId(publicacion.getId());
		publicacionRespuesta.setTitulo(publicacion.getTitulo());
		publicacionRespuesta.setDescripcion(publicacion.getDescripcion());
		publicacionRespuesta.setContenido(publicacion.getContenido());

		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDto) {
		// Convierto de Dto a Entidad

		Publicacion publicacion = mapearDto(publicacionDto);

		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

		// Convierto de Entidad a Dto
		PublicacionDTO publicacionRespuesta = mapearEntidad(nuevaPublicacion);

		return publicacionRespuesta;
	}

	@Override
	public PublicacionRespuestaDTO listarPublicaciones(int numeroDePagina, int TamañoDePagina) {
		Pageable pageable = PageRequest.of(numeroDePagina, TamañoDePagina);
		Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);
		List<Publicacion> publicacionesPaginadas = publicaciones.getContent();
		
		List<PublicacionDTO> contenido = new ArrayList<>();
		
		for (Publicacion publicacion : publicacionesPaginadas) {
			contenido.add(mapearEntidad(publicacion));
		}
		
		PublicacionRespuestaDTO publicacionRespuesta = new PublicacionRespuestaDTO();
		
		publicacionRespuesta.setContenido(contenido);
		publicacionRespuesta.setCantidadPaginas(publicaciones.getTotalPages());
		publicacionRespuesta.setTamañoPagina(publicaciones.getSize());
		publicacionRespuesta.setCantidadElementos((publicaciones.getTotalElements()));
		publicacionRespuesta.setNumeroDePagina(publicaciones.getNumber());
		publicacionRespuesta.setUltima(publicaciones.isLast());

		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO buscarPublicacionPorId(Integer id) {
		Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("publicacion","id", id));
		return mapearEntidad(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDto, Integer id) {
		Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("publicacion","id", id));
		
		publicacion.setTitulo(publicacionDto.getTitulo());
		publicacion.setDescripcion(publicacionDto.getDescripcion());
		publicacion.setContenido(publicacionDto.getContenido());
		
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
		
		return mapearEntidad(nuevaPublicacion);
	}

	@Override
	public void eliminarPublicacion(Integer id) {
		Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("publicacion","id", id));
		publicacionRepositorio.delete(publicacion);
	}

}
