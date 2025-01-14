package com.example.crudapp.ui.cursos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.data.models.Curso

class CursoAdapter(
    private val cursos: List<Curso>,
    private val onEditClick: (Curso) -> Unit,
    private val onDeleteClick: (Curso) -> Unit
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    // Clase interna para el ViewHolder
    class CursoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombreCurso)
        val descripcion: TextView = view.findViewById(R.id.tvDescripcionCurso)
        val duracion: TextView = view.findViewById(R.id.tvDuracionCurso)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEditCurso)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDeleteCurso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        // Inflar el diseño del ítem del curso
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursos[position]
        holder.nombre.text = curso.nombre
        holder.descripcion.text = curso.descripcion
        holder.duracion.text = "Duración: ${curso.duracion} horas"

        // Configurar clic en el botón de editar
        holder.btnEdit.setOnClickListener { onEditClick(curso) }

        // Configurar clic en el botón de eliminar
        holder.btnDelete.setOnClickListener { onDeleteClick(curso) }
    }

    override fun getItemCount(): Int = cursos.size
}
