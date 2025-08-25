package br.edu.infinet.sergioantonioapi.model.service;

import br.edu.infinet.sergioantonioapi.model.domain.Conta;
import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.ContaInvalidaException;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.ContaNaoEncontradaException;
import br.edu.infinet.sergioantonioapi.model.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContaService implements CrudService<Conta, Integer> {


	private final UsuarioService usuarioService;
	private final ContaRepository contaRepository;

	public ContaService(UsuarioService usuarioService, ContaRepository contaRepository) {
		this.usuarioService = usuarioService;
		this.contaRepository = contaRepository;
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
	@Transactional
	public Conta incluir(Conta conta) {
		validar(conta);
		if(conta.getId() != null && conta.getId() != 0) {
			throw new IllegalArgumentException("Uma nova conta não pode ter um ID na inclusão!");
		}
		if(conta.getId() != null && conta.getSaldoInicial() < 0) {
			throw new IllegalArgumentException("Uma nova conta não pode ter saldo negativo");
		}
		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);

		return contaRepository.save(conta);
	}

	@Override
	@Transactional
	public Conta alterar(Integer id, Conta conta) {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");			
		}
		validar(conta);
		obterPorId(id);
		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);
		conta.setId(id);

		return contaRepository.save(conta);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");			
		}
		obterPorId(id);
		contaRepository.deleteById(id);
	}

	@Transactional
	public Conta marcarComoPrincipal(Integer id) {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para marcar a conta como principal não pode ser nulo/zero!");
		}
		Conta conta = obterPorId(id);
		if(!conta.isPrincipal()) {
			conta.setPrincipal(true);
			contaRepository.save(conta);
		}
		return conta;
	}

	@Override
	public List<Conta> obterLista() {
		return contaRepository.findAll();
	}

	@Override
	public Conta obterPorId(Integer id) {
		return contaRepository.findById(id)
				.orElseThrow(() -> new ContaNaoEncontradaException("A Conta com ID " + id + " não foi encontrada!"));
	}
} 