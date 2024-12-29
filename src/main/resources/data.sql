USE sgahc;

INSERT INTO niveles_permisos (nivel)
VALUES
    (1),
    (2),
    (3);

INSERT INTO roles (id_rol, rol, descripcion, ID_NP)
VALUES
    (2,'Medico', 'Rol de los profesionales de la salud',2 ),
    (3,'Paciente', 'Rol de los pacientes y tutores', 3);

INSERT INTO usuarios (ID_usuario, usuario, contrasenia, ID_Rol, fecha_creacion_usuario, correo_electronico)
VALUES
    (1,'_Juan_', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 3, NOW(), 'juan.garcia@email.com'),
    (2,'_Maria12', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 3, NOW(),'maria.fernandez@email.com'),
    (3,'_Carlos_A', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 3, NOW(), 'carlos.gomez@email.com'),
    (4,'_Ana_88', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 3, NOW(), 'ana.martinez@email.com'),
    (5,'_Diegoo', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 3, NOW(), 'diego.sanchez@email.com'),
    (6,'_Lucy', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 3, NOW(), 'lucia.torres@email.com');

-- Direcciones
INSERT INTO direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal)
VALUES ('México', 'Jalisco', 'Guadalajara', 'Av. Vallarta', 'Americana', 1234, '44160');

INSERT INTO direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal)
VALUES ('España', 'Cataluña', 'Barcelona', 'Passeig de Gràcia', 'Eixample', 56, '08007');

INSERT INTO direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal)
VALUES ('Argentina', 'Buenos Aires', 'La Plata', 'Calle 7', 'Centro', 890, 'B1900');

INSERT INTO direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal)
VALUES ('Colombia', 'Antioquia', 'Medellín', 'Carrera 43A', 'El Poblado', 15, '050022');

INSERT INTO direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal)
VALUES ('Chile', 'Región Metropolitana', 'Santiago', 'Av. Providencia', 'Providencia', 2500, '7500000');

INSERT INTO direcciones (pais, estado, municipio, calle, colonia, numero_casa, codigo_postal)
VALUES ('Perú', 'Lima', 'Miraflores', 'Av. Larco', 'Miraflores', 400, '15074');

-- Sexos
INSERT INTO sexos (sexo) VALUES ('Masculino');
INSERT INTO sexos (sexo) VALUES ('Femenino');

