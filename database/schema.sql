-- MySQL schema for gymdb.
-- Reconstructed from the queries in the DAO classes (src/gym/managment/dao).
CREATE DATABASE IF NOT EXISTS gymdb;
USE gymdb;

CREATE TABLE clientes (
    idCliente     INT AUTO_INCREMENT PRIMARY KEY,
    nombreCliente VARCHAR(100) NOT NULL,
    telefono      VARCHAR(30)  NOT NULL,
    dni           INT          NOT NULL
);

CREATE TABLE entrenadores (
    idEntrenador     INT AUTO_INCREMENT PRIMARY KEY,
    nombreEntrenador VARCHAR(100) NOT NULL,
    especialidad     VARCHAR(100) NOT NULL
);

CREATE TABLE actividades (
    idActividad     INT AUTO_INCREMENT PRIMARY KEY,
    nombreActividad VARCHAR(100) NOT NULL,
    descripcion     VARCHAR(255) NOT NULL,
    duracionMinutos INT NOT NULL,
    cupoMaximo      INT NOT NULL,
    idEntrenador    INT NULL,
    fk_cliente      INT NULL,
    FOREIGN KEY (idEntrenador) REFERENCES entrenadores(idEntrenador) ON DELETE SET NULL,
    FOREIGN KEY (fk_cliente)   REFERENCES clientes(idCliente)        ON DELETE SET NULL
);
