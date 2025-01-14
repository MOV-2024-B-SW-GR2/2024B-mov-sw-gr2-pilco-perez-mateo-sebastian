import 'package:flutter/material.dart';
import '../../controllers/estudiante_controller.dart';
import '../../models/estudiante.dart';
import 'add_estudiante_screen.dart';
import 'edit_estudiante_screen.dart';

class EstudiantesListScreen extends StatefulWidget {
  final int cursoId;
  final String cursoNombre;

  const EstudiantesListScreen({
    Key? key,
    required this.cursoId,
    required this.cursoNombre,
  }) : super(key: key);

  @override
  _EstudiantesListScreenState createState() => _EstudiantesListScreenState();
}

class _EstudiantesListScreenState extends State<EstudiantesListScreen> {
  final EstudianteController estudianteController = EstudianteController();
  List<Estudiante> estudiantes = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchEstudiantes();
  }

  Future<void> _fetchEstudiantes() async {
    setState(() {
      isLoading = true;
    });
    try {
      final result =
          await estudianteController.fetchEstudiantesByCurso(widget.cursoId);
      setState(() {
        estudiantes = result;
      });
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Error al cargar estudiantes: $error")),
      );
    } finally {
      setState(() {
        isLoading = false;
      });
    }
  }

  Future<void> _deleteEstudiante(int estudianteId) async {
    try {
      await estudianteController.deleteEstudiante(estudianteId);
      _fetchEstudiantes(); // Actualiza la lista después de eliminar
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Estudiante eliminado")),
      );
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Error al eliminar estudiante: $error")),
      );
    }
  }

  void _showOptions(BuildContext context, Estudiante estudiante) {
    showModalBottomSheet(
      context: context,
      builder: (_) {
        return Wrap(
          children: [
            ListTile(
              leading: Icon(Icons.edit, color: Colors.blue),
              title: Text("Editar"),
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) =>
                        EditEstudianteScreen(estudiante: estudiante),
                  ),
                ).then((_) => _fetchEstudiantes());
              },
            ),
            ListTile(
              leading: Icon(Icons.delete, color: Colors.red),
              title: Text("Eliminar"),
              onTap: () {
                Navigator.pop(context);
                _deleteEstudiante(estudiante.id!);
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[100],
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          "Estudiantes de ${widget.cursoNombre}",
          style: TextStyle(color: Colors.white),
        ),
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator(color: Colors.blue))
          : estudiantes.isEmpty
              ? Center(
                  child: Text(
                    "No hay estudiantes asignados a este curso",
                    style: TextStyle(color: Colors.grey[700]),
                  ),
                )
              : ListView.builder(
                  itemCount: estudiantes.length,
                  itemBuilder: (context, index) {
                    final estudiante = estudiantes[index];
                    return GestureDetector(
                      onLongPress: () => _showOptions(context, estudiante),
                      child: Card(
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                        margin: const EdgeInsets.symmetric(
                            vertical: 8, horizontal: 16),
                        child: ListTile(
                          leading: CircleAvatar(
                            backgroundColor: Colors.blue[100],
                            child: Icon(Icons.person, color: Colors.blue),
                          ),
                          title: Text(
                            estudiante.nombre,
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          subtitle: Text(
                            "Correo: ${estudiante.email}",
                            style: TextStyle(color: Colors.grey[600]),
                          ),
                        ),
                      ),
                    );
                  },
                ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) =>
                  AddEstudianteScreen(cursoId: widget.cursoId),
            ),
          ).then((_) => _fetchEstudiantes());
        },
        backgroundColor: Colors.blue,
        child: Icon(Icons.add, color: Colors.white),
      ),
    );
  }
}
