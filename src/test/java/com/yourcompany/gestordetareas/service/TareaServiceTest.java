package com.yourcompany.gestordetareas.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;

import org.junit.*;

import com.yourcompany.gestordetareas.model.*;

public class TareaServiceTest {
    
    private final TareaService service = new TareaService();
    
    @Test
    public void testCalcularDiasRestantes_Positive() {
        Tarea tarea = new Tarea();
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaVencimiento(LocalDate.now().plusDays(5));
        long dias = service.calcularDiasRestantes(tarea);
        assertEquals(5, dias);
    }
    
    @Test
    public void testCalcularDiasRestantes_Negative() {
        Tarea tarea = new Tarea();
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaVencimiento(LocalDate.now().minusDays(2));
        long dias = service.calcularDiasRestantes(tarea);
        assertEquals(-2, dias);
    }
    
    @Test
    public void testEstaVencida_False() {
        Tarea tarea = new Tarea();
        tarea.setFechaVencimiento(LocalDate.now().plusDays(1));
        assertFalse(service.estaVencida(tarea));
    }
    
    @Test
    public void testEstaVencida_True() {
        Tarea tarea = new Tarea();
        tarea.setFechaVencimiento(LocalDate.now().minusDays(1));
        assertTrue(service.estaVencida(tarea));
    }
}