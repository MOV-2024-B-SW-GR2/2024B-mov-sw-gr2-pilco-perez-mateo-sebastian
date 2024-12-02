package org.example

import org.example.controller.controlador
import org.example.entities.Curso
import javax.swing.JOptionPane

fun main() {
    val controlador = controlador()

    while (true) {
        val menu = """
            ---- Menú ----
            1. Listar Cursos
            2. Agregar Curso
            3. Eliminar Curso
            4. Agregar Estudiante a Curso
            5. Listar Estudiantes de Curso
            6. Eliminar Estudiante de Curso
            7. Actualizar Estudiante
            8. Salir
        """.trimIndent()

        val opcion = JOptionPane.showInputDialog(menu)?.toIntOrNull()
        when (opcion) {
            1 -> {
                val cursos = controlador.listarCurso()
                val mensaje = if (cursos.isEmpty()) {
                    "No hay cursos disponibles"
                } else {
                    cursos.joinToString("\n") { curso ->
                        "ID: ${curso.id}, Nombre: ${curso.nombre}, Descripción: ${curso.descripcion}"
                    }
                }
                JOptionPane.showMessageDialog(null, mensaje)
            }
            2 -> {
                val id = JOptionPane.showInputDialog("Ingrese el ID del curso:")?.toIntOrNull() ?: return
                val nombre = JOptionPane.showInputDialog("Ingrese el nombre del curso:") ?: ""
                val descripcion = JOptionPane.showInputDialog("Ingrese la descripción del curso:") ?: ""
                val duracion = JOptionPane.showInputDialog("Ingrese la duración del curso (en meses):")?.toIntOrNull() ?: return

                controlador.agregarCurso(id, nombre, descripcion, duracion)
                JOptionPane.showMessageDialog(null, "Curso '$nombre' agregado con éxito!")
            }
            3 -> {
                val idCurso = JOptionPane.showInputDialog("Ingrese el ID del curso a eliminar:")?.toIntOrNull() ?: return
                val eliminado = controlador.eliminarCurso(idCurso)
                val mensaje = if (eliminado) {
                    "Curso con ID $idCurso eliminado con éxito!"
                } else {
                    "Curso no encontrado"
                }
                JOptionPane.showMessageDialog(null, mensaje)
            }
            4 -> {
                val idCurso = JOptionPane.showInputDialog("Seleccione el ID del curso para agregar un estudiante:")?.toIntOrNull() ?: return
                val idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:")?.toIntOrNull() ?: return
                val nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:") ?: ""
                val edad = JOptionPane.showInputDialog("Ingrese la edad del estudiante:")?.toIntOrNull() ?: return
                val email = JOptionPane.showInputDialog("Ingrese el email del estudiante:") ?: ""
                val telefono = JOptionPane.showInputDialog("Ingrese el teléfono del estudiante:")?.toIntOrNull() ?: return

                controlador.agregarEstudianteACurso(idCurso, idEstudiante, nombre, edad, email, telefono)
                JOptionPane.showMessageDialog(null, "Estudiante '$nombre' agregado con éxito!")
            }
            5 -> {
                val idCurso = JOptionPane.showInputDialog("Seleccione el ID del curso para listar a los estudiantes:")?.toIntOrNull() ?: return
                val estudiantes = controlador.listarEstudiantesCurso(idCurso)
                val mensaje = if (estudiantes.isEmpty()) {
                    "No hay estudiantes en este curso"
                } else {
                    estudiantes.joinToString("\n") { estudiante ->
                        "ID: ${estudiante.id}, Nombre: ${estudiante.nombre}, Edad: ${estudiante.edad}, Email: ${estudiante.email}"
                    }
                }
                JOptionPane.showMessageDialog(null, mensaje)
            }
            6 -> {
                val idCurso = JOptionPane.showInputDialog("Seleccione el ID del curso para eliminar un estudiante:")?.toIntOrNull() ?: return
                val idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante a eliminar:")?.toIntOrNull() ?: return
                val eliminado = controlador.eliminarEstudianteDeCurso(idEstudiante, idCurso)
                val mensaje = if (eliminado) {
                    "Estudiante con ID $idEstudiante eliminado con éxito!"
                } else {
                    "Estudiante no encontrado!"
                }
                JOptionPane.showMessageDialog(null, mensaje)
            }
            7 -> {
                val idCurso = JOptionPane.showInputDialog("Seleccione el ID del curso para actualizar un estudiante:")?.toIntOrNull() ?: return
                val idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante a actualizar:")?.toIntOrNull() ?: return
                val nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del estudiante:") ?: ""
                val edad = JOptionPane.showInputDialog("Ingrese la nueva edad del estudiante:")?.toIntOrNull() ?: return
                val email = JOptionPane.showInputDialog("Ingrese el nuevo email del estudiante:") ?: ""
                val telefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono del estudiante:")?.toIntOrNull() ?: return

                val actualizado = controlador.actualizarEstudiante(idCurso, idEstudiante, nombre, edad, email, telefono)
                val mensaje = if (actualizado) {
                    "Estudiante '$nombre' actualizado con éxito!"
                } else {
                    "Estudiante no encontrado!"
                }
                JOptionPane.showMessageDialog(null, mensaje)
            }
            8 -> break
            else -> JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo!")
        }
    }
}
