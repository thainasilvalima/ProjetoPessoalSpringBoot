/* interface em Java é uma Classe Abstrata (uma Classe que serve de modelo para
 *  outras Classes), composta somente por Métodos abstratos*/

/* linha 20 declaração da interface foi adicionada a herança
 através da palavra extends com a interface JpaRpository, que recebe 2 parâmetros a classe Postagem e Long.*/

/* linha 19 indica que a interface é do tipo repositório, é responsável pela
interação com o bd através dos métodos padrão (herdados da interface JPA repository*/


package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;


	@Repository
	public interface PostagemRepository extends JpaRepository<Postagem, Long> { 

	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
