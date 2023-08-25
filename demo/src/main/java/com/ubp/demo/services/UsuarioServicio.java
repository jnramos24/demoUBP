package com.ubp.demo.services;

import java.util.List;

import com.ubp.demo.dto.UsuarioDto;

public interface UsuarioServicio {
	
	public List<UsuarioDto> obtenerUsuarios();
	
	public void guardarUsuario(UsuarioDto usuarioDto);
	
	public UsuarioDto obtenerUsuarioPorId(Integer id);
	
	public void actualizarUsuario(UsuarioDto usuarioDto, Integer id);
	
	public void eliminarUsuario(Integer id);

}
