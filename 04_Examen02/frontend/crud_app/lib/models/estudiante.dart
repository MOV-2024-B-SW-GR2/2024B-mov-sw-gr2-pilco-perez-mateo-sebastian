class Estudiante {
  final int id;
  final String nombre;
  final int edad;
  final String email;
  final String telefono;
  final int cursoId;

  Estudiante({
    required this.id,
    required this.nombre,
    required this.edad,
    required this.email,
    required this.telefono,
    required this.cursoId,
  });

  factory Estudiante.fromJson(Map<String, dynamic> json) {
    return Estudiante(
      id: json['id'],
      nombre: json['nombre'],
      edad: json['edad'],
      email: json['email'],
      telefono: json['telefono'],
      cursoId: json['curso_id'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'nombre': nombre,
      'edad': edad,
      'email': email,
      'telefono': telefono,
      'curso_id': cursoId,
    };
  }
}
