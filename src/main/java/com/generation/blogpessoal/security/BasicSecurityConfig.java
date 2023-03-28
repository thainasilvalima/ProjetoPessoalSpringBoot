package com.generation.blogpessoal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

	@Configuration 										/*indica que a Classe é do tipo configuração, ou seja, define uma Classe como fonte de definições de Beans, além de ser uma das anotações essenciais ao utilizar uma configuração baseada em Java.*/
	@EnableWebSecurity 									/*habilita a segurança de forma Global (toda a aplicação) e sobrescreve os Métodos que irão redefinir as regras de Segurança da sua aplicação.*/
	public class BasicSecurityConfig { 					/*é utilizada para sobrescrever a configuração padrão da Spring Security.*/

	@Bean										 /*formam a espinha dorsal da sua aplicação e que são gerenciados pelo Spring */
	public PasswordEncoder passwordEncoder() { 	/*indica ao Spring Security que a aplicação está baseada em um modelo de criptografia*/
		return new BCryptPasswordEncoder();		/*se trata de um algoritmo de criptografia do tipo hash(é um algoritmo que transforma dados de comprimento variável em dados de comprimento fixo codificados.)*/
	}

	@Bean 										 /*formam a espinha dorsal da sua aplicação e que são gerenciados pelo Spring */
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)		
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean 										 /*formam a espinha dorsal da sua aplicação e que são gerenciados pelo Spring */
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().cors();

		http.authorizeHttpRequests(
				(auth) -> auth.requestMatchers("/usuarios/logar").permitAll()
						.requestMatchers("/usuarios/cadastrar").permitAll()
						.requestMatchers(HttpMethod.GET, "/postagens").permitAll()
						.requestMatchers(HttpMethod.OPTIONS).permitAll()
						.anyRequest()
						.authenticated())
						.httpBasic();

		return http.build();

	}

}

/*linha 25 = implementa a confguração de autenticação. Este Método utiliza o Método authenticationConfiguration.getAuthenticationManager() para procurar uma implementação 
 * da Interface UserDetailsService e utilizá-la para identificar se o usuário é válido ou não. Em nosso projeto Blog Pessoal, será utilizada a Classe UserDetailsServiceImpl, 
 * que valida o usuário no Banco de dados.*/

/*linha 31 = SecurityFilterChain filterChain(HttpSecurity http), estamos informando ao Spring que a configuração padrão da Spring Security será substituída por uma nova configuração.*/

/*Classe HttpSecurity: Permite configurar a segurança baseada na Web para solicitações http específicas. Por padrão, será aplicado a todas as requisições que forem recebidas, mas pode ser restringido para apenas alguns endpoints. */

/*linha 33 = Define que o nosso sistema não guardará sessões para o cliente. Quando o cliente faz uma requisição HTTP, ele inclui todas as informações necessárias para o servidor atender à requisição e a mesma é finalizada com a resposta do servidor. */

/*.csrf().disable(): Desabilita a proteção que vem ativa contra ataques do tipo CSRF (Cross-Site-Request-Forgery), que seria uma interceptação dos dados de autenticação antes da requisição chegar ao servidor. */

/*.cors(): Libera o acesso de outras origens, desta forma nossa aplicação poderá ser acessada de outros domínios, ou seja, de outros endereços, além do endereço onde a aplicação está hospedada.*/

/*linha 35 e 36 = .authorizeHttpRequests((auth) -> auth): Através desta Expressão Lambda podemos definir quais endpoints poderão acessar o sistema sem precisar de autenticação. O Objeto auth recebe o endereço (URI) da requisição e checa se o endpoint necessita ou não de autenticação.

.antMatchers("/usuarios/logar").permitAll() e .antMatchers("/usuarios/cadastrar").permitAll(): Define os endereços (URI) dos endpoints que estarão acessíveis sem autenticação. No projeto Blog Pessoal foi definido que apenas os endpoints logar e cadastrar serão livres de autenticação.*/

/*linha 37 = antMatchers(HttpMethod.OPTIONS).permitAll(): O parâmetro HttpMethod.OPTIONS permite que o cliente (front-end), possa descobrir quais são as opções permitidas e/ou obrigatórias no cabeçalho da requisição.
 * .anyRequest().authenticated(): Informa ao sistema que todos os endpoints que não estiverem especificados na lista acima, a autenticação será obrigatória. */

/*linha 38 = httpBasic(): Informa ao sistema que o servidor irá receber requisições que devem ter o esquema HTTP Basic de autenticação. */
 