package com.yourcompany.gestordetareas.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Table(name = "prioridades")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@View(members = 
    "nombre;" +
    "descripcion;"
)
@Tab(properties = "nombre, descripcion", 
     defaultOrder = "CASE WHEN nombre = 'Alta' THEN 1 WHEN nombre = 'Media' THEN 2 WHEN nombre = 'Baja' THEN 3 END")
public class Prioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    @Required
    @DisplaySize(20)
    private String nombre; // Alta, Media, Baja

    @Column(length = 100)
    @DisplaySize(50)
    private String descripcion;
    
    @PrePersist
    @PreUpdate
    private void validarNombre() {
        if (nombre != null && !nombre.equals("Alta") && 
            !nombre.equals("Media") && !nombre.equals("Baja")) {
            throw new IllegalArgumentException("La prioridad debe ser: Alta, Media o Baja");
        }
    }
    
    public String toString() {
        return nombre;
    }
}