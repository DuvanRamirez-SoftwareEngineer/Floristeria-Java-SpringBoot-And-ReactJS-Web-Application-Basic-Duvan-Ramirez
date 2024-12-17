package com.floristeria.floristeria.repository;

// FlorRepository.java


import com.floristeria.floristeria.model.Flor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FlorRepository extends JpaRepository<Flor, Long> {
}


