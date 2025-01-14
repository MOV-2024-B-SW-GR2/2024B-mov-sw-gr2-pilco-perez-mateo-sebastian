class Curso {
  final int id;
  final String nombre;
  final String descripcion;
  final int duracion;

  Curso(
      {required this.id,
      required this.nombre,
      required this.descripcion,
      required this.duracion});

  factory Curso.fromJson(Map<String, dynamic> json) {
    return Curso(
      id: json['id'],
      nombre: json['nombre'],
      descripcion: json['descripcion'],
      duracion: json['duracion'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'nombre': nombre,
      'descripcion': descripcion,
      'duracion': duracion,
    };
  }
}
