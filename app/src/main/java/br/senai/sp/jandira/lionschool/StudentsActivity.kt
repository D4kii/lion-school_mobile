package br.senai.sp.jandira.lionschool

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactoryCourses
import br.senai.sp.jandira.lionschool.service.RetrofitFactoryStudents
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val sigla = intent.getStringExtra("sigla")
                    StudentsScreen(sigla.toString())
                }
            }
        }
    }
}

@Composable
fun StudentsScreen(sigla: String) {
    var context = LocalContext.current

    var findCoursesState by remember {
        mutableStateOf("")
    }

    var studentsList by remember {
        mutableStateOf(listOf<Students>())
    }

    var nameCourse by remember {
        mutableStateOf("")
    }


//    val context = LocalContext.current

//    //Cria uma chamada para o endpoint
//    var call = RetrofitFactoryCourses.get
//
//    call.enqueue(object : Callback<StudentsList> {
//        override fun onResponse(
//            call: Call<StudentsList>,
//            response: Response<StudentsList>
//        ) {
//            studentsList = response.body()!!.estudantes
//
//            nameCourse = studentsList[1].curso
//            nameCourse.split("00", "-")
//        }
//
//        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
//
//        }
//    })


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
                    text = nameCourse,
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
                        items(studentsList){
                            val matricula = it.matricula
                            Button(onClick = {
                                openStudents(context, matricula)

                            }, modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .padding(bottom = 15.dp, start = 20.dp, end = 20.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = confirmStatus(it.status)),
                                shape = RoundedCornerShape(20.dp),

                                ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = it.foto, contentDescription = "",
                                        modifier = Modifier.height(116.dp).width(117.dp),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = it.nome,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 21.sp,
                                        textAlign = TextAlign.Center
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



fun openStudents(context: Context, matricula: String){
    val openStudent = Intent(context, StudentProfileActivity()::class.java)
    openStudent.putExtra("matricula", matricula.toString())
    context.startActivity(openStudent)
}

fun confirmStatus(status: String): Color {
    var statusAluno = status.toString()
    var colorFinalizado = Color(255, 194, 62)
    var colorCursando = Color(51, 71, 176)

    if(statusAluno == "Cursando"){
        return colorCursando
    } else {
        return colorFinalizado
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    LionSchoolTheme {
        StudentsScreen(sigla = "DS")
    }
}