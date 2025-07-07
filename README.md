# Gestor de Tareas

Sistema de gestión de tareas desarrollado con OpenXava para la administración eficiente de proyectos y asignaciones de trabajo.

## Descripción del Proyecto

Esta es una aplicación de gestión de tareas basada en Java y construida con el framework OpenXava. El sistema permite administrar tareas con usuarios asociados, prioridades y estados de progreso.

## Stack Tecnológico

- **Framework**: OpenXava 7.5.2 (framework web para desarrollo rápido de aplicaciones empresariales)
- **Lenguaje**: Java 11
- **Base de Datos**: HSQLDB (embebida) por defecto, con soporte para MySQL, PostgreSQL, Oracle y otros
- **ORM**: JPA/Hibernate (a través de OpenXava)
- **Herramienta de Construcción**: Maven
- **Testing**: JUnit 5
- **Librerías Adicionales**: Lombok (para reducir código repetitivo)

## Modelo de Dominio Principal

La aplicación se centra en cuatro entidades principales:

1. **Tarea**: Entidad principal con título, descripción, responsable, prioridad, estado y fechas
2. **Usuario**: Representa a los responsables de las tareas
3. **Prioridad**: Niveles de prioridad de las tareas (Alta, Media, Baja)
4. **Estado**: Estados de las tareas (Pendiente, En Proceso, Finalizada)

## Comandos de Desarrollo Principales

### Construcción y Ejecución
```bash
# Construir el proyecto
mvn clean compile

# Empaquetar la aplicación
mvn package

# Ejecutar pruebas
mvn test

# Ejecutar la aplicación (inicia servidor y base de datos embebidos)
mvn exec:java -Dexec.mainClass="com.yourcompany.gestordetareas.run.gestordetareas"
```

### Pruebas
```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar una clase de prueba específica
mvn test -Dtest=TareaServiceTest

# Ejecutar pruebas con codificación UTF-8
mvn test -Dfile.encoding=UTF-8
```

## Estructura del Proyecto

```
src/main/java/com/yourcompany/gestordetareas/
├── actions/         # Controladores de acciones OpenXava
├── model/          # Entidades JPA (Tarea, Usuario, Estado, Prioridad)
├── service/        # Servicios de lógica de negocio
├── util/           # Clases utilitarias
└── run/            # Punto de entrada de la aplicación

src/main/resources/
├── xava/           # Archivos de configuración OpenXava
├── i18n/           # Archivos de internacionalización
└── META-INF/       # Configuración de persistencia JPA

src/main/webapp/
├── META-INF/       # Configuración de contexto de base de datos
├── WEB-INF/        # Configuración de aplicación web
├── xava/           # Recursos web de OpenXava
└── naviox/         # Componentes de navegación y seguridad
```

## Configuración de Base de Datos

La aplicación utiliza HSQLDB por defecto pero soporta múltiples bases de datos:

- **Por defecto**: HSQLDB (embebida, se inicia automáticamente)
- **Opciones de producción**: MySQL, PostgreSQL, Oracle, SQL Server, DB2, Informix, Firebird

La configuración de base de datos está en `src/main/webapp/META-INF/context.xml`. Para cambiar de base de datos:
1. Descomentar la configuración de datasource apropiada
2. Agregar la dependencia del driver JDBC correspondiente al `pom.xml`
3. Actualizar los parámetros de conexión (URL, usuario, contraseña)

## Arquitectura OpenXava

Esta aplicación sigue las convenciones de OpenXava:

- **Modelos**: Entidades JPA con anotaciones OpenXava para generación de UI
- **Vistas**: Definidas usando anotaciones `@View` en las clases modelo
- **Controladores**: Clases de acción que extienden `BaseAction` para lógica de negocio personalizada
- **Módulos**: Definidos en `src/main/resources/xava/application.xml`

## Anotaciones y Patrones Clave

### Anotaciones del Modelo
- `@Entity`, `@Table`: JPA estándar
- `@View`: Define el diseño del formulario y agrupación de campos
- `@Tab`: Define las columnas de vista de lista y ordenamiento por defecto
- `@DescriptionsList`: Crea listas desplegables para referencias
- `@Required`: Marca campos obligatorios
- `@DisplaySize`: Controla el ancho de visualización del campo

### Lógica de Negocio
- Usar `@PrePersist` para validación de entidades (ver `Tarea.validarFechas()`)
- Los métodos de negocio personalizados pueden agregarse a las entidades
- Las clases de servicio manejan lógica de negocio compleja

## Notas sobre Pruebas

- Las pruebas se encuentran en `src/test/java/`
- Utiliza JUnit 5 (anotación `@Test`)
- Configuración de pruebas en `src/test/resources/xava-junit.properties`
- Las pruebas se ejecutan con codificación UTF-8 por defecto

## Consejos de Desarrollo

1. **Cambios en Entidades**: Después de modificar entidades, reiniciar la aplicación para actualizar el esquema de base de datos
2. **Cambios en UI**: Modificar las anotaciones `@View` y `@Tab` para cambiar diseños de formularios y visualizaciones de listas
3. **Acciones Personalizadas**: Agregar métodos a las clases controladoras para operaciones de negocio personalizadas
4. **Consultas de Base de Datos**: Usar consultas JPA en clases de servicio para operaciones de datos complejas
5. **Internacionalización**: Agregar etiquetas a archivos en `src/main/resources/i18n/` para soporte multi-idioma

## Funcionalidades Implementadas

### Gestión de Tareas
- Creación, edición y eliminación de tareas
- Asignación de responsables
- Establecimiento de prioridades (Alta, Media, Baja)
- Control de estados (Pendiente, En Proceso, Finalizada)
- Fechas de creación y vencimiento
- Cálculo automático de estado de vencimiento

### Gestión de Usuarios
- Registro de usuarios con roles (Administrador, Líder, Colaborador)
- Validación de correos electrónicos
- Sistema de autenticación
- Control de acceso por roles

### Características Avanzadas
- Filtrado de tareas por estado, prioridad y responsable
- Exportación de datos en formato CSV
- Interfaz web responsiva generada automáticamente
- Soporte completo para UTF-8 y caracteres especiales

## Validaciones de Negocio

El sistema incluye validaciones robustas:

- **Fechas**: La fecha de vencimiento debe ser posterior a la fecha de creación
- **Roles**: Solo se permiten roles predefinidos (ADMINISTRADOR, LIDER, COLABORADOR)
- **Estados**: Valores restringidos a Pendiente, En Proceso, Finalizada
- **Prioridades**: Valores restringidos a Alta, Media, Baja
- **Correos**: Validación de formato de email
- **Campos obligatorios**: Validación de campos requeridos

## Ejecución de la Aplicación

Ejecutar la clase principal `gestordetareas.java` que:
1. Inicia el servidor de base de datos HSQLDB embebido
2. Inicia el servidor de aplicaciones Tomcat embebido
3. Despliega la aplicación en `http://localhost:8080/gestordetareas`

La aplicación incluye una interfaz web completa con autenticación, navegación y operaciones CRUD para todas las entidades.
