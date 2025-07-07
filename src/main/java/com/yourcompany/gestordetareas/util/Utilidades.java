package com.yourcompany.gestordetareas.util;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import com.yourcompany.gestordetareas.model.*;

/**
 * Funciones auxiliares para el proyecto de tareas
 */
public class Utilidades {

    /**
     * Filtra una lista de tareas retornando solo las vencidas.
     */
    public static List<Tarea> filtrarVencidas(List<Tarea> tareas) {
        LocalDate hoy = LocalDate.now();
        return tareas.stream()
            .filter(t -> t.getFechaVencimiento().isBefore(hoy))
            .collect(Collectors.toList());
    }

    /**
     * Genera CSV para una lista de tareas.
     */
    public static String generarCSV(List<Tarea> tareas) {
        StringBuilder sb = new StringBuilder();
        sb.append("id,titulo,descripcion,fechaVencimiento\n");
        for (Tarea t : tareas) {
            sb.append(t.getId()).append(",")
              .append(t.getTitulo()).append(",")
              .append(t.getDescripcion()).append(",")
              .append(t.getFechaVencimiento()).append("\n");
        }
        return sb.toString();
    }
}