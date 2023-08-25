package com.ubp.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTO {

	private Integer id;
	private String titulo;
	private String descripcion;
	private String contenido;

}
