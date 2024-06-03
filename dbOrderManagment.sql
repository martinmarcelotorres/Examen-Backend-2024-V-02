-- Verificar si la base de datos OrderManagment ya existe
SELECT datname FROM pg_database WHERE datname = 'dbOrderManagment';

-- Si la base de datos no existe, crearla
CREATE DATABASE dbOrderManagment;

-- Crear la tabla de usuarios (clientes) si no existe
CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
	apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear la tabla de productos si no existe
CREATE TABLE IF NOT EXISTS productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
    estado BOOLEAN NOT NULL DEFAULT TRUE
);