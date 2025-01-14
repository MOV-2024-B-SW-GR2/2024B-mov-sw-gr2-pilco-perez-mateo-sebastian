import { Router } from "express";
import {
    getEstudiantes,
    createEstudiante,
    updateEstudiante,
    deleteEstudiante,
    getEstudiantesByCursoId, // Agregamos esta función al controlador
} from "../controllers/estudianteController.js";

const router = Router();

router.get("/", getEstudiantes);
router.get("/curso/:cursoId", getEstudiantesByCursoId); // Nueva ruta para obtener estudiantes por curso
router.post("/", createEstudiante);
router.put("/:id", updateEstudiante);
router.delete("/:id", deleteEstudiante);

export default router;
