package com.example.crudapp.data.api
import com.example.crudapp.data.models.Curso
import retrofit2.http.*

interface CursoApi {
    @GET("cursos")
    suspend fun getCursos(): List<Curso>

    @POST("cursos")
    suspend fun createCurso(@Body curso: Curso): retrofit2.Response<Void>

    @PUT("cursos/{id}")
    suspend fun updateCurso(@Path("id") id: Int, @Body curso: Curso): retrofit2.Response<Void>

    @DELETE("cursos/{id}")
    suspend fun deleteCurso(@Path("id") id: Int): retrofit2.Response<Void>

}