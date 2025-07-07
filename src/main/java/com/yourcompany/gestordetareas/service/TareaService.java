package com.yourcompany.gestordetareas.service;

import java.time.*;
import java.time.temporal.*;

import com.yourcompany.gestordetareas.model.*;

/**
 * Servicio para operaciones de negocio sobre Tarea
 */
public class TareaService {

    /**
     * Calcula cuántos días faltan para el vencimiento de la tarea.
     */
    public long calcularDiasRestantes(Tarea tarea) {
        LocalDate hoy = LocalDate.now();
        return ChronoUnit.DAYS.between(hoy, tarea.getFechaVencimiento());
    }

    /**
     * Verifica si una tarea está vencida.
     */
    public boolean estaVencida(Tarea tarea) {
        return LocalDate.now().isAfter(tarea.getFechaVencimiento());
    }
}