import 'package:flutter/material.dart';
import '../../controllers/curso_controller.dart';
import '../../models/curso.dart';

class AddCursoScreen extends StatelessWidget {
  final TextEditingController nombreController = TextEditingController();
  final TextEditingController descripcionController = TextEditingController();
  final TextEditingController duracionController = TextEditingController();

  final CursoController cursoController = CursoController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Agregar Curso"),
        backgroundColor: Colors.blue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            TextField(
              controller: nombreController,
              decoration: InputDecoration(
                labelText: "Nombre del Curso",
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 16),
            TextField(
              controller: descripcionController,
              decoration: InputDecoration(
                labelText: "Descripción",
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 16),
            TextField(
              controller: duracionController,
              decoration: InputDecoration(
                labelText: "Duración (en horas)",
                border: OutlineInputBorder(),
              ),
              keyboardType: TextInputType.number,
            ),
            SizedBox(height: 32),
            Center(
              child: ElevatedButton(
                onPressed: () async {
                  if (nombreController.text.isNotEmpty &&
                      duracionController.text.isNotEmpty) {
                    final curso = Curso(
                      id: 0, // Ignorado, lo asigna el backend
                      nombre: nombreController.text,
                      descripcion: descripcionController.text,
                      duracion: int.parse(duracionController.text),
                    );
                    try {
                      await cursoController.createCurso(curso);
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text("Curso agregado con éxito")),
                      );
                      Navigator.pop(context); // Regresa a la lista
                    } catch (e) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text("Error al agregar curso: $e")),
                      );
                    }
                  } else {
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                          content: Text("Por favor completa todos los campos")),
                    );
                  }
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.blue,
                  padding: const EdgeInsets.symmetric(
                    vertical: 12.0,
                    horizontal: 24.0,
                  ),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                child: Text("Guardar"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
