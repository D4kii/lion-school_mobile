package br.senai.sp.jandira.lionschool

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

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
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.home_greeting
                    ),
                    fontSize = 60.sp,
                    fontWeight = FontWeight(weight = 200),
                    color = Color(255, 255, 255)
                )
                Text(
                    text = stringResource(
                        id = R.string.home_welcome
                    ),
                    fontSize = 40.sp,
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {

                            }
                        ) {
                            
                        }
                        Button(
                            onClick = {

                            }
                        ) {

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