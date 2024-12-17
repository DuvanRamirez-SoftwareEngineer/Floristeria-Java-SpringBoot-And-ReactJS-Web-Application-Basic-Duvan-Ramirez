// FlorService.java
package com.floristeria.floristeria.service;

import com.floristeria.floristeria.model.Flor;
import com.floristeria.floristeria.repository.FlorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service // Indica que esta clase es un servicio de Spring
public class FlorService {
    @Autowired // Inyección de dependencias del repositorio de flores
    private FlorRepository florRepository;

    // Método para obtener todas las flores
    public List<Flor> findAll() {
        return florRepository.findAll();
    }

    // Método para guardar una flor
    public Flor save(Flor flor) {
        return florRepository.save(flor);
    }

    // Método para eliminar una flor por su ID
    public void deleteById(Long id) {
        florRepository.deleteById(id);
    }
}
