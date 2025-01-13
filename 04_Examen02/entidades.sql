CREATE DATABASE cursos_app;

USE cursos_app;

CREATE TABLE Cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion TEXT,
    duracion INT
);

CREATE TABLE Estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    edad INT,
    email VARCHAR(255),
    telefono VARCHAR(15),
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES Cursos(id) ON DELETE CASCADE
);
