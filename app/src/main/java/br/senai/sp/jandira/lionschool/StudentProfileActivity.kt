package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.lionschool.model.Registration
import br.senai.sp.jandira.lionschool.model.SubjectsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactoryCourses
import br.senai.sp.jandira.lionschool.ui.theme.ui.theme.LionSchoolTheme
import retrofit2.Response
import javax.security.auth.callback.Callback

class StudentProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileStudentScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileStudentScreen() {



}
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    LionSchoolTheme {
        ProfileStudentScreen()
    }
}