package com.example.crudapp.ui.estudiantes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.data.models.Estudiante

class EstudianteAdapter(
    private val estudiantes: List<Estudiante>,
    private val onEditClick: (Estudiante) -> Unit,
    private val onDeleteClick: (Estudiante) -> Unit
) : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    class EstudianteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombreEstudiante)
        val email: TextView = view.findViewById(R.id.tvEmailEstudiante)
        val telefono: TextView = view.findViewById(R.id.tvTelefonoEstudiante)
        val btnEdit: View = view.findViewById(R.id.btnEditEstudiante)
        val btnDelete: View = view.findViewById(R.id.btnDeleteEstudiante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante, parent, false)
        return EstudianteViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        val estudiante = estudiantes[position]
        holder.nombre.text = estudiante.nombre
        holder.email.text = estudiante.email
        holder.telefono.text = estudiante.telefono
        holder.btnEdit.setOnClickListener { onEditClick(estudiante) }
        holder.btnDelete.setOnClickListener { onDeleteClick(estudiante) }
    }

    override fun getItemCount(): Int = estudiantes.size
}