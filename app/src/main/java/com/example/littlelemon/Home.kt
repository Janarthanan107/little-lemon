package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var v by remember { mutableStateOf(" ") }


    val markazi = FontFamily(
        Font(R.font.markazi)
    )
    val karla = FontFamily(
        Font(R.font.karla)
    )

    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "header",
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 25.dp, bottom = 25.dp, start = 25.dp)
                    .height(50.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 25.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .height(50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier.clickable(onClick = { navController.navigate("Profile") })
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Little Lemon",
                fontSize = 50.sp,
                fontFamily = markazi,
                color = Color(0xFFF4CE14)
            )
            Text(
                text = "Chicago",
                fontFamily = markazi,
                fontSize = 34.sp,
                color = Color(0xFFEDEFEE)
            )
            Row(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .height(140.dp)
            ) {
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    color = Color(0xFFEDEFEE),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 28.dp)
                        .fillMaxWidth(0.6f)
                )
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = "Upper Panel Image",
                        modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    )
                }
            }
            TextField(
                value = v,
                onValueChange = { v = it },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                placeholder = { Text("Enter search phrase") },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(10.dp)
        ) {
            LowerPanel(v)
        }
    }
}

@Composable
fun LowerPanel(searchText: String) {
    val context= LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)}
    var searchButton=getData("s")
    val karla = FontFamily(
        Font(R.font.karla)
    )

    Column {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontWeight = ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 10.dp)
        )
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category)
            }
        }
        Divider(
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
        LazyColumn {
            items(Dishes.filter { it.name.contains(searchText, ignoreCase = true)&&it.category==searchButton }) { dish ->
                MenuDish(dish)
            }
        }
    }
}

@Composable
fun MenuCategory(category: String) {
    val karla = FontFamily(
        Font(R.font.karla)
    )
    val context= LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
    fun saveData(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    Button(
        onClick = { saveData("s",category )},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDEFEE)),
        shape = RoundedCornerShape(40),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
    ) {
        Text(
            fontWeight = ExtraBold,
            color = Color(0xFF495E57),
            text = category
        )
    }
}

@Composable
fun MenuDish(dish: Dish) {
    val karla = FontFamily(
        Font(R.font.karla)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column {
            Text(
                text = dish.name,
                fontSize = 18.sp,
                fontWeight = ExtraBold
            )
            Text(
                text = dish.description,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(.75f)
            )
            Text(
                text = dish.price,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            painter = painterResource(id = dish.image),
            contentDescription = "",
        )
    }

    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}

val Categories = listOf(
    "Starters",
    "Mains",
    "Desserts",
    "Drinks"
)

data class Dish(
    val name: String,
    val price: String,
    val description: String,
    val image: Int,
    val category:String
)

val Dishes = listOf(
    Dish(
        "Greek Salad",
        "$12.99",
        "The famous Greek salad of crispy lettuce, peppers, olives, and our Chicago...",
        R.drawable.greeksalad,"Starters"
    ),
    Dish(
        "Bruschetta ",
        "$7.99",
        "Our Bruschetta is made from grilled bread that has been smeared with garlic...",
        R.drawable.bruschetta,"Starters"
    ),
    Dish(
        "Grilled Fish",
        "$20.00",
        "Barbequed catch of the day with red onion, crisp capers, chive creme fraiche...",
        R.drawable.grilledfish,"Mains"
    ),
    Dish(
        "Pasta ",
        "$18.99",
        "Penne with fried aubergines, tomato sauce, fresh chili, basil & salted...",
        R.drawable.pasta,"Mains"
    ),
    Dish(
        "Lemon Dessert",
        "$6.99",
        "This comes straight from grandma's recipe book, every last ingredient has...",
        R.drawable.lemondessert,"Desserts"
    )
)
