package com.yourcompany.gestordetareas.model;

import java.time.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import lombok.*;

@Entity
@Table(name = "tareas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@View(members = 
    "Información general [" +
        "titulo;" +
        "descripcion;" +
    "];" +
    "Asignación [" +
        "responsable;" +
        "prioridad;" +
        "estado;" +
    "];" +
    "Fechas [" +
        "fechaVencimiento;" +
    "];"
)
@Tab(properties = "titulo, responsable.nombre, prioridad.nombre, estado.nombre, fechaVencimiento", 
     defaultOrder = "${fechaCreacion} desc")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @Required
    @DisplaySize(50)
    private String titulo;

    @Column(length = 500)
    @Stereotype("MEMO")
    @DisplaySize(60)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsable_id")
    @Required
    @DescriptionsList(descriptionProperties = "nombre")
    private Usuario responsable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prioridad_id")
    @Required
    @DescriptionsList(descriptionProperties = "nombre")
    private Prioridad prioridad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id")
    @Required
    @DescriptionsList(descriptionProperties = "nombre")
    private Estado estado;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    @Required
    private LocalDate fechaVencimiento;

    @PrePersist
    private void validarFechas() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDate.now();
        }
        if (fechaVencimiento != null && fechaCreacion != null && 
            fechaVencimiento.isBefore(fechaCreacion)) {
            throw new IllegalArgumentException("La fecha de vencimiento debe ser posterior a la de creación");
        }
    }
    
    // Métodos auxiliares para mostrar información en las vistas
    @Depends("fechaVencimiento")
    public String getEstadoVencimiento() {
        if (fechaVencimiento == null) return "";
        
        LocalDate hoy = LocalDate.now();
        long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(hoy, fechaVencimiento);
        
        if (diasRestantes < 0) {
            return "VENCIDA (" + Math.abs(diasRestantes) + " días)";
        } else if (diasRestantes == 0) {
            return "VENCE HOY";
        } else if (diasRestantes <= 3) {
            return "PRÓXIMA A VENCER (" + diasRestantes + " días)";
        } else {
            return diasRestantes + " días restantes";
        }
    }
}