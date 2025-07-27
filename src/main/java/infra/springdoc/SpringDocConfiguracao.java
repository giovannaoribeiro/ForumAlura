package infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguracao {

    @Bean
    public OpenAPI customOpenAPI() {

        Components components = new Components();
        components.addSecuritySchemes("bearer-key",
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
        );

        return new OpenAPI()
                .components(components)

                .info(new Info()
                    .title("Desafio Fórum Hub")
                    .description("API Rest do Desafio OracleONE")
                .contact(new Contact()
                        .name("Giovanna")
                        .email("giovanna_oliveira.ribeiro@live.com")
                )
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://forumhub/api/licenca")
                )
        );
    }
}
