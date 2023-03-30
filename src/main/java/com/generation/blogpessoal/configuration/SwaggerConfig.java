package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

	@Configuration
	public class SwaggerConfig {
	
		@Bean
		public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Projeto Blog Pessoal")
				.description("Projeto Blog Pessoal - Generation Brasil")
				.version("v0.0.1")
			.license(new License()
				.name("Generation Brasil")
				.url("https://brazil.generation.org/"))
			.contact(new Contact()
				.name("Conteudo Generation")
				.url("https://github.com/conteudoGeneration")
				.email("conteudogeneration@gmail.com")))
			.externalDocs(new ExternalDocumentation()
				.description("Github")
				.url("https://github.com/conteudoGeneration/"));
	}

		
		@Bean
		public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

			return openApi -> {
				openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

				}));
			};
		}

		private ApiResponse createApiResponse(String message) {

			return new ApiResponse().description(message);

		}
		
}


	
/*	linha 12 = @Configuration indica que a classe é um tipo de configuraçao, define uma Classe como fonte de definicoes de beans, é uma das anotaçoes essenciais ao utilizar uma condiguraçao baseada em Java.
	linha 15 = @Bean utilizada em métodos de uma Classe (OpenAPI), geralmente marcada com @Configuration, indica ao Spring que ele deve invocar aquele método e gerencar o objeto retornado por ele, agora esse objeto pode ser injetado em qualquer ponto da sua aplicaçao.
	linha 17 = return new cria um objeto da Classe openAPI que gera a documentaçao no Swagger utilizando a especificaçao OpenAPI.
	linha 18 = .title/.description/.version Info() insere informaçoes sobre a API (Nome do Projeto (title), descriçao e versao)
	linha 22 = .license/.name/.url License () insere informaçoes referentes a licença da API (nome e link)
	linha 25 = .name/.url/.email Contact () insere as informaçoes de contato da pessoa desenvolvedora (Nome, site, e email)
	linha 29 = .description/.url ExternalDocumentation() insere as info referentes a documentaçao Externas (github, gitpage) onde estao informados o Nome e o Link.*/
	
/* 	linha 39 = OpenApiCustomiser permite personalizar o Swagger, baseado na Especificação OpenAPI,o método customerGlobalHeaderOpenApiCustomiser personaliza todas as mensagens HTTP Responses (Respostas das requisições) do Swagger.
	linha 40 = return openApi -> cria um Objeto da Classe OpenAPI, que gera a documentação no Swagger utilizando a especificação OpenAPI.
	linha 42 = cria um primeiro looping que fará a leitura de todos os recursos (Paths) através do Método getPaths(), que retorna o caminho de cada endpoint. Na sequência, cria um segundo looping que Identificará qual Método HTTP (Operations), está sendo executado em cada endpoint através do Método readOperations(). Para cada Método, todas as mensagens serão lidas e substituídas pelas novas mensagens.
	linha 44 = cria um Objeto da Classe ApiResponses, que receberá as Respostas HTTP de cada endpoint (Paths) através do Método getResponses().
	linha 46 a 52 = adiciona as novas Respostas no endpoint, substituindo as atuais e acrescentando as demais, através do Método addApiResponse(), identificadas pelo HTTP Status Code (200, 201 e etc).
	linha 58 a 60 = O método createApiResponse() adiciona uma descrição (Mensagem), em cada Resposta HTTP.*/
	

