import 'package:flutter/material.dart';
import '../../controllers/curso_controller.dart';
import '../../models/curso.dart';

class EditCursoScreen extends StatelessWidget {
  final Curso curso;
  final CursoController cursoController = CursoController();

  EditCursoScreen({required this.curso});

  @override
  Widget build(BuildContext context) {
    final TextEditingController nombreController =
        TextEditingController(text: curso.nombre);
    final TextEditingController descripcionController =
        TextEditingController(text: curso.descripcion);
    final TextEditingController duracionController =
        TextEditingController(text: curso.duracion.toString());

    return Scaffold(
      appBar: AppBar(
        title: Text("Editar Curso"),
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
                    final updatedCurso = Curso(
                      id: curso.id,
                      nombre: nombreController.text,
                      descripcion: descripcionController.text,
                      duracion: int.parse(duracionController.text),
                    );
                    try {
                      await cursoController.updateCurso(updatedCurso);
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text("Curso actualizado con éxito")),
                      );
                      Navigator.pop(context);
                    } catch (e) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(
                            content: Text("Error al actualizar curso: $e")),
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
                child: Text("Actualizar"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
