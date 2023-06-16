package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryCourses {
    private val BASE_URL = "https://alive-bull-leotard.cyclic.app/v1/lion-school/"

    private val retrofitFactoryCurses = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Cursos
    fun getCoursesService(): LionSchoolService{
        return retrofitFactoryCurses.create(LionSchoolService::class.java)
    }


}