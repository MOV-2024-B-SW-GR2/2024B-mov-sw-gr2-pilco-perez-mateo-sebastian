import '../models/curso.dart';
import '../services/curso_service.dart';

class CursoController {
  final CursoService _cursoService = CursoService();

  Future<List<Curso>> fetchCursos() async {
    return await _cursoService.getCursos();
  }

  Future<void> createCurso(Curso curso) async {
    await _cursoService.addCurso(curso);
  }

  Future<void> updateCurso(Curso curso) async {
    await _cursoService.updateCurso(curso.id, curso);
  }

  Future<void> deleteCurso(int id) async {
    await _cursoService.deleteCurso(id);
  }
}
