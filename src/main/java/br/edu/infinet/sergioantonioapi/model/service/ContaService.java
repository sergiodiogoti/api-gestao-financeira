package br.edu.infinet.sergioantonioapi.model.service;

import br.edu.infinet.sergioantonioapi.model.domain.Conta;
import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.ContaInvalidaException;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.ContaNaoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ContaService implements CrudService<Conta, Integer> {

	private final Map<Integer, Conta> mapa = new ConcurrentHashMap<Integer, Conta>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	private final UsuarioService usuarioService;

	public ContaService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}


	private void validar(Conta conta) {
		if(conta == null) {
			throw new IllegalArgumentException("A conta não pode estar nula!");
		}
		if(conta.getNome() == null || conta.getNome().trim().isEmpty()) {
			throw new ContaInvalidaException("O nome do conta é uma informação obrigatória!");
		}
		if(conta.getUsuario() == null || conta.getUsuario().getId() == null) {
			throw new ContaInvalidaException("O id do usuario é um campo obrigatório!");
		}
		if(conta.getTipo() == null || conta.getTipo().trim().isEmpty()) {
			throw new ContaInvalidaException("O tipo de conta é uma informação obrigatória!");
		}
	}
	
	@Override
	public Conta incluir(Conta conta) {
		
		validar(conta);
		
		if(conta.getId() != null && conta.getId() != 0) {
			throw new IllegalArgumentException("Uma nova conta não pode ter um ID na inclusão!");
		}

		if(conta.getId() != null && conta.getSaldoInicial() < 0) {
			throw new IllegalArgumentException("Uma nova conta não pode ter saldo negativo");
		}

		conta.setId(nextId.getAndIncrement());

		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);
		
		mapa.put(conta.getId(), conta);
		
		return conta;
	}

	@Override
	public Conta alterar(Integer id, Conta conta) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");			
		}
		
		validar(conta);
		
		obterPorId(id);
		
		conta.setId(id);

		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);

		mapa.put(conta.getId(), conta);
		
		return conta;
	}

	@Override
	public void excluir(Integer id) {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");			
		}
		
		if(!mapa.containsKey(id)) {
			throw new ContaNaoEncontradaException("A conta com ID " + id + " não foi encontrado!");
		}

		mapa.remove(id);
	}

	public Conta marcarComoPrincipal(Integer id) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para marcar a conta como principal não pode ser nulo/zero!");
		}
		
		Conta conta = obterPorId(id);
		
		if(!conta.isPrincipal()) {
			conta.setPrincipal(true);
		}else{
			return conta;
		}
		mapa.put(conta.getId(), conta);
		return conta;
	}


	@Override
	public List<Conta> obterLista() {
		
		return new ArrayList<Conta>(mapa.values());
	}

	@Override
	public Conta obterPorId(Integer id) {

		Conta conta = mapa.get(id);
		
		if(conta == null) {
			throw new IllegalArgumentException("Imposível obter a conta pelo ID " + id);
		}
		
		return conta;
	}
} 