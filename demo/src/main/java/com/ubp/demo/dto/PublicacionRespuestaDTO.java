package com.ubp.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class PublicacionRespuestaDTO {
	
	private List<PublicacionDTO> contenido;
	private int numeroDePagina;
	private int tamañoPagina;
	private Long cantidadElementos;
	private int cantidadPaginas;
	private Boolean ultima;
	
}
