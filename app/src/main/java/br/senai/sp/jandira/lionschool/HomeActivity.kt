package br.senai.sp.jandira.lionschool

import android.annotation.SuppressLint
import android.icu.text.IDNA.Info
import android.os.Bundle
import android.telecom.Call
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Response

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme

                HomeScreen()
            }
        }
    }
}
@SuppressLint("ResourceType")
@Composable
fun HomeScreen() {

    var listCourses by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Courses>())
    }


//    val context = LocalContext.current

    //Cria uma chamada para o endpoint
    val call = RetrofitFactory().getCoursesService().getCourses()

    //Executar a chamada
    call.enqueue(object : retrofit2.Callback<CoursesList>{
        override fun onResponse(
            call: retrofit2.Call<CoursesList>,
            response: Response<CoursesList>
        ) {
            listCourses = response.body()!!.cursos
        }

        override fun onFailure(call: retrofit2.Call<CoursesList>, t: Throwable) {

        }
    })


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(255, 196, 75)),
            verticalArrangement = Arrangement.SpaceBetween
                ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {

                    Image(
                        modifier = Modifier
                            .height(40.dp),
                        painter = painterResource(id = R.drawable.lionschool_logo),
                        contentDescription = "Logo"
                    )


            }

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.home_greeting
                        ),
                        fontSize = 50.sp,
                        fontWeight = FontWeight(weight = 200),
                        color = Color(255, 255, 255)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.home_welcome
                        ),
                        fontSize = 30.sp,
                        fontWeight = FontWeight(weight = 200),
                        color = Color(255, 255, 255)
                    )

                }


            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .shadow(
                        40.dp,
                        shape = RoundedCornerShape(0.dp),
                        true,
                        Color(52, 71, 176)
                    ),
                color = Color(255, 255, 255),
                shape = RoundedCornerShape( topEnd = 40.dp,
                    topStart = 40.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        
                    }
                    LazyColumn(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    ) {
                        items(listCourses){
                            Button(
                                modifier = Modifier
                                    .height(200.dp)
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(Color(255,196,75)),
                                onClick = {

                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(20.dp)
                                ){
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(100.dp),
                                        colorFilter = ColorFilter.tint(Color(52,71,176)),
                                        model = it.icone,
                                        contentDescription = "Icone do Curso")
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(20.dp)
                                ) {
                                    Text(
                                        text = it.sigla,
                                        fontSize = 50.sp,
                                        fontWeight = FontWeight(weight = 800),
                                        color = Color(52,71,176)
                                    )
                                    Text(
                                        text = it.nome,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(weight = 800),
                                        color = Color(52,71,176)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Row {
                                            Icon(
                                                modifier = Modifier
                                                    .size(24.dp),
                                                painter = painterResource(id = R.drawable.time_filled_24),
                                                contentDescription = "Ícone de tempo")
                                            Text(
                                                text = it.carga,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight(weight = 800),
                                                color = Color(52,71,176)
                                            )
                                        }

                                    }

                                }


                            }


                        }

                    }

                }

            }
        }

    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        HomeScreen()
    }
}