package com.ubp.demo.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ubp.demo.dto.UsuarioDto;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String URL_BASE = "https://64d3de0267b2662bf3dcd173.mockapi.io/apiExt/User/";

	@Override
	public List<UsuarioDto> obtenerUsuarios() {
		UsuarioDto[] respuesta = restTemplate.getForObject(URL_BASE, UsuarioDto[].class);
		return Arrays.asList(respuesta);
	}
	
	
	public UsuarioDto obtenerUsuarioPorId(Integer id) {
		UsuarioDto userDto = restTemplate.getForObject(URL_BASE + id, UsuarioDto.class);
		return userDto;
	}

	@Override
	public void guardarUsuario(UsuarioDto usuarioDto) {
		restTemplate.postForObject(URL_BASE,usuarioDto ,UsuarioDto.class);
	}

	@Override
	public void actualizarUsuario(UsuarioDto usuarioDto, Integer id) {
		restTemplate.put(URL_BASE + id, usuarioDto, UsuarioDto.class);
		
	}

	@Override
	public void eliminarUsuario(Integer id) {
		restTemplate.delete(URL_BASE + id);	
	}

}
