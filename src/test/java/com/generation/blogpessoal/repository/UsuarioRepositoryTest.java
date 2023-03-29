/*IMPORTANTE: Toda a Classe de teste deve ter no final do seu nome a palavra Test.*/

package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario(0L, "Joao da Silva", "joao@email.com.br", "13265278", "https://i.imgur.com/FETvc20.jpg"));
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13265278", "https://i.imgur.com/NtyGneo.jpg"));
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "13265278", "https://i.imgur.com/mB3VM2N.jpg"));
		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13265278", "https://i.imgur.com/JR7kUFU.jpg"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario()  {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Joao da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll(); 
	}
}

/*	linha 21 = indica que a classe UsuarioRepositoryTest é uma classe Spring Boot Testing, a opc environment indica que caso a porta principal 8080 para uso local esteja ocupada, o spring irá atribuir uma outra porta automaticamente*/
/*	linha 22 = indica que o Ciclo de vida da Classe de Teste será por Classe*/
/* 	linha 25 = obj da interface usuarioRepository para persistir os objts no bd de testes*/ 
/*	linha 29 =  metodo start() anotado com anotaçao BeforeAll  apaga todos os dados da tabela inicializada 4 objts do tipo Usuario (linha 31) o insere no bd de testes atraves do metodo save() uma unica vez*/
/* 	linha 40 = indica que o m[etodo executará um teste*/
/* 	linha 41 = configura uma mensagem que sera exibida ao inves do nome do método*/
/* 	linha 45 = verifica se o usuario cujo email é "joao@email.com.br"foi encontrado, se for encontrado o resultado será aprovado! caso contrário o teste será falhou!*/
/* 	linha 52 = obj listaDeUsuarios recebe o resultado do método findAllByNomeContainingIgnoreCase*/
/* 	linha 53 =  método assertEquals verifica se o tamanho da List é igual a 3, retorna a List se o tamanho for 3 o teste será aprovado!*/
/*	linha 54 = assertTrue verifica em cada posiçao da collection listaDeUsuarios se os usuarios foram gravados na mesma sequencia*/
/* 	linha 59 = método end, na anotaçao AfterAll apaga todos os dados da tabela, depois de todos os testes forem executado.*/

