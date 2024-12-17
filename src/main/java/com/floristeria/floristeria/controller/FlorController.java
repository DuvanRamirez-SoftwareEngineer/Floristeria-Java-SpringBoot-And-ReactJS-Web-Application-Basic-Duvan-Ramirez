package com.floristeria.floristeria.controller;

import com.floristeria.floristeria.model.Flor;
import com.floristeria.floristeria.service.FlorService;
import io.swagger.v3.oas.annotations.Operation; // Importación para las anotaciones de operación de OpenAPI
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importación para las respuestas de API de OpenAPI
import io.swagger.v3.oas.annotations.responses.ApiResponses; // Importación para las respuestas múltiples de API de OpenAPI
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controlador para gestionar las flores en la floristería.
 */
@Controller
@RequestMapping("/flores")
public class FlorController {

    private static final String UPLOAD_FOLDER = "c:/uploads/"; // Carpeta de subida de archivos

    @Autowired
    private FlorService florService; // Servicio para gestionar las operaciones relacionadas con las flores

    /**
     * Muestra el formulario para capturar una nueva flor.
     *
     * @param model Modelo para pasar atributos a la vista.
     * @return Nombre de la vista del formulario de flor.
     */
    @Operation(summary = "Mostrar formulario para capturar una nueva flor") // Descripción de la operación
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulario mostrado correctamente") // Respuesta esperada
    })
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("flor", new Flor()); // Agrega un nuevo objeto Flor al modelo
        return "florForm"; // Retorna el nombre de la plantilla Thymeleaf para el formulario
    }

    /**
     * Guarda los datos de una nueva flor y el archivo subido.
     *
     * @param flor Objeto Flor con los datos capturados del formulario.
     * @param file Archivo subido que representa la imagen de la flor.
     * @param redirectAttributes Atributos para redirigir mensajes a la vista.
     * @return Redirección a la lista de flores.
     */
    @Operation(summary = "Guardar los datos de una nueva flor") // Descripción de la operación
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección a la lista de flores"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping("/guardar")
    public String saveFlor(@ModelAttribute Flor flor, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Por favor, seleccione un archivo para cargar."); // Mensaje en caso de archivo vacío
            return "redirect:/flores/form"; // Redirige de vuelta al formulario
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_FOLDER); // Ruta de la carpeta de subida
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // Crea la carpeta si no existe
            }

            byte[] bytes = file.getBytes();
            Path path = uploadPath.resolve(file.getOriginalFilename());
            Files.write(path, bytes); // Escribe el archivo en la ruta especificada

            // Almacenar la ruta relativa en la base de datos
            flor.setImagePath("/uploads/" + file.getOriginalFilename());
            florService.save(flor); // Guarda la flor usando el servicio
            redirectAttributes.addFlashAttribute("message", "Archivo cargado exitosamente: '" + file.getOriginalFilename() + "'"); // Mensaje de éxito
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/flores/listar"; // Redirige a la lista de flores
    }

    /**
     * Muestra la lista de flores.
     *
     * @param model Modelo para pasar atributos a la vista.
     * @return Nombre de la vista de la lista de flores.
     */
    @Operation(summary = "Mostrar lista de flores") // Descripción de la operación
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de flores mostrada correctamente") // Respuesta esperada
    })
    @GetMapping("/listar")
    public String listFlores(Model model) {
        List<Flor> flores = florService.findAll(); // Obtiene la lista de flores del servicio
        model.addAttribute("flores", flores); // Agrega la lista de flores al modelo
        return "flores"; // Retorna el nombre de la plantilla Thymeleaf para la lista de flores
    }
}
