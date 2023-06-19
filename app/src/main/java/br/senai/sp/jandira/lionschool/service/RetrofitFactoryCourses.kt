package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitFactoryCourses {
    private val BASE_URL = "https://alive-bull-leotard.cyclic.app/v1/lion-school/"

    private val retrofitFactoryCurses = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val BASE_URL2 = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"
    // em caso de haver mais de uma url base, devemos ter duas bases e dois retrofitFactory

    // converte o objeto em json e converte o json em objeto - responsavel pela fabrica
    private val retrofitFactory2 = Retrofit
        .Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getCoursesService(): LionSchoolService{
        return retrofitFactoryCurses.create(LionSchoolService::class.java)
    }

    fun getLionSchoolService(): LionSchoolService{
        return retrofitFactoryCurses.create(LionSchoolService::class.java)
    }

    fun getLionSchoolServiceStudent(): LionSchoolService {
        return retrofitFactory2.create(LionSchoolService::class.java)
    }

}
