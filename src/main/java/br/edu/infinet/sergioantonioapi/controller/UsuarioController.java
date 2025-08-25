package br.edu.infinet.sergioantonioapi.controller;

import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Usuario> incluir(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.incluir(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}
		
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> alterar(@PathVariable Integer id, @RequestBody Usuario usuario) {
		Usuario usuarioAlterado = usuarioService.alterar(id, usuario);
		return ResponseEntity.ok(usuarioAlterado);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}/inativar")
	public ResponseEntity<Usuario> inativar(@PathVariable Integer id) {
		Usuario usuaurio =usuarioService.inativar(id);
		return ResponseEntity.ok(usuaurio);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obterLista(){
		List<Usuario> lista = usuarioService.obterLista();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> obterPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioService.obterPorId(id);
		return ResponseEntity.ok(usuario);
	}	
}