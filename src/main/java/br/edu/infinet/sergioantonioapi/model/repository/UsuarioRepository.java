package br.edu.infinet.sergioantonioapi.model.repository;

import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
