package br.edu.infinet.sergioantonioapi;

import br.edu.infinet.sergioantonioapi.model.domain.Conta;
import br.edu.infinet.sergioantonioapi.model.domain.Endereco;
import br.edu.infinet.sergioantonioapi.model.domain.Usuario;
import br.edu.infinet.sergioantonioapi.model.service.ContaService;
import br.edu.infinet.sergioantonioapi.model.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;


@Order(2)
@Component
public class ContaLoader implements ApplicationRunner {

	private final UsuarioService usuarioService;
	private final ContaService contaService;

	public ContaLoader(UsuarioService usuarioService, ContaService contaService) {
		this.usuarioService = usuarioService;
		this.contaService = contaService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("conta.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();

		String[] campos = null;
		
		while(linha != null) {

			campos = linha.split(";");

			Usuario usuario = usuarioService.obterPorId(Integer.valueOf(campos[4]));

			Conta conta = new Conta();
			conta.setNome(campos[0]);
			conta.setTipo(campos[1]);
			conta.setSaldoInicial(Double.valueOf(campos[2]));
			conta.setPrincipal(Boolean.valueOf(campos[3]));
			conta.setUsuario(usuario);
			contaService.incluir(conta);

			//TODO Imprimir as contas após a leitura do arquivo
			System.out.println(conta);
			
			linha = leitura.readLine();
		}
		
		//TODO chamada da funcionalidade de alteração
		
		System.out.println("- " + contaService.obterLista().size());

		leitura.close();
	}
}
