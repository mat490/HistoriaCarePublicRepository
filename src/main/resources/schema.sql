DROP DATABASE IF EXISTS sgahc;
CREATE DATABASE sgahc;
USE sgahc;
/*Tablas para los usuarios del sistema*/
# Tabla para establecer que tantas acciones y lugares a los que puede acceder el usuario
CREATE TABLE niveles_permisos (
                                  ID_NP INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                  nivel INT NOT NULL UNIQUE
);

# Tabla para establecer el rol del usuario en el sistema
CREATE TABLE Roles (
                       ID_Rol INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       rol VARCHAR (20) NOT NULL UNIQUE,
                       descripcion VARCHAR (300) NOT NULL,
                       ID_NP INT NOT NULL,
                       CONSTRAINT fk_roles_np FOREIGN KEY (ID_NP) REFERENCES niveles_permisos (ID_NP)
);

# Tabla con la información para identificar al usuario
CREATE TABLE usuarios (
                          ID_Usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          usuario VARCHAR (20) NOT NULL UNIQUE,
                          contrasenia VARCHAR (100) NOT NULL,
                          ID_Rol INT NOT NULL,
                          fecha_creacion_usuario DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          correo_electronico VARCHAR (200) NOT NULL UNIQUE,

                          CONSTRAINT fk_usuarios_rol FOREIGN KEY (ID_Rol) REFERENCES roles (ID_Rol)
);

CREATE TABLE direcciones (
                             id_direccion INT PRIMARY KEY AUTO_INCREMENT,
                             pais VARCHAR (50) NOT NULL,
                             estado VARCHAR (50) NOT NULL,
                             municipio VARCHAR (50) NOT NULL,
                             calle VARCHAR (50) NOT NULL,
                             colonia VARCHAR (50) NOT NULL,
                             numero_casa INT NOT NULL,
                             codigo_postal VARCHAR(8) NOT NULL
);

# Unique index para darle unicidad a la tabla direcciones
CREATE UNIQUE INDEX unicidad_direcciones
    ON Direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal);

CREATE TABLE sexos (
                       id_sexo INT PRIMARY KEY AUTO_INCREMENT,
                       sexo VARCHAR (20) NOT NULL UNIQUE
);

CREATE TABLE datos_personales_generales (
                                            id_datos_personales_generales INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                            nombre VARCHAR(50) NOT NULL,
                                            nombre2 VARCHAR(50) NOT NULL DEFAULT 'No tiene',
                                            apellido1 VARCHAR(50) NOT NULL,
                                            apellido2 VARCHAR(50) NOT NULL DEFAULT 'No tiene',
                                            fecha_nacimiento DATE NOT NULL,
                                            edad INT NOT NULL,
                                            telefono VARCHAR (16) NOT NULL,
                                            id_usuario INT NOT NULL,
                                            id_direccion INT NOT NULL,
                                            id_sexo INT NOT NULL,

                                            CONSTRAINT fk_sexos_dpg FOREIGN KEY (id_sexo) REFERENCES sexos (id_sexo),
                                            CONSTRAINT fk_id_direccion_dpg FOREIGN KEY (id_direccion) REFERENCES direcciones (id_direccion),
                                            CONSTRAINT fk_id_usuarios_dpg FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario)
);

/*Trigger para verificar que la fecha de nacimiento de un paciente no sea posterior a la actual*/
DELIMITER //
CREATE TRIGGER verificar_fecha_nacimiento_dpg
    BEFORE INSERT ON datos_personales_generales
    FOR EACH ROW
BEGIN
    IF NEW.fecha_nacimiento > CURDATE()
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Fecha de nacimiento invalida';
    END IF;
END; //
DELIMITER ;

/*Trigger para asignar la edad de manera automática con la fecha de nacimiento*/
DELIMITER //
CREATE TRIGGER asignar_edad_dpg
    BEFORE INSERT ON Datos_Personales_Generales
    FOR EACH ROW
BEGIN
    DECLARE nacimiento DATE;
    # Obtener la fecha de nacimiento de la nueva fila
    SET nacimiento = NEW.fecha_nacimiento;

    # Calcular la edad en años y extraer la parte entera
    SET NEW.edad = FLOOR(YEAR(CURDATE()) - YEAR(nacimiento));
END; //
DELIMITER ;

