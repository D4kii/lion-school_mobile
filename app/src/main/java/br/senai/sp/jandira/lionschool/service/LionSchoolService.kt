package br.senai.sp.jandira.lionschool.service
//package br.senai.sp.jandira.rickandmorty.service

import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.model.Registration
import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LionSchoolService {


        @GET("cursos")
        fun getCourses(): Call<CoursesList>


        @GET("alunos")
        fun getAlunosDoCurso (@Query("cursos") sigla: String): Call<StudentsList>

        @GET("alunos/{matricula}")
        fun getAlunoDoCurso (@Path("matricula") matricula: String): Call<Registration>




}