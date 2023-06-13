package br.senai.sp.jandira.lionschool.service
//package br.senai.sp.jandira.rickandmorty.service

import br.senai.sp.jandira.lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface LionSchoolService {


        @GET("cursos")
        fun getCourses(): Call<CoursesList>

//        @GET("/alunos")
//        fun getAlunos(@Path("id") id: Long): Call<Character>
//
//        @GET("character/")
//        fun getCharacterByPage(@Query("page") pageNumber: Int): Call<CharacterList>

        //https://rickandmortyapi.com/api/character/?name=rick&status=alive

//        @GET("character/")
//        fun getCharacterByNameAndStatus(
//            @Query("name") name: String,
//            @Query("status") status: String
//        ): Call<CursesList>


}