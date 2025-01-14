import { getConnection } from "../config/db.js";

// Obtener todos los cursos
export const getCursos = (req, res) => {
    const db = getConnection();
    db.query("SELECT * FROM Cursos", (err, results) => {
        if (err) {
            console.error("Error al obtener los cursos:", err);
            res.status(500).json({ error: "Error al obtener los cursos" });
            return;
        }
        res.json(results);
    });
};

// Crear un nuevo curso
export const createCurso = (req, res) => {
    const { nombre, descripcion, duracion } = req.body;
    const db = getConnection();
    db.query(
        "INSERT INTO Cursos (nombre, descripcion, duracion) VALUES (?, ?, ?)",
        [nombre, descripcion, duracion],
        (err) => {
            if (err) {
                console.error("Error al crear el curso:", err);
                res.status(500).json({ error: "Error al crear el curso" });
                return;
            }
            res.status(201).json({ message: "Curso creado exitosamente" });
        }
    );
};

// Actualizar un curso
export const updateCurso = (req, res) => {
    const { id } = req.params;
    const { nombre, descripcion, duracion } = req.body;
    const db = getConnection();
    db.query(
        "UPDATE Cursos SET nombre = ?, descripcion = ?, duracion = ? WHERE id = ?",
        [nombre, descripcion, duracion, id],
        (err) => {
            if (err) {
                console.error("Error al actualizar el curso:", err);
                res.status(500).json({ error: "Error al actualizar el curso" });
                return;
            }
            res.json({ message: "Curso actualizado exitosamente" });
        }
    );
};

// Eliminar un curso
export const deleteCurso = (req, res) => {
    const { id } = req.params;
    const db = getConnection();
    db.query("DELETE FROM Cursos WHERE id = ?", [id], (err) => {
        if (err) {
            console.error("Error al eliminar el curso:", err);
            res.status(500).json({ error: "Error al eliminar el curso" });
            return;
        }
        res.json({ message: "Curso eliminado exitosamente" });
    });
};
