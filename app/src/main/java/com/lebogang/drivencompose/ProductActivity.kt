package com.lebogang.drivencompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lebogang.drivencompose.ui.theme.DrivenComposeTheme

class ProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrivenComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    DrivenComposeTheme {

        Details(TempProductEnum.SHOES)
    }
}

/**
 * Compose based on product type
 * - Shoes
 * - Other Stuff
 * - Receive Product via Intent extras? we'll see lol //don't remember the name
 * - At least there's no need for a million activities to accommodate
 * - Need to learn how to animate!!!
 * */

@Composable
fun Details(temp:TempProductEnum){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        // go back
                    }) {
                        Icon(Icons.Rounded.ArrowBack, null)
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 0.dp
            )
        },bottomBar = {
            TextButton(onClick = {

            }, modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 6.dp,topEnd = 6.dp))){
                Text(text = "Add to Cart")
            }
        }
    ){
        when(temp){
            TempProductEnum.SHOES -> Shoes()
            TempProductEnum.OTHER -> TODO()
            TempProductEnum.SHIT -> TODO()
        }
    }
}

/**
 * Add shoe in the parameter
 * */
@Composable
fun Shoes(){
    LazyColumn (modifier = Modifier.padding(horizontal = 8.dp)){
        item {
            Box (modifier = Modifier
                .fillMaxWidth()
                .height(196.dp)){
                Image(Icons.Rounded.Image, null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight() )
                Text(text = "R120",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
                        )
                        .padding(8.dp))
            }
        }
        item {
            Text(text = "Shoe Name", style = MaterialTheme.typography.h6)
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Type or something", style = MaterialTheme.typography.subtitle1)
                Row {
                   Text(text = "5",
                       color= MaterialTheme.colors.primary ,
                       fontWeight = FontWeight.Bold,
                       style = MaterialTheme.typography.h6)
                   Icon(Icons.Rounded.Star, contentDescription = null,
                       tint = MaterialTheme.colors.primary )
                }
            }
        }
        item {
            Column(modifier = Modifier.fillMaxWidth(),) {
                Text(text = "In Stock?",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(vertical = 8.dp)
                    )
                Text(text = "Description")
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Size")
                IconButton(onClick = {
                    //show dialog or something
                }) {
                    Row(verticalAlignment = Alignment.CenterVertically,){
                        Text(text = "Size")
                        Icon(Icons.Rounded.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
        item {
            ShoeSizes(sizes = listOf(1f,2f,5f,5f,6f,5.4f,7f,7.7f,5f,7f,3f,4f))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Related")
                IconButton(onClick = {
                    //go to recommended
                }) {
                    Icon(Icons.Rounded.Forward, contentDescription = null)
                }
            }
        }
        item{
            RelatedShoes(tempShoes = listOf("Shoe"))
        }
    }
}

/**
 * Show list of sizes
 * @param sizes: sizes
 * */
@Composable
fun ShoeSizes(sizes:List<Float>){
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(sizes.size){ index ->
            ShoeSize(size = sizes[index])
        }
    }
}

/**
 * Show show
 * @param size: ?
 * */
@Composable
fun ShoeSize(size:Float){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = "$size",modifier = Modifier.padding(14.dp))
    }
}

/**
 * Pass related shoes object
 * */
@Composable
fun RelatedShoes(tempShoes:List<String>){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(tempShoes.size) { index->
            RelatedShoe(temp = tempShoes[index])
        }
    }
}

/**
 * Pass related shoe in the parameters
 * */
@Composable
fun RelatedShoe(temp:String){
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp
    ){
        Image(Icons.Rounded.Image, contentDescription = null, modifier = Modifier.size(96.dp))
    }
}

@Composable
fun OtherShit(){
    
}
