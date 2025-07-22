package com.example.rickandmortyworkmate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.entities.Details
import com.example.rickandmortyworkmate.R
import com.example.rickandmortyworkmate.ui.theme.myGreen

@Composable
fun DetailsScreen(details: Details?) {
    Column(modifier = Modifier.padding(top = 20.dp, start = 5.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        ImageWithTextInMiddle(details)
    }
}

@Composable
fun Item(field: String, fieldValue: String){
    Row(modifier = Modifier.padding(5.dp, 3.dp).background(myGreen))
    {
        Text(text = field, fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold)
        Text(text = fieldValue, fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ImageWithTextInMiddle(details: Details?) {
    Card {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(details?.image)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_book_96),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp))
            {
                Item("Name: ", details?.name.toString())
                Item("Gender: ", details?.gender.toString())
                Item("Status: ", details?.status.toString())
                Item("Species: ", details?.species.toString())
                Item("Location: ", details?.location.toString())
            }
        }
    }
}