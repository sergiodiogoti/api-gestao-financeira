package br.edu.infinet.sergioantonioapi.controller;

import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.service.UsuarioService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public Usuario incluir(@RequestBody Usuario usuario) {
		return usuarioService.incluir(usuario);
	}
		
	@PutMapping(value = "/{id}")
	public Usuario alterar(@PathVariable Integer id, @RequestBody Usuario usuario) {
		return usuarioService.alterar(id, usuario);
	}
	
	@DeleteMapping(value = "/{id}")
	public void excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
	}
	
	@PatchMapping(value = "/{id}/inativar")
	public Usuario inativar(@PathVariable Integer id) {
		return usuarioService.inativar(id);
	}
	
	@GetMapping
	public List<Usuario> obterLista(){
		return usuarioService.obterLista();
	}
	
	@GetMapping(value = "/{id}")
	public Usuario obterPorId(@PathVariable Integer id) {
		return usuarioService.obterPorId(id);
	}	
}