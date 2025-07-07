package com.yourcompany.gestordetareas.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Table(name = "estados")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@View(members = 
    "nombre;" +
    "descripcion;"
)
@Tab(properties = "nombre, descripcion", 
     defaultOrder = "${nombre} asc")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    @Required
    @DisplaySize(20)
    private String nombre; // Pendiente, En Proceso, Finalizada

    @Column(length = 100)
    @DisplaySize(50)
    private String descripcion;
    
    @PrePersist
    @PreUpdate
    private void validarNombre() {
        if (nombre != null && !nombre.equals("Pendiente") && 
            !nombre.equals("En Proceso") && !nombre.equals("Finalizada")) {
            throw new IllegalArgumentException("El estado debe ser: Pendiente, En Proceso o Finalizada");
        }
    }
    
    public String toString() {
        return nombre;
    }
}