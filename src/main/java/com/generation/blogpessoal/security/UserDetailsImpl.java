package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails{ 	/*implementa a Interface UserDetails, que tem como principal funcionalidade fornecer as informações básicas do usuário para o Spring Security (Usuário, Senha, Direitos de acesso e as Restrições da conta).*/

private static final long serialVersionUID =1L;
	
	private String userName;
	private String password;
	
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl (Usuario user){
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl (){ }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return authorities;
	}

	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

    @Override
	public boolean isEnabled() {
		
		return true;
	}

}

/* 	linha 46 = isAccountNonExpired() Indica se a conta do usuário expirou. Uma conta expirada não pode ser autenticada (return false).
 	linha = 52 = isAccountNonLocked() Indica se o usuário está bloqueado ou desbloqueado. Um usuário bloqueado não pode ser autenticado (return false).
 	linha 58 = isCredentialsNonExpired() Indica se as credenciais do usuário (senha) expiraram. Senha expirada impede a autenticação (return false).
	linha 64 = isEnabled() Indica se o usuário está habilitado ou desabilitado. Um usuário desabilitado não pode ser autenticado (return false).*/
/* 	linha 28 = getAuthorities(), que retorna a lista com os direitos de acesso do usuário, sempre retornará uma List vazia, porquê este Atributo não pode ser Nulo.*/