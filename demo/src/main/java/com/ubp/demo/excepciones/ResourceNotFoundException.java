package com.ubp.demo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter @Setter
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String nombreRecurso;
	private String nombreCampo;
	private Integer valor;
	
	public ResourceNotFoundException(String nombreRecurso, String nombreCampo, Integer valor) {
		super(String.format("%s no encontrada con: %s,'%s'",nombreRecurso,nombreCampo, valor));
		this.nombreRecurso = nombreRecurso;
		this.nombreCampo = nombreCampo;
		this.valor = valor;
	}

}
