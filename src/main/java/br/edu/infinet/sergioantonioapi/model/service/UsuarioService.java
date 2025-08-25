package br.edu.infinet.sergioantonioapi.model.service;

import java.util.List;
import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.UsuarioInvalidoException;
import br.edu.infinet.sergioantonioapi.model.domain.exceptions.UsuariorNaoEncontradoException;
import br.edu.infinet.sergioantonioapi.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements CrudService<Usuario, Integer> {


	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	private void validar(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("O usuario não pode estar nulo!");
		}
		if(usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			throw new UsuarioInvalidoException("O nome do usuario é uma informação obrigatória!");
		}		
	}

	@Override
	@Transactional
	public Usuario incluir(Usuario usuario) {
		validar(usuario);
		if(usuario.getId() != null && usuario.getId() != 0) {
			throw new IllegalArgumentException("Um novo usuario não pode ter um ID na inclusão!");			
		}
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public Usuario alterar(Integer id, Usuario usuario) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");			
		}
		validar(usuario);
		obterPorId(id);
		usuario.setId(id);
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void excluir(Integer id)  {
		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");			
		}
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuariorNaoEncontradoException("O usuario com ID " + id + " não foi encontrado!"));
		usuarioRepository.delete(usuario);
	}

	@Transactional
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
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> obterLista() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario obterPorId(Integer id) {
        return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuariorNaoEncontradoException("O usuario com ID " + id + " não foi encontrado!"));
	}
} 