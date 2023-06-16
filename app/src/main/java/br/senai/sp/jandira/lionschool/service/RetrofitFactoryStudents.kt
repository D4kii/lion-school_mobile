package br.senai.sp.jandira.lionschool.service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryStudents {

    private val BASE_URL = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"

    private val retrofitFactoryStudents = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Alunos
    fun getStudentService(): LionSchoolService{
        return retrofitFactoryStudents.create(LionSchoolService::class.java)
    }


}