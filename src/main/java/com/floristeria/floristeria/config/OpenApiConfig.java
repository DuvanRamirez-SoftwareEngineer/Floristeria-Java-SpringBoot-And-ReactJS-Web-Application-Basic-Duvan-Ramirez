package com.floristeria.floristeria.config;


import io.swagger.v3.oas.models.OpenAPI; // Importación de la clase OpenAPI de la especificación OpenAPI 3
import io.swagger.v3.oas.models.info.Info; // Importación de la clase Info utilizada para describir la API
import org.springframework.context.annotation.Bean; // Importación de la anotación @Bean para definir un bean de Spring
import org.springframework.context.annotation.Configuration; // Importación de la anotación @Configuration para definir una clase de configuración de Spring

/**
 * Clase de configuración para la documentación de la API utilizando OpenAPI.
 * Esta clase configura un bean de OpenAPI con la información de la API.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Define un bean de OpenAPI que configura la información básica de la API.
     * Este bean se utilizará para generar la documentación de la API.
     *
     * @return un objeto OpenAPI configurado con la información de la API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info() // Crea una nueva instancia de Info para describir la API
                        .title("Floristeria API") // Establece el título de la API
                        .version("1.0") // Establece la versión de la API
                        .description("API para gestionar los productos y pedidos de una floristería")); // Establece una descripción de la API
    }
}
