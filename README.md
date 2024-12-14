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
- Funcionalidades:
  - Inicio de sesión con manejo de cookies y generación de JWT.
  - Redirección basada en roles (médico o paciente).
  - Página de inicio con información personalizada.

#### HistoriaClinicaController
- Permite la visualización detallada de las historias clínicas de los pacientes.
- Proporciona:
  - Medicamentos consumidos en los últimos meses.
  - Enfermedades diagnosticadas y frecuencia.
  - Estadísticas de citas médicas por mes.

#### AntecedenteFamiliarController
- Administra los antecedentes familiares de los pacientes.
- Funcionalidades:
  - Visualización de antecedentes familiares asociados a un paciente.
  - Registro de nuevos antecedentes familiares con detalles como enfermedades hereditarias y causas de fallecimiento de familiares.

#### AntecedentePatologicoController
- Gestiona los antecedentes patológicos de los pacientes.
- Funcionalidades:
  - Consulta de antecedentes médicos de cada paciente.
  - Registro de enfermedades diagnosticadas previamente.

#### CitasController
- Administra las citas médicas entre médicos y pacientes.
- Funcionalidades:
  - Programación, modificación y cancelación de citas.
  - Visualización de citas pendientes o historial de citas.
  - Filtrado por médicos y pacientes según el rol del usuario.

#### DirectoriosController
- Ofrece un directorio médico y de pacientes.
- Funcionalidades:
  - Búsqueda de médicos por especialidad y estado.
  - Listado de pacientes asignados a un médico y viceversa.

#### DiagnosticoController
- Maneja la creación y visualización de diagnósticos médicos.
- Funcionalidades:
  - Registro de nuevos diagnósticos.
  - Consulta de diagnósticos de un paciente en particular.
  - Listado de diagnósticos asociados a médicos.

#### RecetasController
- Administra recetas y tratamientos médicos.
- Funcionalidades:
  - Generación de recetas con medicamentos asignados.
  - Visualización y actualización de tratamientos.
  - Acceso a tratamientos asociados a diagnósticos.

#### RegistroController
- Gestiona el registro de usuarios en el sistema.
- Funcionalidades:
  - Registro de nuevos usuarios con asignación de roles.
  - Validación y almacenamiento de datos de usuario.

#### RegistroDatosController
- Maneja el registro de datos personales y direcciones de los usuarios.
- Funcionalidades:
  - Registro de direcciones.
  - Registro de datos personales como género y fecha de nacimiento.

#### RegistroMedicoController
- Administra el registro de médicos en el sistema.
- Funcionalidades:
  - Registro de datos específicos de médicos.
  - Asignación de especialidades médicas.

#### RegistroPacienteController
- Gestiona el registro de pacientes en el sistema.
- Funcionalidades:
  - Registro de datos personales de pacientes.
  - Asignación de detalles médicos como grupo sanguíneo y RH.

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

