package com.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

	@Service
	public class UserDetailsServiceImpl implements UserDetailsService { 	/*permite autenticar um usuário baseando-se na sua existência no Banco de dados (em nosso caso na tabela tb_usuarios). */
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException { 	/*recebe o usuário através da tela de login do sistema e utiliza a Query Method findByUsuario(String usuario), implementada na Interface UsuarioRepository, para checar se o usuário digitado está persistido no Banco de dados.*/

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

		if (usuario.isPresent()) 	/*ele executa o construtor da Classe UserDetailsImpl, passando o Objeto usuario como parâmetro. Observe que foi utilizado Método get() no Objeto usuario por se tratar de um Optional.*/
			return new UserDetailsImpl(usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}
}
