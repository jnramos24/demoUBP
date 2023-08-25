package com.ubp.demo.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubp.demo.dto.PublicacionDTO;
import com.ubp.demo.dto.PublicacionRespuestaDTO;
import com.ubp.demo.services.PublicacionServicio;

@RestController
@RequestMapping("/apiV1/publicaciones")
public class PublicacionControlador {
	
	

	@Autowired
	private PublicacionServicio publicacionServicio;
	
	
	@GetMapping
	public PublicacionRespuestaDTO obtenerTodasLasPublicaciones(
			@RequestParam(value="PagNo", defaultValue="0", required=false)int numeroDePagina,
			@RequestParam(value="PageSize", defaultValue="5", required=false)int tamañoDePagina){
		
		return publicacionServicio.listarPublicaciones(numeroDePagina,tamañoDePagina);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id")Integer id){
		
		return new ResponseEntity<>(publicacionServicio.buscarPublicacionPorId(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardar(@RequestBody PublicacionDTO publicacionDto){
	return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDto), HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacion(
			@RequestBody PublicacionDTO publicacionDto, 
			@PathVariable(name = "id")Integer Id){
		PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDto, Id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id")Integer id){
		publicacionServicio.eliminarPublicacion(id);
		return new ResponseEntity<>("Publicación eliminada correctamente", HttpStatus.OK);
	}
}
