-- Archivo: src/main/resources/data-initial.sql
-- Datos iniciales para el gestor de tareas

-- Insertar Estados predefinidos (IDs auto-incrementales)
INSERT INTO estados (nombre, descripcion) VALUES 
('Pendiente', 'Tarea creada pero no iniciada'),
('En Proceso', 'Tarea en desarrollo o ejecucion'),
('Finalizada', 'Tarea completada exitosamente');

-- Insertar Prioridades predefinidas (IDs auto-incrementales)
INSERT INTO prioridades (nombre, descripcion) VALUES 
('Alta', 'Requiere atencion inmediata'),
('Media', 'Importancia normal'),
('Baja', 'Puede realizarse cuando haya tiempo disponible');

-- Insertar Usuarios de ejemplo (IDs auto-incrementales)
INSERT INTO usuarios (nombre, correo, rol, contrasena) VALUES 
('Administrador', 'admin@empresa.com', 'ADMINISTRADOR', 'admin123'),
('Juan Perez', 'juan.perez@empresa.com', 'LIDER', 'juan123'),
('Maria Garcia', 'maria.garcia@empresa.com', 'COLABORADOR', 'maria123'),
('Carlos Lopez', 'carlos.lopez@empresa.com', 'COLABORADOR', 'carlos123'),
('Ana Martinez', 'ana.martinez@empresa.com', 'LIDER', 'ana123');

-- Insertar algunas tareas de ejemplo (usando IDs numéricos)
INSERT INTO tareas (titulo, descripcion, responsable_id, prioridad_id, estado_id, fechaCreacion, fechaVencimiento) VALUES 
('Implementar modulo de reportes', 'Desarrollar la funcionalidad para generar reportes de tareas', 2, 1, 2, '2025-07-07', '2025-07-14'),
('Revisar documentacion', 'Actualizar la documentación del sistema con los nuevos cambios', 3, 3, 1, '2025-07-07', '2025-07-10'),
('Corregir bug en filtros', 'Solucionar el problema con los filtros de fecha en el listado', 4, 1, 1, '2025-07-07', '2025-07-09'),
('Capacitacion usuarios', 'Realizar sesión de capacitacion para los nuevos usuarios', 5, 2, 1, '2025-07-07', '2025-07-17'),
('Optimizar base de datos', 'Mejorar las consultas y indices de la base de datos', 2, 2, 2, '2025-07-07', '2025-07-12');