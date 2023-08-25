package com.ubp.demo.controladores;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ubp.demo.dto.PublicacionDTO;
import com.ubp.demo.dto.PublicacionRespuestaDTO;
import com.ubp.demo.services.PublicacionServicioImpl;

@WebMvcTest(PublicacionControlador.class)
public class PublicacionControladorTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PublicacionServicioImpl publicacionServicio;
	
	@DisplayName("Test para obtener todas las publicaciones")
	@Test
	void testObtenerTodasLasPublicaciones() throws Exception {
		List<PublicacionDTO> contenidoSimulado = new ArrayList<>();
		
		PublicacionRespuestaDTO respuestaSimulada = new PublicacionRespuestaDTO();
		
		respuestaSimulada.setContenido(contenidoSimulado);
		respuestaSimulada.setCantidadElementos(2L);
		respuestaSimulada.setTamañoPagina(5);
		respuestaSimulada.setNumeroDePagina(0);
		respuestaSimulada.setCantidadPaginas(1);
		respuestaSimulada.setUltima(true);
		
		when(publicacionServicio.listarPublicaciones(0, 5)).thenReturn(respuestaSimulada);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/apiV1/publicaciones"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();	
	}
	
	@DisplayName("Test para obtener todas las publicaciones")
	@WithMockUser(roles = "ADMIN")
	@Test
	void guardarPublicacionTest() throws Exception{
	    PublicacionDTO publicacionDtoSimulado = new PublicacionDTO(null, "titulo simulado", "descripcion simulada", "contenido simulado");
	    PublicacionDTO publicacionRespuestaSimulada = new PublicacionDTO(1, "titulo simulado", "descripcion simulada", "contenido simulado");
	    
	    when(publicacionServicio.crearPublicacion(ArgumentMatchers.eq(publicacionDtoSimulado))).thenReturn(publicacionRespuestaSimulada);
	    
	    mockMvc.perform(MockMvcRequestBuilders.post("/apiV1/publicaciones")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content("{\"titulo\":\"titulo simulado\",\"descripcion\":\"descripcion simulada\",\"contenido\":\"contenido simulado\"}"))
	    .andExpect(MockMvcResultMatchers.status().isCreated())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("titulo simulado"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("descripcion simulada"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.contenido").value("contenido simulado"))
        .andReturn();
	}
	
	
	
	
	
	
	
}
