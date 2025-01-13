package com.example.examenappcrud.dao

import androidx.room.*
import com.example.examenappcrud.entities.Curso

@Dao
interface CursoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurso(curso: Curso): Long

    @Query("SELECT * FROM Cursos")
    suspend fun getAllCursos(): List<Curso>

    @Delete
    suspend fun deleteCurso(curso: Curso)
}