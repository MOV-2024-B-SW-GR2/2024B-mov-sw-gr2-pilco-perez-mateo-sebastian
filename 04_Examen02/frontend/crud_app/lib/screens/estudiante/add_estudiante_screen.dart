import 'package:flutter/material.dart';
import '../../controllers/estudiante_controller.dart';
import '../../models/estudiante.dart';

class AddEstudianteScreen extends StatelessWidget {
  final TextEditingController nombreController = TextEditingController();
  final TextEditingController edadController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController telefonoController = TextEditingController();

  final int cursoId; // Curso asociado
  final EstudianteController estudianteController = EstudianteController();

  AddEstudianteScreen({required this.cursoId});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Agregar Estudiante"),
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
                final estudiante = Estudiante(
                  id: 0,
                  nombre: nombreController.text,
                  edad: int.parse(edadController.text),
                  email: emailController.text,
                  telefono: telefonoController.text,
                  cursoId: cursoId,
                );
                await estudianteController.createEstudiante(estudiante);
                Navigator.pop(context);
              },
              child: Text("Guardar"),
            ),
          ],
        ),
      ),
    );
  }
}
