import 'package:flutter/material.dart';
import '../../controllers/estudiante_controller.dart';
import '../../models/estudiante.dart';

class EditEstudianteScreen extends StatelessWidget {
  final Estudiante estudiante;
  final EstudianteController estudianteController = EstudianteController();

  EditEstudianteScreen({required this.estudiante});

  @override
  Widget build(BuildContext context) {
    final TextEditingController nombreController =
        TextEditingController(text: estudiante.nombre);
    final TextEditingController edadController =
        TextEditingController(text: estudiante.edad.toString());
    final TextEditingController emailController =
        TextEditingController(text: estudiante.email);
    final TextEditingController telefonoController =
        TextEditingController(text: estudiante.telefono);

    return Scaffold(
      appBar: AppBar(
        title: Text("Editar Estudiante"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: nombreController,
              decoration: InputDecoration(labelText: "Nombre del Estudiante"),
            ),
            TextField(
              controller: edadController,
              decoration: InputDecoration(labelText: "Edad"),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: emailController,
              decoration: InputDecoration(labelText: "Correo Electrónico"),
            ),
            TextField(
              controller: telefonoController,
              decoration: InputDecoration(labelText: "Teléfono"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () async {
                final updatedEstudiante = Estudiante(
                  id: estudiante.id,
                  nombre: nombreController.text,
                  edad: int.parse(edadController.text),
                  email: emailController.text,
                  telefono: telefonoController.text,
                  cursoId: estudiante.cursoId,
                );
                await estudianteController.updateEstudiante(updatedEstudiante);
                Navigator.pop(context);
              },
              child: Text("Actualizar"),
            ),
          ],
        ),
      ),
    );
  }
}
