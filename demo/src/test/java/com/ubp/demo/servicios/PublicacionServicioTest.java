package com.ubp.demo.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ubp.demo.dto.PublicacionDTO;
import com.ubp.demo.dto.PublicacionRespuestaDTO;
import com.ubp.demo.models.Publicacion;
import com.ubp.demo.repositories.PublicacionRepositorio;
import com.ubp.demo.services.PublicacionServicioImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PublicacionServicioTest {

	@Mock
	private PublicacionRepositorio publicacionRepositorio;
	
	@InjectMocks
	private PublicacionServicioImpl publicacionServicio;
	
	private Publicacion publicacion;
	private PublicacionDTO publicacionDto;
	
	@BeforeEach
	void setup() {
		publicacion = new Publicacion("titulo generico", "descripcion generica", "contenido generico");
		publicacionDto = new PublicacionDTO(1,"titulo generico","descripcion generica","contenido generico");
	}
	
	@DisplayName("Test para guardar una publicacion")
	@Test
	void testGuardarPublicacion() {
		given(publicacionRepositorio.save(publicacion)).willReturn(publicacion);
		
		PublicacionDTO publicacionGuardada = publicacionServicio.crearPublicacion(publicacionDto);
		
		assertThat(publicacionGuardada).isNotNull();
	
	}
	
	@DisplayName("Test para listar publicaciones")
	@Test
	void testListarPublicaciones() {
		Publicacion publicacion2 = new Publicacion("titulo generico2", "descripcion generica2", "contenido generico2");
		
		List<Publicacion> listaPublicaciones = Arrays.asList(publicacion,publicacion2);
		Page<Publicacion> paginaPublicaciones = new PageImpl<>(listaPublicaciones);
		
		given(publicacionRepositorio.findAll(any(Pageable.class))).willReturn(paginaPublicaciones);
		
		PublicacionRespuestaDTO publicacionesBD = publicacionServicio.listarPublicaciones(0, 5);
		
		assertThat(publicacionesBD.getContenido()).isNotNull();
		assertThat(publicacionesBD.getContenido().size()).isEqualTo(2);		
	}
	
	@DisplayName("Test para actualizar publicaciones")
	@Test
	void testActualizarPublicacion() {
		
		
		given(publicacionRepositorio.save(publicacion)).willReturn(publicacion);
		given(publicacionRepositorio.findById(publicacion.getId())).willReturn(Optional.of(publicacion));
		
		publicacion.setTitulo("titulo actualizado");
		publicacion.setDescripcion("descripcion actualizado");
		publicacion.setContenido("contenido actualizado");
		
		given(publicacionRepositorio.save(publicacion)).willReturn(publicacion);
		
		publicacionDto.setTitulo("titulo actualizado");
		publicacionDto.setDescripcion("descripcion actualizado");
		publicacionDto.setContenido("contenido actualizado");
		
		PublicacionDTO resultado = publicacionServicio.actualizarPublicacion(publicacionDto, publicacion.getId());
		
		assertThat(resultado.getTitulo()).isEqualTo("titulo actualizado");
		assertThat(resultado.getDescripcion()).isEqualTo("descripcion actualizado");
		assertThat(resultado.getContenido()).isEqualTo("contenido actualizado");
	}
	
	
	
}