/*Trigger para evitar que dos adultos no tengan el mismo usuarios (los niños pueden tener repetidos con adultos porque pueden ser sus tutores)*/
DELIMITER //
CREATE TRIGGER evitar_usuario_duplicado_adulto
    BEFORE INSERT ON Datos_Personales_Generales
    FOR EACH ROW
BEGIN
    IF NEW.id_usuario IN (
        SELECT id_usuario
        FROM datos_personales_generales
    ) AND NEW.edad >= 18
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El usuario ya existe para otro adulto';
    END IF;
END; //
DELIMITER ;

/*Trigger para evitar que dos adultos no tengan el mismo telefono (los niños pueden tener repetidos con adultos porque pueden ser sus tutores)*/
DELIMITER //
CREATE TRIGGER evitar_telefono_duplicado_adulto
    BEFORE INSERT ON Datos_Personales_Generales
    FOR EACH ROW
BEGIN
    IF NEW.telefono IN (
        SELECT telefono
        FROM datos_personales_generales
    ) AND NEW.edad >= 18
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El número de teléfono ya existe para otro adulto';
    END IF;
END; //
DELIMITER ;

/*Tablas para contener la variedad de datos para los campos de los pacientes*/
CREATE TABLE Estados_Civiles (
                                 ID_Estado_Civil INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                 estado_civil VARCHAR (20) NOT NULL UNIQUE
);

