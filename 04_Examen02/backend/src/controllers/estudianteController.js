import { getConnection } from "../config/db.js";

// Obtener todos los estudiantes
export const getEstudiantes = (req, res) => {
    const db = getConnection();
    db.query("SELECT * FROM Estudiantes", (err, results) => {
        if (err) {
            console.error("Error al obtener los estudiantes:", err);
            res.status(500).json({ error: "Error al obtener los estudiantes" });
            return;
        }
        res.json(results);
    });
};

// Obtener estudiantes por curso ID
export const getEstudiantesByCursoId = (req, res) => {
    const { cursoId } = req.params;
    const db = getConnection();
    db.query(
        "SELECT * FROM Estudiantes WHERE curso_id = ?",
        [cursoId],
        (err, results) => {
            if (err) {
                console.error(
                    `Error al obtener los estudiantes del curso ${cursoId}:`,
                    err
                );
                res.status(500).json({
                    error: `Error al obtener los estudiantes del curso ${cursoId}`,
                });
                return;
            }
            res.json(results);
        }
    );
};

// Crear un nuevo estudiante
export const createEstudiante = (req, res) => {
    const { nombre, edad, email, telefono, curso_id } = req.body;
    const db = getConnection();
    db.query(
        "INSERT INTO Estudiantes (nombre, edad, email, telefono, curso_id) VALUES (?, ?, ?, ?, ?)",
        [nombre, edad, email, telefono, curso_id],
        (err) => {
            if (err) {
                console.error("Error al crear el estudiante:", err);
                res.status(500).json({ error: "Error al crear el estudiante" });
                return;
            }
            res.status(201).json({ message: "Estudiante creado exitosamente" });
        }
    );
};

// Actualizar un estudiante
export const updateEstudiante = (req, res) => {
    const { id } = req.params;
    const { nombre, edad, email, telefono, curso_id } = req.body;
    const db = getConnection();
    db.query(
        "UPDATE Estudiantes SET nombre = ?, edad = ?, email = ?, telefono = ?, curso_id = ? WHERE id = ?",
        [nombre, edad, email, telefono, curso_id, id],
        (err) => {
            if (err) {
                console.error("Error al actualizar el estudiante:", err);
                res.status(500).json({ error: "Error al actualizar el estudiante" });
                return;
            }
            res.json({ message: "Estudiante actualizado exitosamente" });
        }
    );
};

// Eliminar un estudiante
export const deleteEstudiante = (req, res) => {
    const { id } = req.params;
    const db = getConnection();
    db.query("DELETE FROM Estudiantes WHERE id = ?", [id], (err) => {
        if (err) {
            console.error("Error al eliminar el estudiante:", err);
            res.status(500).json({ error: "Error al eliminar el estudiante" });
            return;
        }
        res.json({ message: "Estudiante eliminado exitosamente" });
    });
};
