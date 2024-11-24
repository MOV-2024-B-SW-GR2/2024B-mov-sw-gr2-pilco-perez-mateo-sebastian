package org.example

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.Stage
import org.example.controller.controlador

class MainWindow : Application() {

    private val controlador = controlador()

    override fun start(stage: Stage) {
        // Crear controles gráficos
        val cursosListView = ListView<String>()
        val estudiantesListView = ListView<String>()
        val idCursoField = TextField()
        val nombreCursoField = TextField()
        val descripcionCursoField = TextField()
        val duracionCursoField = TextField()

        val idEstudianteField = TextField()
        val nombreEstudianteField = TextField()
        val edadEstudianteField = TextField()
        val emailEstudianteField = TextField()
        val telefonoEstudianteField = TextField()

        // Crear menú lateral
        val menu = Menu("Menú")
        val menuBar = MenuBar(menu)

        // Botones de acción
        val listarCursosButton = Button("Listar Cursos")
        val agregarCursoButton = Button("Agregar Curso")
        val eliminarCursoButton = Button("Eliminar Curso")
        val agregarEstudianteButton = Button("Agregar Estudiante a Curso")
        val listarEstudiantesButton = Button("Listar Estudiantes")
        val eliminarEstudianteButton = Button("Eliminar Estudiante")
        val actualizarEstudianteButton = Button("Actualizar Estudiante")

        // Crear paneles de contenido
        val cursosPanel = VBox(10.0, listarCursosButton, agregarCursoButton, eliminarCursoButton)
        val estudiantesPanel = VBox(10.0, agregarEstudianteButton, listarEstudiantesButton, eliminarEstudianteButton, actualizarEstudianteButton)
        val infoPanel = VBox(10.0, Label("ID del Curso"), idCursoField, Label("Nombre del Curso"), nombreCursoField, Label("Descripción del Curso"), descripcionCursoField, Label("Duración del Curso (meses)"), duracionCursoField)
        val estudiantePanel = VBox(10.0, Label("ID Estudiante"), idEstudianteField, Label("Nombre Estudiante"), nombreEstudianteField, Label("Edad Estudiante"), edadEstudianteField, Label("Email Estudiante"), emailEstudianteField, Label("Teléfono Estudiante"), telefonoEstudianteField)

        val rootPanel = BorderPane()

        // Crear la acción del botón de listar cursos
        listarCursosButton.setOnAction {
            cursosListView.items.clear()
            val cursos = controlador.listarCurso()
            if (cursos.isEmpty()) {
                cursosListView.items.add("No hay cursos disponibles")
            } else {
                cursos.forEach { curso -> cursosListView.items.add("ID: ${curso.id}, Nombre: ${curso.nombre}") }
            }
            rootPanel.center = cursosListView
        }

        // Acción de agregar un curso
        agregarCursoButton.setOnAction {
            val id = idCursoField.text.toIntOrNull()
            val nombre = nombreCursoField.text
            val descripcion = descripcionCursoField.text
            val duracion = duracionCursoField.text.toIntOrNull()

            if (id != null && nombre.isNotEmpty() && descripcion.isNotEmpty() && duracion != null) {
                controlador.agregarCurso(id, nombre, descripcion, duracion)
                println("Curso $nombre agregado con éxito!")
            } else {
                println("Por favor complete todos los campos correctamente.")
            }
        }

        // Acción de eliminar curso
        eliminarCursoButton.setOnAction {
            val id = idCursoField.text.toIntOrNull()
            if (id != null) {
                val eliminado = controlador.eliminarCurso(id)
                if (eliminado) {
                    println("Curso eliminado con éxito!")
                } else {
                    println("Curso no encontrado.")
                }
            } else {
                println("ID de curso inválido.")
            }
        }

        // Acción de agregar estudiante a curso
        agregarEstudianteButton.setOnAction {
            val idCurso = idCursoField.text.toIntOrNull()
            val idEstudiante = idEstudianteField.text.toIntOrNull()
            val nombre = nombreEstudianteField.text
            val edad = edadEstudianteField.text.toIntOrNull()
            val email = emailEstudianteField.text
            val telefono = telefonoEstudianteField.text.toIntOrNull()

            if (idCurso != null && idEstudiante != null && nombre.isNotEmpty() && edad != null && email.isNotEmpty() && telefono != null) {
                controlador.agregarEstudianteACurso(idCurso, idEstudiante, nombre, edad, email, telefono)
                println("Estudiante agregado al curso con éxito!")
            } else {
                println("Por favor complete todos los campos correctamente.")
            }
        }

        // Acción de listar estudiantes
        listarEstudiantesButton.setOnAction {
            estudiantesListView.items.clear()
            val idCurso = idCursoField.text.toIntOrNull()
            if (idCurso != null) {
                val estudiantes = controlador.listarEstudiantesCurso(idCurso)
                if (estudiantes.isEmpty()) {
                    estudiantesListView.items.add("No hay estudiantes en este curso.")
                } else {
                    estudiantes.forEach { estudiante -> estudiantesListView.items.add("ID: ${estudiante.id}, Nombre: ${estudiante.nombre}") }
                }
            } else {
                println("ID de curso inválido.")
            }
            rootPanel.center = estudiantesListView
        }

        // Acción de eliminar estudiante
        eliminarEstudianteButton.setOnAction {
            val idCurso = idCursoField.text.toIntOrNull()
            val idEstudiante = idEstudianteField.text.toIntOrNull()
            if (idCurso != null && idEstudiante != null) {
                val eliminado = controlador.eliminarEstudianteDeCurso(idEstudiante, idCurso)
                if (eliminado) {
                    println("Estudiante eliminado con éxito!")
                } else {
                    println("Estudiante no encontrado.")
                }
            } else {
                println("ID de estudiante o curso inválido.")
            }
        }

        // Acción de actualizar estudiante
        actualizarEstudianteButton.setOnAction {
            val idCurso = idCursoField.text.toIntOrNull()
            val idEstudiante = idEstudianteField.text.toIntOrNull()
            val nombre = nombreEstudianteField.text
            val edad = edadEstudianteField.text.toIntOrNull()
            val email = emailEstudianteField.text
            val telefono = telefonoEstudianteField.text.toIntOrNull()

            if (idCurso != null && idEstudiante != null && nombre.isNotEmpty() && edad != null && email.isNotEmpty() && telefono != null) {
                val actualizado = controlador.actualizarEstudiante(idCurso, idEstudiante, nombre, edad, email, telefono)
                if (actualizado) {
                    println("Estudiante actualizado con éxito!")
                } else {
                    println("Estudiante no encontrado.")
                }
            } else {
                println("Por favor complete todos los campos correctamente.")
            }
        }

        // Asignar los paneles al menú
        menu.items.addAll(MenuItem("Cursos").apply {
            setOnAction { rootPanel.center = cursosPanel }
        })
        menu.items.addAll(MenuItem("Estudiantes").apply {
            setOnAction { rootPanel.center = estudiantesPanel }
        })
        menu.items.addAll(MenuItem("Información del Curso").apply {
            setOnAction { rootPanel.center = infoPanel }
        })
        menu.items.addAll(MenuItem("Estudiante Información").apply {
            setOnAction { rootPanel.center = estudiantePanel }
        })

        // Contenedor principal
        rootPanel.top = menuBar
        rootPanel.center = cursosPanel

        // Configuración de la escena
        val scene = Scene(rootPanel, 600.0, 600.0)
        stage.title = "Gestión de Cursos y Estudiantes"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(MainWindow::class.java)
}
