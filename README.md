# HistoriaCare

HistoriaCare es un Sistema de Gestión de Historias Clínicas Electrónicas (SGHCE) desarrollado para optimizar la administración de información médica de pacientes en clínicas y hospitales. Este proyecto fue creado como parte del Diplomado en Desarrollo de Sistemas con Tecnología Java.

## Características principales

- **Registro de usuarios:** Permite registrar pacientes y médicos, gestionando sus datos personales y roles.
- **Autenticación segura:** Uso de Spring Security con JWT para la autenticación de usuarios y asignación de roles.
- **Manejo de historias clínicas:** Acceso y actualización de antecedentes médicos, tratamientos y diagnósticos.
- **Gestín de citas:** Programación, modificación y cancelación de citas médicas.
- **Directorios médicos:** Búsqueda de médicos por especialidad y ubicación.
- **Análisis visual:** Gráficos y métricas para facilitar la visualización de datos.

## Tecnologías utilizadas

- **Backend:** Spring Framework (Spring Boot, Spring Security, Spring MVC)
- **Frontend:** Thymeleaf
- **Base de datos:** MariaDB
- **Seguridad:** Autenticación JWT, BCrypt para el cifrado de contraseñas
- **Gestor de dependencias:** Maven

## Arquitectura

HistoriaCare sigue una arquitectura MVC (Modelo-Vista-Controlador) con los siguientes componentes clave:

### Controladores

#### InicioSesionController

- Gestiona la autenticación de usuarios.
- Implementa:
  - Inicio de sesión seguro con JWT y manejo de cookies.
  - Redirecciones basadas en roles (médico o paciente).

#### HistoriaClinicaController

- Maneja la visualización y actualización de historias clínicas.
- Proporciona datos como:
  - Medicamentos y días de consumo.
  - Enfermedades diagnosticadas en el último año.
  - Citas médicas registradas por mes.

### Módulos principales

1. **Módulo de registro:**
   - Registro de usuarios con roles de médico o paciente.
2. **Módulo de citas médicas:**
   - Agendar, reprogramar o cancelar citas.
   - Notificaciones automáticas por correo electrónico.
3. **Módulo de historial clínico:**
   - Seguimiento de tratamientos, diagnósticos y antecedentes médicos.
   - Generación de reportes médicos.
4. **Módulo de directorios:**
   - Búsqueda de médicos filtrada por especialidad y ubicación.
5. **Módulo de recetas médicas:**
   - Creación de recetas PDF y envío por correo.

## Requerimientos del sistema

- **Java 17** o superior
- **MariaDB 10.5** o superior
- **Maven 3.8** o superior

## Instalación

1. Clona el repositorio:
   ```bash
   git clone <URL-del-repositorio>
   ```
2. Configura la base de datos en `application.properties`.
3. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
4. Accede a la aplicación en `http://localhost:8090`.


## Autor

Matías Alejandro Suárez Zúñiga

