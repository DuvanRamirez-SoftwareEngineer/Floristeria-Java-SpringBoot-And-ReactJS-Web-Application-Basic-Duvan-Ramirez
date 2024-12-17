package com.floristeria.floristeria.model;


import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private double total;

    @OneToMany
    private List<Flor> flores;
}




