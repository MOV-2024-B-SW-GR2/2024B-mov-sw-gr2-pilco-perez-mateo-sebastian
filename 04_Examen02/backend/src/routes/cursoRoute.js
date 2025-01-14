import { Router } from 'express';
import { getCursos, createCurso, updateCurso, deleteCurso } from "../controllers/cursoController.js";

const router = Router();

router.get("/", getCursos);
router.post("/", createCurso);
router.put("/:id", updateCurso);
router.delete("/:id", deleteCurso);

export default router;
