package com.ubp.demo.controladores;


import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.ubp.demo.dto.UsuarioDto;
import com.ubp.demo.services.UsuarioServicio;

@RestController
@RequestMapping("/apiV1/usuarios")
public class UsuarioControlador {
	
	@Autowired
	UsuarioServicio usuarioServicio;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> traerUsuarios(){
		return new ResponseEntity<>(usuarioServicio.obtenerUsuarios(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> traerUsuarioPorId(@PathVariable(name="id")Integer id){
		return new ResponseEntity<>(usuarioServicio.obtenerUsuarioPorId(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
		usuarioServicio.guardarUsuario(usuarioDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable(name = "id") Integer id, @RequestBody UsuarioDto usuarioDto) {
		usuarioServicio.actualizarUsuario(usuarioDto, id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable(name = "id") Integer id) {
		usuarioServicio.eliminarUsuario(id);
	}
	
}
