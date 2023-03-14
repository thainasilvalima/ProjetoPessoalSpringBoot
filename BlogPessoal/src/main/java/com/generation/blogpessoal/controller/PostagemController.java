package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}

}


/* Linha 10 = define que a classe é do tipo RestController, que receberá requisições
 * que serão compostas por URL, Verbo, corpo da requisição .
 * Após receber e processar a requisição, a classe controladora responderá a estas 
 * requisições com: um código de status HTTP pertinente a operação que está sendo realizada, 
 * resultado do processamento inserido diretamente no corpo da resposta.*/

/* Linha 11 = é usada para mapear as solicitações para os métodos da classe controladora
 * PostagemController, define a URL padrão do recurso (/postagens)*/

 /*Linha 23 = mapeia todas as requisições HTTP GET, enviadas para um endereço específico
  chamado endpoint, dentro do recurso postagem, para um método específico que responderá
  a requisição, ele indica que o método getAll responderá todas as requisições do tipo HTTP GET
  enviadas no end http://localhost:8080/postagens/ */

/* Linha 17 = indica que a classe controladora permitirá o recebimento de requisições realizadas
 * de fora do domínio ao qual ela pertence.*/

/* Linha 15 = é a implementação utilizada pelo Spring Framework para aplicar a Inversão 
   de Controle quando necessário */

/* Linha 24 = o método getAll() será do tipo ResponseEntity pq ele responderá a Requisição
 * HTTP com uma resposta HTTP.
 * O método <List<Postagem>> além de retornar um obj da classe ResponseEntity, no parâmetro corpo da resposta
 * será retornado um obj da classe List (Collection), contendo todos os Objetos da classe Postagem persistidos no bd na tabela tb_postagens.*/

/*	Executa o Método findAll() (Método padrão da Interface JpaRepository), que retornará todos os Objetos da
 *  Classe Postagem persistidos no Banco de dados)*/
 