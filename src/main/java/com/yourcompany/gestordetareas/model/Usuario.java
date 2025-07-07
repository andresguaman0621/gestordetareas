package com.yourcompany.gestordetareas.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@View(members = 
    "Información personal [" +
        "nombre;" +
        "correo;" +
    "];" +
    "Configuración [" +
        "rol;" +
        "contrasena;" +
    "];"
)
@Tab(properties = "nombre, correo, rol", 
     defaultOrder = "${nombre} asc")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @Required
    @DisplaySize(30)
    private String nombre;

    @Column(length = 50, nullable = false, unique = true)
    @Required
    @DisplaySize(30)
    @Stereotype("EMAIL")
    private String correo;

    @Column(length = 20, nullable = false)
    @Required
    @Stereotype("COMBO")
    @LabelFormat(LabelFormatType.SMALL)
    private String rol; // ADMINISTRADOR, LIDER, COLABORADOR

    @Column(nullable = false)
    @Required
    @Stereotype("PASSWORD")
    @DisplaySize(20)
    private String contrasena;
    
    // Método para validar roles
    @PrePersist
    @PreUpdate
    private void validarRol() {
        if (rol != null && !rol.equals("ADMINISTRADOR") && 
            !rol.equals("LIDER") && !rol.equals("COLABORADOR")) {
            throw new IllegalArgumentException("El rol debe ser: ADMINISTRADOR, LIDER o COLABORADOR");
        }
    }
    
    public String toString() {
        return nombre + " (" + correo + ")";
    }
}