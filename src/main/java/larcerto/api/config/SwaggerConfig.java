package larcerto.api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI testOpenAPIDefinition() {

    return new OpenAPI()
        .info(new io.swagger.v3.oas.models.info.Info()
            .title("API - Lar Certo Imóveis")
            .contact(new Contact()
                .name("Anderson Fuhr").email("andersonfuhr@yahoo.com.br"))
            .description("Aplicação para controle de imóveis")
            .version("v0.0.1"))
        .externalDocs(new ExternalDocumentation()
            .description("Repositório Desta API no GitHub")
            .url("https://github.com/fuhr-br"));
  }

}