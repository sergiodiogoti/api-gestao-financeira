package br.edu.infinet.sergioantonioapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.UsuarioInvalidoException;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.UsuariorNaoEncontradoException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements CrudService<Usuario, Integer> {

	private final Map<Integer, Usuario> mapa = new ConcurrentHashMap<Integer, Usuario>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	private void validar(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("O usuario não pode estar nulo!");
		}

		if(usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			throw new UsuarioInvalidoException("O nome do usuario é uma informação obrigatória!");
		}		
	}
	
	@Override
	public Usuario incluir(Usuario usuario) {
		
		validar(usuario);
		
		if(usuario.getId() != null && usuario.getId() != 0) {
			throw new IllegalArgumentException("Um novo usuario não pode ter um ID na inclusão!");			
		}
		
		usuario.setId(nextId.getAndIncrement());		
		
		mapa.put(usuario.getId(), usuario);
		
		return usuario;
	}

	@Override
	public Usuario alterar(Integer id, Usuario usuario) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");			
		}
		
		validar(usuario);
		
		obterPorId(id);
		
		usuario.setId(id);

		mapa.put(usuario.getId(), usuario);
		
		return usuario;
	}

	@Override
	public void excluir(Integer id) {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");			
		}
		
		if(!mapa.containsKey(id)) {
			throw new UsuariorNaoEncontradoException("O usuario com ID " + id + " não foi encontrado!");
		}

		mapa.remove(id);
	}

	public Usuario inativar(Integer id) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");			
		}
		
		Usuario usuario = obterPorId(id);
		
		if(!usuario.isAtivo()) {
			System.out.println("Usuario " + usuario.getNome() + " já está inativo!");
			return usuario;
		}
		
		usuario.setAtivo(false);
		
		mapa.put(usuario.getId(), usuario);
		
		return usuario;
	}
	
	@Override
	public List<Usuario> obterLista() {
		
		return new ArrayList<Usuario>(mapa.values());
	}

	@Override
	public Usuario obterPorId(Integer id) {

		Usuario usuario = mapa.get(id);
		
		if(usuario == null) {
			throw new IllegalArgumentException("Imposível obter o usuario pelo ID " + id);
		}
		
		return usuario;
	}
} 