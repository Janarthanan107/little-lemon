package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun OnBoardingScreen() {
    var firstname by remember{
        mutableStateOf("")
    }
    var lastname by remember{
        mutableStateOf("")
    }
    var email by remember{
        mutableStateOf("")
    }
    val karla = FontFamily(
        Font(R.font.karla),
    )
    val markazi = FontFamily(
        Font(R.font.markazi),
    )
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "header",modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 25.dp)
            .height(50.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF495E57))
            .height(90.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Let's get to know you",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 25.sp, color = Color.White,
                    fontFamily = karla)
            )

        }
        Text(
            text = "Personal information",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold,
                fontFamily = karla),
        modifier=Modifier.padding(top=30.dp,bottom=30.dp,start=10.dp))
        Text(
            text = "First name",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 10.sp, color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontFamily = karla),
            modifier=Modifier.padding(start=10.dp,bottom=5.dp)
        )
        TextField(value = firstname, onValueChange ={it:String-> firstname=it},modifier= Modifier
            .padding(start = 10.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(10.dp)))
        Text(
            text = "Last name",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 10.sp, color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontFamily = karla),
            modifier=Modifier.padding(start=10.dp,bottom=5.dp)
        )
        TextField(value = lastname, onValueChange ={it:String-> lastname=it},modifier= Modifier
            .padding(start = 10.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(10.dp)))
        Text(
            text = "email",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 10.sp, color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontFamily = karla),
            modifier=Modifier.padding(start=10.dp,bottom=5.dp)
        )
        TextField(value = email, onValueChange ={it:String-> email=it},modifier= Modifier
            .padding(start = 10.dp, bottom = 50.dp)
            .clip(RoundedCornerShape(10.dp)))
        Button(onClick = { /*TODO*/ },shape= RoundedCornerShape(10.dp),modifier=Modifier.padding(start=10.dp,end=10.dp).fillMaxWidth(),colors=ButtonDefaults.buttonColors(containerColor=Color(0xFFF4CE14))) {
            Text(text="Register",style = TextStyle( color = Color.Black,
                fontFamily = karla))


        }



    }
}
@Preview(showBackground=true)
@Composable
fun OnBoardingScreenPreview(){
    OnBoardingScreen()
}
