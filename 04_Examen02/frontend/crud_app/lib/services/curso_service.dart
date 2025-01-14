import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/curso.dart';

class CursoService {
  static const String baseUrl = "http://192.168.3.8:3111/cursos";

  Future<List<Curso>> getCursos() async {
    final response = await http.get(Uri.parse(baseUrl));
    if (response.statusCode == 200) {
      List<dynamic> body = jsonDecode(response.body);
      return body.map((curso) => Curso.fromJson(curso)).toList();
    } else {
      throw Exception("Error al obtener los cursos");
    }
  }

  Future<void> addCurso(Curso curso) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode(curso.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception("Error al agregar el curso");
    }
  }

  Future<void> updateCurso(int id, Curso curso) async {
    final response = await http.put(
      Uri.parse("$baseUrl/$id"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode(curso.toJson()),
    );
    if (response.statusCode != 200) {
      throw Exception("Error al actualizar el curso");
    }
  }

  Future<void> deleteCurso(int id) async {
    final response = await http.delete(Uri.parse("$baseUrl/$id"));
    if (response.statusCode != 200) {
      throw Exception("Error al eliminar el curso");
    }
  }
}
