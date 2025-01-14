import '../services/estudiante_service.dart';
import '../models/estudiante.dart';

class EstudianteController {
  final EstudianteService _estudianteService = EstudianteService();

  Future<List<Estudiante>> fetchEstudiantes() async {
    return await _estudianteService.getEstudiantes();
  }

  Future<List<Estudiante>> fetchEstudiantesByCurso(int cursoId) async {
    return await _estudianteService.getEstudiantesByCurso(cursoId);
  }

  Future<void> createEstudiante(Estudiante estudiante) async {
    await _estudianteService.addEstudiante(estudiante);
  }

  Future<void> updateEstudiante(Estudiante estudiante) async {
    await _estudianteService.updateEstudiante(estudiante.id, estudiante);
  }

  Future<void> deleteEstudiante(int id) async {
    await _estudianteService.deleteEstudiante(id);
  }
}
