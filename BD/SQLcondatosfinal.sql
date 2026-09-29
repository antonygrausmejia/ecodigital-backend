-- ==============================================================================
-- BASE DE DATOS
-- ==============================================================================
CREATE DATABASE IF NOT EXISTS ecodigital
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE ecodigital;

-- ==============================================================================
-- LIMPIEZA OPCIONAL
-- ==============================================================================

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS detalle_donacion;
DROP TABLE IF EXISTS donacion;
DROP TABLE IF EXISTS historial_cambios_estado;
DROP TABLE IF EXISTS equipos;
DROP TABLE IF EXISTS instituciones_beneficiarias;
DROP TABLE IF EXISTS donantes;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS estados_equipo;
DROP TABLE IF EXISTS tipos_equipo;
DROP TABLE IF EXISTS roles_usuario;

SET FOREIGN_KEY_CHECKS = 1;

-- ==============================================================================
-- 1. ESTRUCTURA (DDL) - MySQL 8+
-- ==============================================================================

CREATE TABLE roles_usuario (
    rol_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE tipos_equipo (
    tipo_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE estados_equipo (
    estado_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE usuarios (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    `contraseña` CHAR(60) NOT NULL,
    rol_id INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuarios_rol
        FOREIGN KEY (rol_id) REFERENCES roles_usuario(rol_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE donantes (
    donante_id INT AUTO_INCREMENT PRIMARY KEY,
    razon_social VARCHAR(255) NOT NULL,
    RUC_DNI VARCHAR(20) UNIQUE,
    contacto_nombre VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    correo VARCHAR(255),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE instituciones_beneficiarias (
    institucion_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_colegio VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    direccion VARCHAR(255),
    ugel VARCHAR(50),
    telefono_contacto VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE equipos (
    equipo_id INT AUTO_INCREMENT PRIMARY KEY,
    numero_serie VARCHAR(100) NOT NULL UNIQUE,
    tipo_equipo_id INT NOT NULL,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    estado_actual_id INT NOT NULL DEFAULT 1,
    donante_id INT,
    usuario_id_tecnico_actual INT,
    fecha_recepcion DATE NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    fecha_donacion DATE,
    CONSTRAINT fk_equipos_tipo
        FOREIGN KEY (tipo_equipo_id) REFERENCES tipos_equipo(tipo_id),
    CONSTRAINT fk_equipos_estado
        FOREIGN KEY (estado_actual_id) REFERENCES estados_equipo(estado_id),
    CONSTRAINT fk_equipos_donante
        FOREIGN KEY (donante_id) REFERENCES donantes(donante_id),
    CONSTRAINT fk_equipos_tecnico
        FOREIGN KEY (usuario_id_tecnico_actual) REFERENCES usuarios(usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE historial_cambios_estado (
    historial_id INT AUTO_INCREMENT PRIMARY KEY,
    equipo_id INT NOT NULL,
    estado_anterior_id INT,
    estado_nuevo_id INT NOT NULL,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id_responsable INT NOT NULL,
    observaciones TEXT,
    CONSTRAINT fk_historial_equipo
        FOREIGN KEY (equipo_id) REFERENCES equipos(equipo_id),
    CONSTRAINT fk_historial_usuario
        FOREIGN KEY (usuario_id_responsable) REFERENCES usuarios(usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE donacion (
    donacion_id INT AUTO_INCREMENT PRIMARY KEY,
    institucion_id INT NOT NULL,
    usuario_id_responsable INT NOT NULL,
    fecha_envio DATE NOT NULL,
    numero_guia_remision VARCHAR(50) UNIQUE,
    documento_referencia_guia TEXT,
    CONSTRAINT fk_donacion_institucion
        FOREIGN KEY (institucion_id) REFERENCES instituciones_beneficiarias(institucion_id),
    CONSTRAINT fk_donacion_usuario
        FOREIGN KEY (usuario_id_responsable) REFERENCES usuarios(usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE detalle_donacion (
    donacion_id INT NOT NULL,
    equipo_id INT NOT NULL,
    PRIMARY KEY (donacion_id, equipo_id),
    CONSTRAINT fk_detalle_donacion_parent
        FOREIGN KEY (donacion_id) REFERENCES donacion(donacion_id),
    CONSTRAINT fk_detalle_equipo
        FOREIGN KEY (equipo_id) REFERENCES equipos(equipo_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==============================================================================
-- 2. DATOS DE REFERENCIA
-- ==============================================================================

-- ROLES (10)
INSERT INTO roles_usuario (rol_id, nombre_rol) VALUES
(1, 'Administrador'),
(2, 'Registrador'),
(3, 'Técnico'),
(4, 'Supervisor'),
(5, 'Coordinador'),
(6, 'Almacenero'),
(7, 'Auditor'),
(8, 'Voluntario'),
(9, 'Invitado'),
(10, 'Soporte');

-- TIPOS DE EQUIPO (10)
INSERT INTO tipos_equipo (tipo_id, nombre_tipo) VALUES
(1, 'Computadora'),
(2, 'Laptop'),
(3, 'Monitor'),
(4, 'Impresora'),
(5, 'Teclado'),
(6, 'Mouse'),
(7, 'Proyector'),
(8, 'Tablet'),
(9, 'Servidor'),
(10, 'Switch');

-- ESTADOS DE EQUIPO (10)
INSERT INTO estados_equipo (estado_id, nombre_estado) VALUES
(1, 'Recepcionado'),
(2, 'En Revisión'),
(3, 'Operativo'),
(4, 'Descartado'),
(5, 'Despachado'),
(6, 'En Mantenimiento'),
(7, 'Pendiente de Repuesto'),
(8, 'Listo para Donación'),
(9, 'Donado'),
(10, 'En Almacén');

-- USUARIOS (12) 
-- usuario admin@admin, tecnico@tecnico, resgistrador@registrador
-- la contraseña es:1234
INSERT INTO usuarios (usuario_id, nombre, correo, `contraseña`, rol_id) VALUES
(1,'Admin EcoDigital','admin@ecodigital.org','$2a$10$2LJB2plpKcIMwNzghQW.YeOcGFzGFcUPLSCXYQbMsONLxeJt3FZaq',1),
(2,'Maria Registradora','maria@ecodigital.pe','$2a$10$Ui209sAS8mBiH6VEd/trxOpJuE5.2MHtjJMF30XvsmQpJMPvDYdTO',2),
(3,'Pedro Técnico','pedrotecnico@ecodigital.pe','$2a$10$G0JW3vs2LbMRQs2CkqGDc.pO1kq7loN/shTJY1rle0URzdg7cdCoq',3),
(4,'Prueba registrador','prueba@ecodigital.pe','$2a$10$.1DndqQYROxNGzPw/PA3kOqYn3BaDDVp4bD7T82AOs4sk5OBO9PG6',2),
(5,'Luis tecnico','luis@ecodigital.pe','$2a$10$kvYCgiB32XlSD4kwTJct2.s8.gpk6zjqT8uU3tiCxWeqLB3ygJ3di',3),
(6,'a','a@a','$2a$10$kvYCgiB32XlSD4kwTJct2.s8.gpk6zjqT8uU3tiCxWeqLB3ygJ3di',1),
(7,'Jorge Tecnico','jorge@ecodigital.pe','$2a$10$lDUTE0tZ3hXm7CLRCSbrye7qDlBLNHHNwjcOZfF2tNcw7xouKckL2',3),
(8,'Rosa Tecnico','rosa@ecodigital.pe','$2a$10$KPFtvmO5msPhETkpAkk7deaC6e1v2nuhfoV3zbjX4jxipMQwyCaYK',3),
(9,'Kevin Voluntario','kevin@ecodigital.pe','$2a$10$AHqLgGrkFnznQUVaUxKnvOFjcR6oU151jZNfhVowzmP3RJ/8DIMDS',8),
(10,'admin','admin@admin','$2a$10$y4LzjtkQyx36xC3P8emTA.6JKSMDeW59q/1OEhh2fc85bFJ3oXtzK',1),
(11,'tecnico','tecnico@tecnico','$2a$10$7GTnUmhVGNtSQgtgCKkT2u/ce2Fjs9x5WjRnnpW0vQOBmw1c7N0T6',3),
(12,'registrador','registrador@registrador','$2a$10$aS..C1DpdQ71Jj..mxUS0OxsfUeFnovoiZMSo9sXutZB3741U7kRa',2),
(13,'nuevo','nuevo@nuevo','$2a$10$ID3oaOcwiflyKuvGQbEFjenzQgVC8IfpKlbAIBzoHwjty.2zsCt1.',1),
(16,'Jose Tecnico','jtecnico@tecnico.com','$2a$10$7.RHasBQ5VfwJb2Q69TgLO6pHEnqUzWwxvzuner/xFF6Az2cpAJcW',3),
(17,'Desarrollador','dev@dev','$2a$10$//PyCGACavEUFStea6UeJ.MddXcDunZm4/KSqMtOK01eqA15F4q9C',10);
-- DONANTES (10)
INSERT INTO donantes (donante_id, razon_social, RUC_DNI, contacto_nombre, direccion, telefono, correo) VALUES
(1, 'Banca Trujillo S.A.', '20100020001', 'Carlos Ruiz', 'Av. Larco 123, Trujillo', '944000111', 'carlos@bancatrujillo.pe'),
(2, 'Universidad Privada del Norte', '20200030002', 'Lucía Varas', 'Av. El Ejército 500, Trujillo', '955000222', 'lvaras@upn.pe'),
(3, 'Tecnología Norte SAC', '20300040003', 'Miguel Reyes', 'Av. España 850, Trujillo', '966000333', 'miguel@tecnorte.pe'),
(4, 'Municipalidad Distrital de Víctor Larco', '20400050004', 'Sandra León', 'Av. Libertad 220, Víctor Larco', '977000444', 'sandra@munivl.gob.pe'),
(5, 'Colegio San José', '20500060005', 'Patricia Gómez', 'Jr. Bolívar 321, Trujillo', '988000555', 'patricia@sanjose.edu.pe'),
(6, 'Agroindustrial Chimú', '20600070006', 'Renato Salas', 'Carretera Industrial Km 4, Trujillo', '999000666', 'renato@chimu.pe'),
(7, 'Hospital Regional Docente', '20700080007', 'Mariela Ponce', 'Av. Mansiche 795, Trujillo', '911000777', 'mariela@hrdt.pe'),
(8, 'Inversiones Pacífico EIRL', '20800090008', 'Héctor Vela', 'Av. América Oeste 1450, Trujillo', '922000888', 'hector@pacifico.pe'),
(9, 'Notaría Mendoza', '20900100009', 'Julio Mendoza', 'Jr. Pizarro 654, Trujillo', '933000999', 'julio@notariamendoza.pe'),
(10, 'Cámara de Comercio La Libertad', '21000110010', 'Verónica Silva', 'Av. Juan Pablo II 180, Trujillo', '944001000', 'veronica@camaralibertad.pe');

-- INSTITUCIONES BENEFICIARIAS (10)
INSERT INTO instituciones_beneficiarias (institucion_id, nombre_colegio, director, direccion, ugel, telefono_contacto) VALUES
(1, 'IE José Faustino Sánchez Carrión', 'Dr. Jorge Mantilla', 'Florencia de Mora, Trujillo', 'UGEL 01', '988000333'),
(2, 'IE Francisco Lizarzaburu', 'Mg. Elena Torres', 'El Porvenir, Trujillo', 'UGEL 02', '988000444'),
(3, 'IE César Vallejo', 'Prof. Ricardo Vera', 'La Esperanza, Trujillo', 'UGEL 01', '988000445'),
(4, 'IE Santa Edelmira', 'Prof. Carmen Ruiz', 'Víctor Larco, Trujillo', 'UGEL 03', '988000446'),
(5, 'IE José Olaya', 'Prof. Marco Alva', 'Moche, Trujillo', 'UGEL 02', '988000447'),
(6, 'IE Virgen del Socorro', 'Prof. Diana Torres', 'Huanchaco, Trujillo', 'UGEL 03', '988000448'),
(7, 'IE Andrés Avelino Cáceres', 'Prof. Luis Ramírez', 'Laredo, Trujillo', 'UGEL 01', '988000449'),
(8, 'IE San Martín de Porres', 'Prof. Rosa Chávez', 'Salaverry, Trujillo', 'UGEL 02', '988000450'),
(9, 'IE Indoamérica', 'Prof. Nelly Rojas', 'El Milagro, Trujillo', 'UGEL 01', '988000451'),
(10, 'IE José Carlos Mariátegui', 'Prof. Víctor Peña', 'Esperanza Baja, Trujillo', 'UGEL 03', '988000452');

-- EQUIPOS (10)
INSERT INTO equipos (
    equipo_id, numero_serie, tipo_equipo_id, marca, modelo,
    estado_actual_id, donante_id, usuario_id_tecnico_actual, fecha_recepcion, fecha_donacion
) VALUES
(1, 'SN-DELL-9988', 2, 'Dell', 'Latitude 5400', 1, 1, 3, '2026-06-01', NULL),
(2, 'SN-HP-7766', 1, 'HP', 'ProDesk 600', 2, 2, 3, '2026-06-05', NULL),
(3, 'SN-SAMS-1122', 3, 'Samsung', 'FHD 24', 3, 1, 10, '2026-06-08', NULL),
(4, 'SN-LEN-2233', 2, 'Lenovo', 'ThinkPad T480', 8, 3, 3, '2026-06-09', '2026-06-20'),
(5, 'SN-CAN-3344', 4, 'Canon', 'G3110', 6, 4, 10, '2026-06-10', NULL),
(6, 'SN-LOG-4455', 5, 'Logitech', 'K120', 3, 5, 3, '2026-06-11', NULL),
(7, 'SN-LOG-5566', 6, 'Logitech', 'M90', 3, 5, 10, '2026-06-11', NULL),
(8, 'SN-EPS-6677', 4, 'Epson', 'L3250', 7, 6, 3, '2026-06-12', NULL),
(9, 'SN-APL-7788', 8, 'Apple', 'iPad 8th Gen', 8, 7, 10, '2026-06-13', '2026-06-22'),
(10, 'SN-CIS-8899', 10, 'Cisco', 'Catalyst 2960', 10, 8, 3, '2026-06-14', NULL);

-- HISTORIAL DE CAMBIOS DE ESTADO (10)
INSERT INTO historial_cambios_estado (
    historial_id, equipo_id, estado_anterior_id, estado_nuevo_id,
    fecha_cambio, usuario_id_responsable, observaciones
) VALUES
(1, 1, NULL, 1, '2026-06-01 09:00:00', 2, 'Equipo recepcionado y registrado en sistema.'),
(2, 2, 1, 2, '2026-06-05 11:30:00', 2, 'Equipo pasó a revisión técnica inicial.'),
(3, 3, 2, 3, '2026-06-08 15:00:00', 3, 'Monitor probado y operativo.'),
(4, 4, 2, 8, '2026-06-12 10:15:00', 3, 'Laptop lista para donación.'),
(5, 5, 2, 6, '2026-06-13 16:40:00', 10, 'Impresora requiere mantenimiento preventivo.'),
(6, 6, 1, 3, '2026-06-14 08:45:00', 3, 'Teclado verificado y operativo.'),
(7, 7, 1, 3, '2026-06-14 09:10:00', 10, 'Mouse en buen estado.'),
(8, 8, 6, 7, '2026-06-15 14:20:00', 3, 'Impresora pendiente de repuesto.'),
(9, 9, 2, 8, '2026-06-16 12:00:00', 10, 'Tablet lista para donación.'),
(10, 10, 1, 10, '2026-06-17 17:35:00', 3, 'Switch almacenado para futura asignación.');

-- DONACION (10)
INSERT INTO donacion (
    donacion_id, institucion_id, usuario_id_responsable, fecha_envio,
    numero_guia_remision, documento_referencia_guia
) VALUES
(1, 1, 6, '2026-06-20', 'GR-2026-001', 'Entrega de laptop y periféricos.'),
(2, 2, 6, '2026-06-21', 'GR-2026-002', 'Entrega de equipos básicos para laboratorio.'),
(3, 3, 1, '2026-06-22', 'GR-2026-003', 'Entrega de tablet para aula digital.'),
(4, 4, 5, '2026-06-23', 'GR-2026-004', 'Entrega de monitor y teclado.'),
(5, 5, 6, '2026-06-24', 'GR-2026-005', 'Entrega de computadora completa.'),
(6, 6, 1, '2026-06-25', 'GR-2026-006', 'Entrega de impresora reparada.'),
(7, 7, 5, '2026-06-26', 'GR-2026-007', 'Entrega de periféricos para oficina.'),
(8, 8, 6, '2026-06-27', 'GR-2026-008', 'Entrega de equipos mixtos.'),
(9, 9, 1, '2026-06-28', 'GR-2026-009', 'Entrega de equipo de conectividad.'),
(10, 10, 5, '2026-06-29', 'GR-2026-010', 'Entrega complementaria de activos tecnológicos.');

-- DETALLE DONACION (10)
INSERT INTO detalle_donacion (donacion_id, equipo_id) VALUES
(1, 4),
(2, 6),
(3, 9),
(4, 3),
(5, 2),
(6, 5),
(7, 7),
(8, 1),
(9, 10),
(10, 8);