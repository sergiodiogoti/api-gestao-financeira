package br.edu.infinet.sergioantonioapi.controller;

import br.edu.infinet.sergioantonioapi.model.domain.Conta;
import br.edu.infinet.sergioantonioapi.model.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

	private final ContaService contaService;
	
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
	@PostMapping
	public Conta incluir(@RequestBody Conta conta) {
		return contaService.incluir(conta);
	}
		
	@PutMapping(value = "/{id}") 
	public Conta alterar(@PathVariable Integer id, @RequestBody Conta conta) {
		return contaService.alterar(id, conta);
	}
	
	@DeleteMapping(value = "/{id}")
	public void excluir(@PathVariable Integer id) {
		contaService.excluir(id);
	}
	
	@PatchMapping(value = "/{id}/principal")
	public Conta marcarComoPrincipal(@PathVariable Integer id) {
		return contaService.marcarComoPrincipal(id);
	}
	
	@GetMapping
	public List<Conta> obterLista(){
		return contaService.obterLista();
	}
	
	@GetMapping(value = "/{id}")
	public Conta obterPorId(@PathVariable Integer id) {
		return contaService.obterPorId(id);
	}	
}