import 'package:flutter/material.dart';
import 'screens/curso/cursos_list_screen.dart';
import 'screens/curso/add_curso_screen.dart';
import 'screens/estudiante/add_estudiante_screen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CRUD App',
      theme: ThemeData(primarySwatch: Colors.blue),
      initialRoute: '/',
      routes: {
        '/': (context) => CursosListScreen(),
        '/add_curso': (context) => AddCursoScreen(),
        '/add_estudiante': (context) =>
            AddEstudianteScreen(cursoId: 0), // Se pasa ID dinámicamente
      },
    );
  }
}
