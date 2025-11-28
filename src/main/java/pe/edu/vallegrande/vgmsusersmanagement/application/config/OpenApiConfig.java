package pe.edu.vallegrande.vgmsusersmanagement.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

     @Bean
     public OpenAPI customOpenAPI() {
          return new OpenAPI()
                    .info(new Info()
                              .title("API de Gestión de Usuarios - SIGEI")
                              .description("Microservicio para la gestión de usuarios del sistema SIGEI")
                              .version("1.0.0")
                              .contact(new Contact()
                                        .name("Valle Grande")
                                        .email("soporte@vallegrande.edu.pe")
                                        .url("https://www.vallegrande.edu.pe"))
                              .license(new License()
                                        .name("Apache 2.0")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                    .servers(List.of(
                              new Server()
                                        .url("http://localhost:9083")
                                        .description("Servidor Local"),
                              new Server()
                                        .url("http://localhost:9083")
                                        .description("Servidor de Desarrollo")));
     }
}
