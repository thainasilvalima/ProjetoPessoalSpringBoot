/* interface em Java é uma Classe Abstrata (uma Classe que serve de modelo para
 *  outras Classes), composta somente por Métodos abstratos*/


package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;


/* indica que a interface é do tipo repositório, é responsável pela
interação com o bd através dos métodos padrão (herdados da interface JPA repository*/

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> { /* declaração da interface foi adicionada a herança
 através da palavra extends com a interface JpaRpository, que recebe 2 parâmetros a classe Postagem e Long.*/

}
