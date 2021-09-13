package com.lebogang.drivencompose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lebogang.drivencompose.models.ContentModel
import com.lebogang.drivencompose.ui.theme.DrivenComposeTheme
import com.lebogang.drivencompose.ui.theme.randomColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrivenComposeTheme(darkTheme = true) {

            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreview() {
    DrivenComposeTheme {
        Main()
    }
}

@Composable
fun Main(){
    var bottomNavigationIndex by rememberSaveable{ mutableStateOf(2)}
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "App Name")},
                navigationIcon = {
                    Icon(Icons.Rounded.Face, null)
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 0.dp
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.primary,
                elevation = 0.dp,
            ) {
                BottomNavigationItem(
                    icon = {
                        if (bottomNavigationIndex == 0) Icon(Icons.Rounded.ShoppingCart, contentDescription = null)
                        else Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
                       },
                    label = { Text("Cart") },
                    selected = bottomNavigationIndex == 0,
                    onClick = {
                        bottomNavigationIndex = 0
                    }
                )
                BottomNavigationItem(
                    icon = {
                        if (bottomNavigationIndex == 1) Icon(Icons.Rounded.Bookmark, contentDescription = null)
                        else Icon(Icons.Outlined.Bookmark, contentDescription = null)
                       },
                    label = { Text("Text") },
                    selected = bottomNavigationIndex == 1,
                    onClick = {
                        bottomNavigationIndex = 1
                    }
                )
                BottomNavigationItem(
                    icon = {
                        if (bottomNavigationIndex == 2) Icon(Icons.Rounded.Home, contentDescription = null)
                        else Icon(Icons.Outlined.Home, contentDescription = null)
                       },
                    label = { Text("Home") },
                    selected = bottomNavigationIndex == 2,
                    onClick = {
                        bottomNavigationIndex = 2
                    }
                )
                BottomNavigationItem(
                    icon = {
                        if (bottomNavigationIndex == 3) Icon(Icons.Rounded.Person, contentDescription = null)
                        else Icon(Icons.Outlined.Person, contentDescription = null)
                       },
                    label = { Text("Profile") },
                    selected = bottomNavigationIndex == 3,
                    onClick = {
                        bottomNavigationIndex = 3
                    }
                )
                BottomNavigationItem(
                    icon = {
                        if (bottomNavigationIndex == 4) Icon(Icons.Rounded.Settings, contentDescription = null)
                        else Icon(Icons.Outlined.Settings, contentDescription = null)
                       },
                    label = { Text("Settings") },
                    selected = bottomNavigationIndex == 4,
                    onClick = {
                        bottomNavigationIndex = 4
                    }
                )
            }
        }
    ) {
        Content()
    }
}

@Composable
fun Content(){
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                //go to search
            }.background(color = Color.LightGray.copy(alpha = 0.6f), shape = RoundedCornerShape(8.dp))){
            Icon(Icons.Rounded.Search,"Logo",
                modifier = Modifier
                    .padding(8.dp))
            Text(
                text = "Search for product", style = MaterialTheme.typography.caption
            )
        }
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)){
            item {
                Image(Icons.Rounded.Image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Recommended")
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Forward,contentDescription = "")
                    }
                }
            }
            item {
                RecommendedList(urls = listOf("Item"))
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Popular")
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Forward,contentDescription = "")
                    }
                }
            }
            item {
                PopularList(urls = listOf("Items"))
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "More")
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Forward,contentDescription = "")
                    }
                }
            }

            item{
                MoreList(urls = listOf("Items"))
            }
        }
    }
}

// Need to find out if I need to move all this to MainActivity
// Still need to able to navigate between activities

/**
 * Display short list of products
 * - could pass a list of actual products but we'll see as time goes by
 * @param urls: list of product urls
 * */
@Composable
fun RecommendedList(urls:List<String>){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(urls.size){ index->
            RecommendedItem(url = urls[index])
        }
    }
}

/**
 * Display product image
 * @param url: Product Image
 * */
@Composable
fun RecommendedItem(url:String){
    Card(
        modifier = Modifier.size(128.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ){
        Image(Icons.Rounded.Image,"")
    }
}

/**
 * Display short list of products
 * - could pass a list of actual products but we'll see as time goes by
 * @param urls: list of product urls
 * */
@Composable
fun PopularList(urls:List<String>){
    LazyRow(
        modifier = Modifier.padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(urls.size){ index->
            PopularItem(url = urls[index])
        }
    }
}

/**
 * Display product image
 * @param url: Product Image
 * */
@Composable
fun PopularItem(url:String){
    Card(
        modifier = Modifier.size(96.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ){
        Image(Icons.Rounded.Image,"")
    }
}

/**
 * Display short list of products
 * - could pass a list of actual products but we'll see as time goes by
 * @param urls: list of products
 * */
@Composable
fun MoreList(urls: List<String>){
    LazyRow(
        modifier = Modifier.padding(horizontal = 8.dp),){
        items(urls.size){ index->
            MoreItem(url = urls[index])
        }
    }
}

/**
 * Display product image
 * @param url: Product
 * */
@Composable
fun MoreItem(url:String){
    Card(
        modifier = Modifier.size(96.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ){
        Image(Icons.Rounded.Image,"")
    }
}
