package br.edu.infinet.sergioantonioapi;

import java.io.BufferedReader;
import java.io.FileReader;

import br.edu.infinet.sergioantonioapi.model.domain.Endereco;
import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(1)
@Component
public class UsuarioLoader implements ApplicationRunner {
	
	private final UsuarioService usuarioService;
	
	public UsuarioLoader(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("usuario.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();

		String[] campos = null;
		
		while(linha != null) {
			
			campos = linha.split(";");
			
			Endereco endereco = new Endereco();
			endereco.setCep(campos[8]);
			endereco.setLocalidade(campos[9]);
			
			Usuario usuario = new Usuario();
			usuario.setNome(campos[0]);
			usuario.setPontuacaoCredito(Integer.valueOf(campos[1]));
			usuario.setRendaMensal(Double.valueOf(campos[2]));
			usuario.setAtivo(Boolean.valueOf(campos[3]));
			usuario.setCpf(campos[4]);
			usuario.setEmail(campos[5]);
			usuario.setTelefone(campos[6]);
			usuario.setPerfil(campos[7]);

			usuario.setEndereco(endereco);
			
			usuarioService.incluir(usuario);

			//TODO Imprimir os usuarioes após a leitura do arquivo
			System.out.println(usuario);
			
			linha = leitura.readLine();
		}
		
		//TODO chamada da funcionalidade de alteração
		
		System.out.println("- " + usuarioService.obterLista().size());

		leitura.close();
	}
}
