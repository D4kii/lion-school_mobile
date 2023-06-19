package br.senai.sp.jandira.lionschool

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.service.RetrofitFactoryCourses
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

    var context = LocalContext.current

    var findCoursesState by remember {
        mutableStateOf("")
    }

    var listCourses by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Courses>())
    }

    var siglaCourses by remember {
        mutableStateOf("")
    }


//    val context = LocalContext.current

    //Cria uma chamada para o endpoint
    val call = RetrofitFactoryCourses().getCoursesService().getCourses()

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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    top = 30.dp,
                                    start = 30.dp
                                    )
                        ) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.computador_portatil
                                ),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(35.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.home_curses),
                                fontSize = 25.sp,
                                color = Color(52, 71, 176),
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = findCoursesState,
                            onValueChange = {
                                findCoursesState = it
                            },
                            modifier = Modifier
                            .fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_search),
                                    contentDescription = ""
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(155, 187, 211)
                            )
                        )

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
                                    siglaCourses = it.sigla
                                    Log.i("Sigla", "Sigla - ${siglaCourses}")
                                    openStudentsActivity(context, siglaCourses)
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
                                            Image(
                                                modifier = Modifier
                                                    .size(25.dp),
                                                painter = painterResource(id = R.drawable.relogio),
                                                contentDescription = "Ícone de tempo")
                                            Spacer(modifier = Modifier.width(5.dp))
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

fun openStudentsActivity(context: Context, siglaCouses: String){
    val openStudentsActivity = Intent(context, StudentsActivity()::class.java)
    openStudentsActivity.putExtra("sigla", siglaCouses.toString())
    context.startActivity(openStudentsActivity)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
         HomeScreen()
    }
}