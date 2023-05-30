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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme

                LoginScreen()

            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(52, 71, 176)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(50, 60, 172)
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .shadow(
                        40.dp,
                        shape = RoundedCornerShape(0.dp),
                        true,
                        Color(52, 71, 176)
                    ),
                    color = Color(255, 255, 255),
                shape = RoundedCornerShape( bottomEnd = 40.dp,
                                            bottomStart = 40.dp),

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween

                ) {
                    Row(
                        modifier = Modifier
                            .height(400.dp)
                            .width(300.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                            Image(
                                modifier = Modifier
                                    .height(200.dp),
                                painter = painterResource(id = R.drawable.lionschool_logo),
                                contentDescription = "Logo",
                                contentScale = ContentScale.Crop
                            )

                        Spacer(modifier = Modifier
                            .width(5.dp))
                        Text(
                            text = stringResource(id = R.string.app_name),
                            fontSize = 40.sp,
                            color = Color(52, 71, 176),
                            fontWeight = FontWeight(weight = 800)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Button(
                            modifier = Modifier,
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(Color(255, 196, 55)),
                            onClick = { /*TODO*/ }
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp),
                                color = Color(255, 255, 255),
                                text = stringResource(id = R.string.login_button),
                                fontWeight = FontWeight(weight = 800),
                                fontSize = 20.sp
                            )

                            Icon(
                                tint = Color(255, 255, 255),
                                painter = painterResource(id = R.drawable.baseline_arrow_forward),
                                contentDescription = "Enter Icon")
                        }
                    }

                }


            }

            Column(
                modifier = Modifier
                    .height(300.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = Color(255, 255, 255),
                    fontSize = 40.sp,
                    text = stringResource(id = R.string.login_title),
                    fontWeight = FontWeight(weight = 100)
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LionSchoolTheme {
        LoginScreen()
    }
}