-- Datos Personales
INSERT INTO datos_personales_generales (nombre, nombre2, apellido1, apellido2, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('Juan', 'Carlos', 'García', 'López', '1990-05-15', 33, '+5213312345678', 1, 1, 1);
-- Menor de edad
INSERT INTO datos_personales_generales (nombre, nombre2, apellido1, apellido2, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('Miguel', 'Ángel', 'López', 'Gómez', '2010-11-25', 13, '+5213319876543', 1, 1, 1);

INSERT INTO datos_personales_generales (nombre, apellido1, apellido2, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('María', 'Fernández', 'Rodríguez', '1985-09-22', 38, '+34932345678', 2, 2, 2);

INSERT INTO datos_personales_generales (nombre, nombre2, apellido1, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('Carlos', 'Alberto', 'Gómez', '1992-11-30', 31, '+5492212345678', 3, 3, 1);

INSERT INTO datos_personales_generales (nombre, apellido1, apellido2, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('Ana', 'Martínez', 'Pérez', '1988-07-10', 35, '+573001234567', 4, 4, 2);

INSERT INTO datos_personales_generales (nombre, nombre2, apellido1, apellido2, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('Diego', 'Alejandro', 'Sánchez', 'Vargas', '1995-03-25', 28, '+56987654321', 5, 5, 1);

INSERT INTO datos_personales_generales (nombre, apellido1, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES ('Lucía', 'Torres', '1993-12-05', 30, '+5112345678', 6, 6, 2);


INSERT INTO usuarios (ID_usuario, usuario, contrasenia, ID_Rol, fecha_creacion_usuario, correo_electronico)
VALUES
    (8,'Dr_Roberto_u', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'roberto.ramirez@email.com'),
    (9,'Dra_Laura_Sa', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(),'laura.serra@email.com'),
    (10,'Dr_Mario_PJ', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'mario.perez@email.com'),
    (11,'Dra_Ana__80', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'ana.moreno@email.com'),
    (12,'Dr_Carlos', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'carlos.hernan@email.com'),
    (13,'Dra_Marta', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'marta.lopez@email.com'),
    (14,'Dr_Diego', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'diego.suarez@email.com'),
    (15,'Dra_Claudia', '$2y$11$KUvDUBdLeSiNCJCy7EfC8.YSTxVxPFWI2OSfuyVlmMVjreZj4duTi', 2, NOW(), 'claudia.vega@email.com');


-- Nuevos registros en Datos Personales Generales para los médicos
INSERT INTO datos_personales_generales (nombre, apellido1, apellido2, fecha_nacimiento, edad, telefono, id_usuario, id_direccion, id_sexo)
VALUES
    ('Roberto', 'Ramírez', 'López', '1978-01-15', 46, '+5213395678901', 8, 1, 1),
    ('Laura', 'Serrano', 'González', '1982-06-22', 42, '+34939223344', 9, 2, 2),
    ('Mario', 'Pérez', 'Jiménez', '1975-03-10', 49, '+5492219976543', 10, 3, 1),
    ('Ana', 'Moreno', 'Castro', '1980-11-03', 43, '+573001987554', 11, 4, 2),
    ('Carlos', 'Hernández', 'Ortiz', '1985-05-20', 39, '+56966456721', 12, 5, 1),
    ('Marta', 'López', 'Pérez', '1977-09-25', 47, '+5112345622', 13, 6, 2),
    ('Diego', 'Suárez', 'Mendoza', '1990-12-15', 33, '+522227756789', 14, 1, 1),
    ('Claudia', 'Vega', 'Rodríguez', '1983-08-30', 41, '+34934434567', 15, 2, 2);

-- Pacientes
-- Inserts para Estados_Civiles
INSERT INTO Estados_Civiles (estado_civil) VALUES
                                               ('Soltero/a'), ('Casado/a'), ('Divorciado/a'), ('Viudo/a'), ('Unión libre'), ('Separado/a');

-- Inserts para Ocupaciones
INSERT INTO Ocupaciones (ocupacion) VALUES
                                        ('Estudiante'), ('Empleado/a'), ('Autónomo/a'), ('Jubilado/a'), ('Desempleado/a'), ('Ama/o de casa');

-- Inserts para COMBE
INSERT INTO COMBE (combe) VALUES
                              ('Negativo'), ('Positivo'), ('No sé');

-- Inserts para Grupos_Sanguineos
INSERT INTO Grupos_Sanguineos (grupo_sanguineo) VALUES
                                                    ('A'), ('B'), ('AB'), ('O');

-- Inserts para RH
INSERT INTO RH (RH) VALUES
                        ('Positivo'), ('Negativo');

-- Inserts para Lugares_Nacimiento
INSERT INTO Lugares_Nacimiento (pais, estado, municipio) VALUES
                                                             ('México', 'Jalisco', 'Guadalajara'),
                                                             ('España', 'Cataluña', 'Barcelona'),
                                                             ('Argentina', 'Buenos Aires', 'La Plata'),
                                                             ('Colombia', 'Antioquia', 'Medellín'),
                                                             ('Chile', 'Región Metropolitana', 'Santiago'),
                                                             ('Perú', 'Lima', 'Miraflores');

-- Inserts para pacientes
INSERT INTO pacientes (ID_Datos_Personales_Generales, ID_Estado_Civil, ID_Ocupacion, ID_COMBE, ID_Grupo_Sanguineo, ID_RH, ID_Lugar_Nacimiento)
VALUES
    (1, 2, 2, 3, 1, 1, 1),
    (2, 1, 3, 3, 4, 1, 2),
    (3, 3, 1, 3, 2, 2, 3),
    (4, 5, 4, 3, 3, 1, 4),
    (5, 1, 2, 2, 4, 2, 5),
    (6, 4, 6, 2, 1, 1, 6),
    (7, 1, 1, 2, 1, 1, 1);


INSERT INTO Sustancias (sustancia, descripcion) VALUES
                                                    ('Alcohol', 'Bebidas alcohólicas como cerveza, vino, licores'),
                                                    ('Tabaco', 'Cigarrillos, puros, tabaco para pipa'),
                                                    ('Ninguna sustancia', 'No consume ninguna sustancia');

INSERT INTO Consumo_Sustancias (ID_Paciente, ID_Sustancia, cantidad_consumo, frecuencia) VALUES
                                                                                             (1, 1, 2, 7),    -- Paciente 1 consume 2 unidades de alcohol 7 veces por semana
                                                                                             (1, 2, 10, 7),   -- Paciente 1 consume 10 cigarrillos 7 veces por semana
                                                                                             (2, 1, 3, 7),    -- Paciente 2 consume 3 tundidades de alcohol 7 veces por semana
                                                                                             (3, 3, 1, 2),
                                                                                             (4, 3, 1, 7),
                                                                                             (5, 1, 1, 2),
                                                                                             (5, 2, 5, 7),
                                                                                             (6, 1, 10, 2),
                                                                                             (7, 3, 0, 0);


/* Inserciones en la tabla Enfermedades */
INSERT INTO Enfermedades (enfermedad, descripcion)
VALUES
    ('Hipertensión', 'Presión arterial crónicamente alta, puede causar complicaciones cardiovasculares.'),
    ('Diabetes Tipo 2', 'Afección crónica que afecta el modo en que el cuerpo metaboliza el azúcar (glucosa).'),
    ('Asma', 'Enfermedad respiratoria que causa dificultades para respirar debido a la inflamación de las vías respiratorias.'),
    ('Artritis', 'Inflamación de las articulaciones, causando dolor y rigidez que empeoran con la edad.'),
    ('Colesterol Alto', 'Niveles altos de colesterol en la sangre que pueden obstruir las arterias.'),
    ('Sin antecedentes', 'El paciente no presenta enfermedades relevantes hasta la fecha.');

/* Inserciones en la tabla Antecedentes_Patológicos */
INSERT INTO Antecedentes_Patologicos (ID_Paciente, ID_Enfermedad, descripcion, fecha)
VALUES
    (1, 1, 'El paciente tiene hipertensión desde hace 5 años, controlada con medicación.', '2019-12-10'),
    (2, 2, 'El paciente ha sido diagnosticado con diabetes tipo 2 hace 2 años.','2022-12-20'),
    (3, 3, 'Paciente con asma desde la infancia, utilizando inhalador ocasionalmente.', '2000-12-10'),
    (4, 6, 'El paciente reporta estar saludable y no tener antecedentes patológicos relevantes.', '0000-00-00'),
    (5, 4, 'Diagnosticado con artritis en las manos hace 3 años, bajo tratamiento.', '2021-12-03'),
    (6, 5, 'El paciente tiene niveles elevados de colesterol, bajo dieta y medicación.', '2013-07-12'),
    (7, 6, 'Paciente sin antecedentes patológicos relevantes a su edad.', '0000-00-00');

/* Inserciones en la tabla Parentescos */
INSERT INTO Parentescos (parentesco)
VALUES
    ('Padre'),
    ('Madre'),
    ('Hermano/a'),
    ('Abuelo/a'),
    ('Tío/a'),
    ('Primo/a');

/* Inserciones en la tabla Razones_Fallecimiento */
INSERT INTO Razones_Fallecimiento (razon)
VALUES
    ('Infarto'),
    ('Cáncer'),
    ('Accidente automovilístico'),
    ('Insuficiencia renal'),
    ('Diabetes'),
    ('Causas naturales'),
    ('No falleció');  -- Nueva opción para familiares que aún viven

/* Inserciones en la tabla Antecedentes_Heredofamiliares */
INSERT INTO Antecedentes_Heredofamiliares (ID_Paciente, ID_Lugar_Nacimiento, ID_Parentesco, ID_Razon_Fallecimiento, ID_Sexo, ID_Enfermedad, edad)
VALUES
    (1, 1, 1, 1, 1, 1, 60),   -- Padre del Paciente 1, fallecido por Infarto a los 60 años, con hipertensión
    (2, 2, 2, 7, 2, 2, 55),   -- Madre del Paciente 2, aún vive, con diabetes
    (3, 3, 3, 3, 1, 3, 25),   -- Hermano del Paciente 3, fallecido en un accidente automovilístico a los 25 años, con asma
    (4, 4, 4, 7, 2, 6, 80),   -- Abuela del Paciente 4, aún vive, sin antecedentes de enfermedad
    (5, 5, 5, 4, 1, 4, 70),   -- Tío del Paciente 5, fallecido por insuficiencia renal a los 70 años, con artritis
    (6, 6, 6, 7, 2, 5, 65);   -- Prima del Paciente 6, aún vive, con colesterol alto

INSERT INTO tutores (ID_Datos_Personales_Generales, ID_Paciente, ID_Parentesco)
VALUES (1, 2, 1);


/* Inserciones en la tabla Especialidades */
INSERT INTO Especialidades (especialidad, descripcion_especialidad)
VALUES
    ('Cardiología', 'Especialidad médica centrada en el diagnóstico y tratamiento de las enfermedades del corazón y los vasos sanguíneos.'),
    ('Endocrinología', 'Estudio de las glándulas que producen hormonas y las enfermedades relacionadas.'),
    ('Neumología', 'Enfocada en el tratamiento de enfermedades del sistema respiratorio, como el asma y la bronquitis.'),
    ('Pediatría', 'Brinda atención médica a los niños y adolescentes, incluyendo la prevención y tratamiento de enfermedades infantiles.'),
    ('Neurología', 'Especializada en el tratamiento de trastornos del sistema nervioso.'),
    ('Ginecología', 'Especialidad que se enfoca en la salud del sistema reproductivo femenino.'),
    ('Dermatología', 'Diagnóstico y tratamiento de las enfermedades de la piel.'),
    ('Cirugía General', 'Realiza procedimientos quirúrgicos para tratar enfermedades y condiciones médicas.');

SELECT * FROM especialidades;
-- Registros para la tabla Medicos
INSERT INTO Medicos (ID_Especialidad, cedula, ID_Datos_Personales_Generales)
VALUES
    (2, '23456789', 8),  -- Laura Serrano González
    (3, '34567890', 9),  -- Mario Pérez Jiménez
    (4, '45678901', 10), -- Ana Moreno Castro
    (5, '56789012', 11), -- Carlos Hernández Ortiz
    (6, '67890123', 12), -- Marta López Pérez
    (7, '28901234', 13), -- Diego Suárez Mendoza
    (7, '78901234', 14), -- Diego Suárez Mendoza
    (8, '89012345', 15); -- Claudia Vega Rodríguez

INSERT INTO citas (ID_Medico, ID_Paciente, fecha)
VALUES
    (1, 1, '2024-10-15 09:00:00'), -- Cita con el médico 1 (Roberto Ramírez López) para el paciente 1 (Juan García)
    (2, 2, '2024-10-11 10:30:00'), -- Cita con el médico 2 (Laura Serrano González) para el paciente 2 (María Fernández)
    (3, 2, '2024-10-12 10:30:00'), -- Cita con el médico 3 (Laura Serrano González) para el paciente 2 (María Fernández)
    (5, 2, '2024-10-16 10:30:00'), -- Cita con el médico 5 (Laura Serrano González) para el paciente 2 (María Fernández)
    (8, 2, '2024-10-14 10:30:00'), -- Cita con el médico 8 (Laura Serrano González) para el paciente 2 (María Fernández)
    (3, 3, '2024-10-17 11:00:00'), -- Cita con el médico 3 (Mario Pérez Jiménez) para el paciente 3 (Carlos Gómez)
    (4, 4, '2024-10-18 14:00:00'), -- Cita con el médico 4 (Ana Moreno Castro) para el paciente 4 (Ana Martínez)
    (5, 5, '2024-10-19 15:30:00'), -- Cita con el médico 5 (Carlos Hernández Ortiz) para el paciente 5 (Diego Sánchez)
    (6, 6, '2024-10-20 16:00:00'), -- Cita con el médico 6 (Marta López Pérez) para el paciente 6 (Lucía Torres)
    (7, 3, '2024-10-21 09:30:00'), -- Cita con el médico 7 (Diego Suárez Mendoza) para el paciente 3 (Carlos Gómez)
    (8, 2, '2024-10-22 10:00:00');


INSERT INTO Datos_Exploraciones_Fisica (ID_Cita, habitus_exterior, temperatura_corporal, tension_arterial, frecuencia_cardiaca, frecuencia_respiratoria, peso, talla)
VALUES
    (1, 'En buen estado general', 36.5, '120/80', 72, 16, 70.5, '1.75'), -- Para la cita 1
    (2, 'Presenta leve malestar', 37.2, '130/85', 78, 18, 65.0, '1.65'), -- Para la cita 2
    (3, 'Sin alteraciones', 36.8, '115/75', 70, 14, 80.0, '1.80'), -- Para la cita 3
    (4, 'En buen estado de salud', 36.6, '125/82', 76, 15, 58.0, '1.60'), -- Para la cita 4
    (5, 'Sin síntomas evidentes', 37.0, '118/76', 74, 17, 75.0, '1.70'), -- Para la cita 5
    (6, 'En buen estado físico', 36.7, '120/80', 68, 15, 60.0, '1.62'), -- Para la cita 6
    (7, 'Estado general aceptable', 36.9, '110/70', 71, 16, 82.0, '1.78'), -- Para la cita 7
    (8, 'Presenta tos leve', 37.5, '125/80', 80, 19, 68.0, '1.66');

INSERT INTO Medicamentos (nombre_generico, descripcion_medicamento, advertencias)
VALUES
    ('Paracetamol', 'Analgesico y antipirético utilizado para tratar el dolor y la fiebre.', 'No exceder la dosis recomendada.'),
    ('Ibuprofeno', 'Antiinflamatorio no esteroideo (AINE) utilizado para reducir la fiebre, el dolor y la inflamación.', 'Puede causar malestar estomacal.'),
    ('Amoxicilina', 'Antibiótico utilizado para tratar diversas infecciones bacterianas.', 'Completar el tratamiento según prescripción.'),
    ('Lisinopril', 'Medicamento utilizado para tratar la presión arterial alta y la insuficiencia cardíaca.', 'No utilizar si tiene antecedentes de angioedema.'),
    ('Metformina', 'Medicamento utilizado para el control de la glucosa en pacientes con diabetes tipo 2.', 'Controlar la función renal periódicamente.'),
    ('Salbutamol', 'Broncodilatador utilizado para el alivio del asma y otras afecciones respiratorias.', 'No utilizar en exceso.'),
    ('Simvastatina', 'Medicamento utilizado para reducir el colesterol y prevenir enfermedades cardiovasculares.', 'Puede causar efectos secundarios musculares.'),
    ('Omeprazol', 'Inhibidor de la bomba de protones utilizado para tratar problemas gástricos como reflujo.', 'Tomar antes de las comidas.');

INSERT INTO diagnosticos (ID_Cita, titulo, descripcion, ID_Enfermedad)
VALUES
    (1, 'Hipertensión Arterial', 'El paciente presenta una presión arterial elevada en múltiples mediciones.', 1),  -- Enfermedad ID 1
    (2, 'Diabetes Tipo 2', 'El paciente muestra signos de resistencia a la insulina y niveles elevados de glucosa.', 2),  -- Enfermedad ID 2
    (3, 'Asma Bronquial', 'El paciente presenta episodios recurrentes de dificultad respiratoria.', 3),  -- Enfermedad ID 3
    (4, 'Artritis', 'El paciente presenta inflamación y dolor en las articulaciones.', 4),  -- Enfermedad ID 4
    (5, 'Hiperlipidemia', 'El paciente tiene niveles elevados de colesterol en sangre.', 5),  -- Enfermedad ID 5
    (6, 'Resfriado Común', 'El paciente presenta síntomas típicos de resfriado, incluyendo tos y congestión.', 6);  -- Enfermedad ID 8

INSERT INTO tratamientos (ID_Tratamiento, ID_Diagnostico, ID_Medicamento, fecha_inicio, notas_adicionales,
                          dosis, frecuencia, duracion)
VALUES
    (1,1, 4, '2024-10-15 09:00:00', 'Revisar presión cada semana.', '-', '-','-'),  -- Tratamiento para Hipertensión
    (2,2, 5, '2024-10-16 10:30:00', 'Controlar niveles de glucosa regularmente.', '-', '-','-'),  -- Tratamiento para Diabetes
    (3,3, 6, '2024-10-17 11:00:00', 'Utilizar el inhalador según sea necesario.', '-', '-', '-'),  -- Tratamiento para Asma
    (4,4, 1, '2024-10-18 14:00:00', 'Realizar ejercicios de bajo impacto.', '-', '-','-'),  -- Tratamiento para Artritis
    (5,5, 7, '2024-10-19 15:30:00', 'Iniciar dieta baja en grasas.', '-', '-','-'),  -- Tratamiento para Hiperlipidemia
    (6,6, 8, '2024-10-20 16:00:00', 'Hidratación y reposo.', '-', '-','-');


SELECT * FROM roles;
