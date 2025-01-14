package com.example.crudapp.data.api
import com.example.crudapp.data.models.Estudiante
import retrofit2.http.*

interface EstudianteApi {
    @GET("estudiantes")
    suspend fun getEstudiantes(): List<Estudiante>

    @POST("estudiantes")
    suspend fun createEstudiante(@Body estudiante: Estudiante): retrofit2.Response<Void>

    @PUT("estudiantes/{id}")
    suspend fun updateEstudiante(@Path("id") id: Int, @Body estudiante: Estudiante): retrofit2.Response<Void>

    @DELETE("estudiantes/{id}")
    suspend fun deleteEstudiante(@Path("id") id: Int): retrofit2.Response<Void>
}