package com.floristeria.floristeria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// La anotación @Configuration indica que esta clase contiene configuraciones de Spring
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Este método se sobrescribe para agregar configuraciones personalizadas de manejo de recursos
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Configurar para servir archivos estáticos desde c:/uploads/
        // addResourceHandler("/uploads/**") define la ruta URL a través de la cual los recursos serán accesibles
        // addResourceLocations("file:/uploads/") define la ubicación física de los archivos en el sistema de archivos
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/c:/uploads/");
    }
}
