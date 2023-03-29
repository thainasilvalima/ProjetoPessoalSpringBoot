package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;

	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class UsuarioControllerTest {

		@Autowired
		private TestRestTemplate testRestTemplate;

		@Autowired
		private UsuarioService usuarioService;

		@Autowired
		private UsuarioRepository usuarioRepository;

		@BeforeAll
		void start(){

			usuarioRepository.deleteAll();

			usuarioService.cadastrarUsuario(new Usuario(0L, 
				"Root", "root@root.com", "rootroot", " "));

		}
		
	@Test
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmUsuario() {

			HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, 
				"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

			ResponseEntity<Usuario> corpoResposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

			assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
			assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
			assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
		
		}
	
	@Test
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Atualizar um Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "https://i.imgur.com/yDRVeK7.jpg"));

		Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(), 
			"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123" , "https://i.imgur.com/yDRVeK7.jpg");
		
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);

		ResponseEntity<Usuario> corpoResposta = testRestTemplate
			.withBasicAuth("root@root.com", "rootroot")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}
	
	@Test
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123", "https://i.imgur.com/5M2p5Wb.jpg"));
		
		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123", "https://i.imgur.com/Sk5SjWE.jpg"));

		ResponseEntity<String> resposta = testRestTemplate
		.withBasicAuth("root@root.com", "rootroot")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}
		
		
}
	
	/* linha 24 = @SpringBootTest indica que a classe UsuarioControllerTest é uma classe Spring Boot Testing, a opc environment indica que caso a porta principal 8080 para uso local esteja ocupada, o spring irá atribuir uma outra porta automaticamente*/
	/* linha = 25 @TestInstance indica que o Ciclo de vida da Classe de Teste será por Classe.
	linha 28 = @Autowired um objeto da Classe TestRestTemplate para enviar as requisições para a nossa aplicação.
	linha 31 = @Autowired, um objeto da Classe UsuarioService para persistir os objetos no Banco de dados de testes com a senha criptografada.
	linha 34 = @Autowired, um objeto da Interface UsuarioRepository para limpar o Banco de dados de testes.
	linha 37 = start() anotado com a anotação @BeforeAll, apaga todos os dados da tabela e cri o usuário root@root.com para testar os Métodos protegidos por autenticação.*/
	
/*	linha 49 =	deveCriarUmUsuario() foi antotado com a anotação @Test que indica que este Método executará um teste.
	linha 48 =	@DisplayName configura uma mensagem que será exibida ao invés do nome do Método.
	linha 51 =	HttpEntity chamado requisicao, recebendo um objeto da Classe Usuario. Nesta etapa, o processo é equivalente ao que o Postman faz em uma requisição do tipo POST: Transforma os Atributos num objeto da Classe Usuario, que será enviado no corpo da requisição (Request Body).
	linha 54 =	Requisição HTTP será enviada através do Método exchange() da Classe TestRestTemplate e a Resposta da Requisição (Response) será recebida pelo objeto respostado tipo ResponseEntity
	linha 57 =	AssertEquals(), checaremos se a resposta da requisição (Response), é a resposta esperada (CREATED 🡪 201). Para obter o status da resposta vamos utilizar o Método getStatusCode() da Classe ResponseEntity.
	linha 58 =	AssertEquals(), checaremos se o nome e o usuário(e-mail) enviados na requisição foram persistidos no Banco de Dados.
	linha 59 =	getBody() faremos o acesso aos objetos requisição e resposta, que estão no corpo (body) tanto da requisição quanto da resposta, e através dos Métodos getNome() e getUsuario() faremos o acesso aos Atributos que serão comparados.*/
	
	
	
	
	
	