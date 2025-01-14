import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/estudiante.dart';

class EstudianteService {
  static const String baseUrl =
      "http://192.168.3.8:3111/estudiantes"; // Cambia según tu configuración

  /// Obtener todos los estudiantes
  Future<List<Estudiante>> getEstudiantes() async {
    final response = await http.get(Uri.parse(baseUrl));
    if (response.statusCode == 200) {
      List<dynamic> body = jsonDecode(response.body);
      return body.map((estudiante) => Estudiante.fromJson(estudiante)).toList();
    } else {
      throw Exception("Error al obtener los estudiantes");
    }
  }

  /// Obtener estudiantes de un curso específico
  Future<List<Estudiante>> getEstudiantesByCurso(int cursoId) async {
    final response = await http.get(Uri.parse("$baseUrl/curso/$cursoId"));
    if (response.statusCode == 200) {
      List<dynamic> body = jsonDecode(response.body);
      return body.map((estudiante) => Estudiante.fromJson(estudiante)).toList();
    } else {
      throw Exception("Error al obtener estudiantes del curso $cursoId");
    }
  }

  /// Agregar un nuevo estudiante
  Future<void> addEstudiante(Estudiante estudiante) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode(estudiante.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception("Error al agregar el estudiante");
    }
  }

  /// Actualizar un estudiante existente
  Future<void> updateEstudiante(int id, Estudiante estudiante) async {
    final response = await http.put(
      Uri.parse("$baseUrl/$id"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode(estudiante.toJson()),
    );
    if (response.statusCode != 200) {
      throw Exception("Error al actualizar el estudiante");
    }
  }

  /// Eliminar un estudiante
  Future<void> deleteEstudiante(int id) async {
    final response = await http.delete(Uri.parse("$baseUrl/$id"));
    if (response.statusCode != 200) {
      throw Exception("Error al eliminar el estudiante");
    }
  }
}
