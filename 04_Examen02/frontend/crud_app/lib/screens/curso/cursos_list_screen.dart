import 'package:flutter/material.dart';
import '../../controllers/curso_controller.dart';
import '../../models/curso.dart';
import 'add_curso_screen.dart';
import 'edit_curso_screen.dart';
import '../estudiante/estudiantes_list_screen.dart';

class CursosListScreen extends StatefulWidget {
  const CursosListScreen({Key? key}) : super(key: key);

  @override
  _CursosListScreenState createState() => _CursosListScreenState();
}

class _CursosListScreenState extends State<CursosListScreen> {
  final CursoController cursoController = CursoController();
  List<Curso> cursos = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    _fetchCursos();
  }

  Future<void> _fetchCursos() async {
    setState(() {
      isLoading = true;
    });
    try {
      final result = await cursoController.fetchCursos();
      setState(() {
        cursos = result;
      });
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Error al cargar cursos: $error")),
      );
    } finally {
      setState(() {
        isLoading = false;
      });
    }
  }

  Future<void> _deleteCurso(int cursoId) async {
    try {
      await cursoController.deleteCurso(cursoId);
      _fetchCursos(); // Refresca la lista después de eliminar
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Curso eliminado")),
      );
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Error al eliminar curso: $error")),
      );
    }
  }

  void _showOptions(BuildContext context, Curso curso) {
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
                    builder: (context) => EditCursoScreen(curso: curso),
                  ),
                ).then((_) => _fetchCursos());
              },
            ),
            ListTile(
              leading: Icon(Icons.people, color: Colors.green),
              title: Text("Ver estudiantes"),
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => EstudiantesListScreen(
                      cursoId: curso.id!,
                      cursoNombre: curso.nombre,
                    ),
                  ),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.delete, color: Colors.red),
              title: Text("Eliminar"),
              onTap: () {
                Navigator.pop(context);
                _deleteCurso(curso.id!);
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
        title: Text("Lista de Cursos"),
      ),
      body: isLoading
          ? Center(child: CircularProgressIndicator(color: Colors.blue))
          : cursos.isEmpty
              ? Center(child: Text("No hay cursos disponibles"))
              : ListView.builder(
                  itemCount: cursos.length,
                  itemBuilder: (context, index) {
                    final curso = cursos[index];
                    return GestureDetector(
                      onLongPress: () => _showOptions(context, curso),
                      child: Card(
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                        margin: const EdgeInsets.symmetric(
                            vertical: 8, horizontal: 16),
                        child: ListTile(
                          leading: CircleAvatar(
                            backgroundColor: Colors.blue[100],
                            child: Icon(Icons.school, color: Colors.blue),
                          ),
                          title: Text(
                            curso.nombre,
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          subtitle: Text(
                            "Duración: ${curso.duracion} horas",
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
            MaterialPageRoute(builder: (context) => AddCursoScreen()),
          ).then((_) => _fetchCursos());
        },
        backgroundColor: Colors.blue,
        child: Icon(Icons.add, color: Colors.white),
      ),
    );
  }
}