CREATE TABLE Ocupaciones (
                             ID_Ocupacion INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             ocupacion VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE COMBE (
                       ID_COMBE INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       combe VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE Grupos_Sanguineos (
                                   ID_Grupo_Sanguineo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                   grupo_sanguineo CHAR(2) NOT NULL UNIQUE
);

CREATE TABLE RH (
                    ID_RH INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                    RH CHAR(9) NOT NULL UNIQUE
);

CREATE TABLE Lugares_Nacimiento (
                                    ID_Lugar_Nacimiento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                    pais VARCHAR (50) NOT NULL,
                                    estado VARCHAR (50) NOT NULL,
                                    municipio VARCHAR (50) NOT NULL
);

# Unique index para darle unicidad a la tabla de lugares de nacimiento
CREATE UNIQUE INDEX unicidad_lugares_nacimiento
    ON lugares_nacimiento (pais, estado, municipio);

/*Tabla de pacientes */
CREATE TABLE pacientes (
                           ID_Paciente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                           ID_Datos_Personales_Generales INT NOT NULL UNIQUE,
                           ID_Estado_Civil INT NOT NULL,
                           ID_Ocupacion INT NOT NULL,
                           ID_COMBE INT NOT NULL,
                           ID_Grupo_Sanguineo INT NOT NULL,
                           ID_RH INT NOT NULL,
                           ID_Lugar_Nacimiento INT NOT NULL,

                           CONSTRAINT fk_dpg FOREIGN KEY (ID_Datos_Personales_Generales) REFERENCES Datos_Personales_Generales (ID_Datos_Personales_Generales),
                           CONSTRAINT fk_estado_civil FOREIGN KEY (ID_Estado_Civil) REFERENCES estados_civiles (ID_Estado_Civil),
                           CONSTRAINT fk_ocupacion FOREIGN KEY (ID_Ocupacion) REFERENCES Ocupaciones (ID_Ocupacion),
                           CONSTRAINT fk_combe FOREIGN KEY (ID_COMBE) REFERENCES COMBE (ID_COMBE),
                           CONSTRAINT fk_grupos_sanguineos FOREIGN KEY (ID_Grupo_Sanguineo) REFERENCES grupos_sanguineos (ID_Grupo_Sanguineo),
                           CONSTRAINT fk_rh FOREIGN KEY (ID_RH) REFERENCES RH (ID_RH),
                           CONSTRAINT fk_lugar_nacimiento FOREIGN KEY (ID_Lugar_Nacimiento) REFERENCES lugares_nacimiento (ID_Lugar_Nacimiento)
);

/*Tablas para el consumo de sustancias*/
CREATE TABLE Sustancias (
                            ID_Sustancia INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                            sustancia VARCHAR (60) NOT NULL UNIQUE,
                            descripcion VARCHAR (200) NOT NULL
);

CREATE TABLE Consumo_Sustancias (
                                    ID_Consumo_Sustancia INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                    ID_Paciente INT NOT NULL,
                                    ID_Sustancia INT NOT NULL,
                                    cantidad_consumo INT NOT NULL,
                                    frecuencia INT NOT NULL,

                                    CONSTRAINT fk_cs_paciente FOREIGN KEY (ID_Paciente) REFERENCES pacientes (ID_Paciente),
                                    CONSTRAINT fk_cs_sustancia FOREIGN KEY (ID_Sustancia) REFERENCES sustancias (ID_Sustancia)
);

# Unique index para la unicidad del consumo de sustancias
CREATE UNIQUE INDEX unicidad_consumo_sustancias
    ON Consumo_Sustancias (ID_Paciente, ID_Sustancia);

/*Tabla enfermedades */
CREATE TABLE Enfermedades (
                              ID_Enfermedad INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                              enfermedad VARCHAR (100) NOT NULL UNIQUE,
                              descripcion VARCHAR (300) NOT NULL
);

/*Tabla antecedentes patológicos*/
CREATE TABLE Antecedentes_Patologicos (
                                          ID_Antecedente_Patologico INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                          ID_Paciente INT NOT NULL,
                                          ID_Enfermedad INT NOT NULL,
                                          descripcion VARCHAR (500) NOT NULL,
                                          fecha DATE NOT NULL,

                                          CONSTRAINT fk_anpat_enfermedad FOREIGN KEY (ID_Enfermedad) REFERENCES enfermedades (ID_Enfermedad),
                                          CONSTRAINT fk_anpat_paciente FOREIGN KEY (ID_Paciente) REFERENCES pacientes (ID_Paciente)
);

# Unique index para la unicidad de la tabla de antecedentes patológicos
CREATE UNIQUE INDEX unicidad_antecedentes_patologicos
    ON Antecedentes_Patologicos (ID_Paciente, ID_Enfermedad, fecha);


/*Tabla parentescos*/
CREATE TABLE Parentescos (
                             ID_Parentesco INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             parentesco VARCHAR(50) NOT NULL UNIQUE
);

/*Tabla razones de fallecimiento*/
CREATE TABLE Razones_Fallecimiento (
                                       ID_Razon_Fallecimiento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                       razon VARCHAR (50) NOT NULL UNIQUE
);

/*Tabla antecedentes heredofamiliares*/
CREATE TABLE Antecedentes_Heredofamiliares (
                                               ID_Antecedente_Familiar INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                               ID_Paciente INT NOT NULL,
                                               ID_Lugar_Nacimiento INT NOT NULL,
                                               ID_Parentesco INT NOT NULL,
                                               ID_Razon_Fallecimiento INT NOT NULL,
                                               ID_Sexo INT NOT NULL,
                                               ID_Enfermedad INT NOT NULL,
                                               edad INT NOT NULL,


                                               CONSTRAINT fk_anthf_paciente FOREIGN KEY (ID_Paciente) REFERENCES pacientes (ID_Paciente),
                                               CONSTRAINT fk_anthf_lugar_nacimiento FOREIGN KEY (ID_Lugar_Nacimiento) REFERENCES lugares_nacimiento (ID_Lugar_Nacimiento),
                                               CONSTRAINT fk_anthf_parentesco FOREIGN KEY (ID_Parentesco) REFERENCES parentescos (ID_Parentesco),
                                               CONSTRAINT fk_anthf_sexos FOREIGN KEY (ID_Sexo) REFERENCES sexos (ID_Sexo),
                                               CONSTRAINT fk_anthf_razon_fallecimiento FOREIGN KEY (ID_Razon_Fallecimiento) REFERENCES Razones_Fallecimiento (ID_Razon_Fallecimiento),
                                               CONSTRAINT fk_anthf_enfermedad FOREIGN KEY (ID_Enfermedad) REFERENCES Enfermedades (ID_Enfermedad)
);

# Unique index para la unicidad de la tabla de antecedentes heredofamiliares
CREATE UNIQUE INDEX unicidad_antecedentes
    ON Antecedentes_Heredofamiliares (ID_Paciente, ID_Parentesco, ID_Enfermedad, edad, ID_Sexo, ID_Razon_Fallecimiento);


/*Tabla para los tutores de los pacientes pediátricos*/
CREATE TABLE tutores(
                        ID_Tutor INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        ID_Datos_Personales_Generales INT NOT NULL,
                        ID_Paciente INT NOT NULL,
                        ID_Parentesco INT NOT NULL,

                        CONSTRAINT fk_tutor_dpg FOREIGN KEY (ID_Datos_Personales_Generales) REFERENCES datos_personales_generales (ID_Datos_Personales_Generales),
                        CONSTRAINT fk_tutor_pacinte FOREIGN KEY (ID_Paciente) REFERENCES pacientes (ID_Paciente),
                        CONSTRAINT fk_tutor_parentesco FOREIGN KEY (ID_Parentesco) REFERENCES parentescos (ID_Parentesco)
);

# Unique index para unicidad de la tabla tutores
CREATE UNIQUE INDEX unicidad_tutores
    ON tutores (ID_Datos_Personales_Generales, ID_Paciente);


/* Trigger para asegurar que no haya tutores menores de edad */
DELIMITER //
CREATE TRIGGER verificar_tutores_mayoria_edad
    BEFORE INSERT ON tutores
    FOR EACH ROW
BEGIN
    DECLARE tutor_edad INT;

    -- Obtener la edad del tutor
    SELECT edad INTO tutor_edad
    FROM Datos_Personales_Generales
    WHERE ID_Datos_Personales_Generales = NEW.ID_Datos_Personales_Generales;

    -- Verificar si el tutor es menor de edad
    IF tutor_edad < 18
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se permiten tutores menores de edad.';
    END IF;
END; //
DELIMITER

/* Trigger para asegurar que no haya tutores de pacientes mayores de edad de edad */
DELIMITER //
CREATE TRIGGER verificar_pacientes_menores_edad_tutores
    BEFORE INSERT ON tutores
    FOR EACH ROW
BEGIN
    DECLARE id_dpg_paciente INT;
    DECLARE edad_paciente INT;

    -- Obtener el id de los datos personales del paciente
    SELECT ID_Datos_Personales_Generales INTO id_dpg_paciente
    FROM pacientes
    WHERE ID_Paciente = NEW.ID_Paciente;

    -- Obtener el id de los datos personales del paciente
    SELECT edad INTO edad_paciente
    FROM datos_personales_generales
    WHERE ID_Datos_Personales_Generales = id_dpg_paciente;
    -- Verificar si el tutor es menor de edad
    IF edad_paciente >= 18 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se permiten tutores para pacientes mayores de edad. ';
    END IF;
END;
DELIMITER

/* Tabla para las especialidades médicas*/
CREATE TABLE Especialidades (
                                ID_Especialidad INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                especialidad VARCHAR (60) NOT NULL UNIQUE,
                                descripcion_especialidad VARCHAR (400) NOT NULL DEFAULT 'Sin descripción'
);

/*Tabla médicos*/
CREATE TABLE Medicos (
                         ID_Medico INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         ID_Especialidad INT NOT NULL,
                         cedula VARCHAR (8) NOT NULL  UNIQUE,
                         ID_Datos_Personales_Generales INT NOT NULL  UNIQUE,

                         CONSTRAINT fk_medicos_especialidad FOREIGN KEY (ID_Especialidad) REFERENCES especialidades (ID_Especialidad),
                         CONSTRAINT fk_medicos_dpg FOREIGN KEY (ID_Datos_Personales_Generales) REFERENCES Datos_Personales_Generales (ID_Datos_Personales_Generales)
);

/*Tabla citas*/
CREATE TABLE citas (
                       ID_Cita INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       ID_Medico INT NOT NULL,
                       ID_Paciente INT NOT NULL,
                       fecha DATETIME NOT NULL,

                       CONSTRAINT fk_cita_medico FOREIGN KEY (ID_Medico) REFERENCES medicos (ID_Medico),
                       CONSTRAINT fk_cita_paciente FOREIGN KEY (ID_Paciente) REFERENCES pacientes (ID_Paciente)
);


# Unique index para darle unicidad a las citas de los médicos
CREATE UNIQUE INDEX unicidad_citas_medico
    ON citas (ID_Medico, fecha);

# Unique index para darle unicidad a las citas de los pacientes
CREATE UNIQUE INDEX unicidad_citas_paciente
    ON citas (ID_Paciente, fecha);

/*Tabla para los datos obtenidos en las exploraciones fisicas*/
CREATE TABLE Datos_Exploraciones_Fisica (
                                            ID_Datos_Exploracion INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                            ID_Cita INT NOT NULL UNIQUE,
                                            habitus_exterior VARCHAR (500) NOT NULL,
                                            temperatura_corporal NUMERIC NOT NULL,
                                            tension_arterial VARCHAR (10) NOT NULL,
                                            frecuencia_cardiaca INT NOT NULL,
                                            frecuencia_respiratoria INT NOT NULL,
                                            peso NUMERIC NOT NULL,
                                            talla VARCHAR (6) NOT NULL,

                                            CONSTRAINT fk_datos_exploracion_cita FOREIGN KEY (ID_Cita) REFERENCES citas (ID_Cita)
);


/*Tabla medicamentos*/
CREATE TABLE Medicamentos (
                              ID_Medicamento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                              nombre_generico VARCHAR (100) NOT NULL UNIQUE,
                              descripcion_medicamento VARCHAR (400) NOT NULL DEFAULT 'Sin descripción',
                              advertencias VARCHAR (1000) NOT NULL DEFAULT 'Sin advertencias incluidas en el sistema'
);


/*Tabla para tratamientos aplicados a familiares*/
CREATE TABLE Tratamientos_Familiares (
                                         ID_Tratamiento_Familiar INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                         ID_Antecedente_Familiar INT NOT NULL,
                                         ID_Medicamento INT NOT NULL,

                                         CONSTRAINT fk_tratamientos_familiares_medicamento FOREIGN KEY (ID_Medicamento) REFERENCES medicamentos (ID_Medicamento),
                                         CONSTRAINT fk_tratamientos_familiares_anthf FOREIGN KEY (ID_Antecedente_Familiar) REFERENCES Antecedentes_Heredofamiliares (ID_Antecedente_Familiar)
);

# Unique index para darle unicidad a la tabla tratamientos familiares
CREATE UNIQUE INDEX unicidad_tratamientos_familiares
    ON tratamientos_familiares (ID_Antecedente_Familiar, ID_Medicamento);

/*Tabla diagnósticos*/
CREATE TABLE diagnosticos(
                             ID_Diagnostico INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             ID_Cita INT NOT NULL UNIQUE,
                             titulo VARCHAR(50) NOT NULL,
                             descripcion VARCHAR (1500) NOT NULL DEFAULT 'Sin descripción',
                             fecha DATETIME NOT NULL DEFAULT NOW(),
                             ID_Enfermedad INT NOT NULL,

                             CONSTRAINT fk_diagnostico_cita FOREIGN KEY (ID_CIta) REFERENCES citas (ID_Cita),
                             CONSTRAINT fk_diagnostico_enfermedad FOREIGN KEY (ID_Enfermedad) REFERENCES enfermedades (ID_Enfermedad)
);

/*Tabla para los tratamientos correspondientes para cada diagnóstico*/
CREATE TABLE tratamientos (
                              ID_Tratamiento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                              ID_Diagnostico INT NOT NULL,
                              ID_Medicamento INT NOT NULL,
                              dosis VARCHAR(250) NOT NULL,
                              frecuencia VARCHAR(200) NOT NULL,
                              fecha_inicio DATETIME NOT NULL,
                              duracion VARCHAR(260) NOT NULL,
                              notas_adicionales VARCHAR (500) NOT NULL DEFAULT 'Sin notas adicionales',

                              CONSTRAINT fk_tratamientos_diagnostico FOREIGN KEY (ID_Diagnostico) REFERENCES diagnosticos (ID_Diagnostico),
                              CONSTRAINT fk_tratamientos_medicamento FOREIGN KEY (ID_Medicamento) REFERENCES medicamentos (ID_Medicamento)
);

# Unique index para la unicidad de cada tratamiento
CREATE UNIQUE INDEX unicidad_tratamientos
    ON tratamientos (ID_Diagnostico, ID_Medicamento);


CREATE TABLE acceso_historial_clinico (
                                          ID_Acceso_HC INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                          ID_Usuario_Accede INT NOT NULL,
                                          ID_Paciente INT NOT NULL,
                                          fecha DATETIME NOT NULL,
                                          accion_realizada VARCHAR(250),

                                          CONSTRAINT fk_acceso_hc_usuario FOREIGN KEY (ID_Usuario_Accede) REFERENCES usuarios (ID_Usuario),
                                          CONSTRAINT fk_acceso_hc_paciente FOREIGN KEY (ID_Paciente) REFERENCES pacientes (ID_Paciente)
);


SELECT * FROM niveles_permisos;
INSERT INTO niveles_permisos(nivel) VALUES (1);
INSERT INTO roles(id_np, rol, descripcion) VALUES (1, 'admin', 'administrador');
SELECT * FROM usuarios;