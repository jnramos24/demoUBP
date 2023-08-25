package com.ubp.demo.respositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ubp.demo.models.Publicacion;
import com.ubp.demo.repositories.PublicacionRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicacionRepositorioTest {

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	private Publicacion publicacion;

	@BeforeEach
	void setup() {
		publicacion = new Publicacion("titulo generico", "descripcion generica", "contenido generico");
	}

	@DisplayName("Test para guardar una publicacion")
	@Test
	void testGuardarPublicacion() {
		// given condicion previa o configuracion
		// Publicacion publicacion= new Publicacion("titulo generico","descripcion
		// generica","contenido generico");
		// when accion o comportamiento a testear
		Publicacion publicacionBD = publicacionRepositorio.save(publicacion);
		// then verificar la salida o comportamiento esperado
		assertThat(publicacionBD).isNotNull();
		assertThat(publicacionBD.getId()).isGreaterThan(0);

	}

	@DisplayName("test para listar publicaciones")
	@Test
	void testListarPublicaciones() {
		// given condicion previa o configuracion
		Publicacion publicacion1 = new Publicacion("titulo", "descripcion", "contenido");
		publicacionRepositorio.save(publicacion1);
		publicacionRepositorio.save(publicacion);
		// when accion o comportamiento a testear
		List<Publicacion> listaBD = publicacionRepositorio.findAll();
		// then verificar la salida o comportamiento esperado
		assertThat(listaBD).isNotNull();
		assertThat(listaBD.size()).isEqualTo(2);
	}

	@DisplayName("test para obtener una publicacion por ID")
	@Test
	void testObtenerPublicacionPorId() {
		publicacionRepositorio.save(publicacion);

		Publicacion publicacionBD = publicacionRepositorio.findById(publicacion.getId()).get();

		assertThat(publicacionBD).isNotNull();
	}

	@DisplayName("test para actualizar publicacion")
	@Test
	void testActualizarPublicacion() {
		publicacionRepositorio.save(publicacion);

		Publicacion publicacionBD = publicacionRepositorio.findById(publicacion.getId()).get();

		publicacionBD.setTitulo("titulo cambiado");
		publicacionBD.setDescripcion("descripcion cambiada");
		publicacionBD.setContenido("contenido cambiado");

		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);

		assertThat(publicacionActualizada.getTitulo()).isEqualTo("titulo cambiado");
		assertThat(publicacionActualizada.getDescripcion()).isEqualTo("descripcion cambiada");
		assertThat(publicacionActualizada.getContenido()).isEqualTo("contenido cambiado");

	}

	@DisplayName("test para eliminar una publicacion")
	@Test
	void testEliminarPublicacion() {
		publicacionRepositorio.save(publicacion);

		publicacionRepositorio.deleteById(publicacion.getId());

		Optional<Publicacion> publicacionBorrada = publicacionRepositorio.findById(publicacion.getId());

		assertThat(publicacionBorrada).isEmpty();

	}

}
