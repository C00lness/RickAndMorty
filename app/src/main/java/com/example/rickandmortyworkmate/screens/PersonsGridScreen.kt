package com.example.rickandmortyworkmate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.ui.Alignment
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.entities.Person
import com.example.rickandmortyworkmate.R
import com.example.rickandmortyworkmate.ui.theme.myBrown
import com.example.rickandmortyworkmate.ui.theme.myGreen

@Composable
fun PersonsGridScreen(
    pers: List<Person>,
    click: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(pers) { _, per ->
            PersonsCard(pers = per, click)
        }
    }
}

@Composable
fun PersonsCard(
    pers: Person,
    click: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp)
            .shadow(elevation = 8.dp)
            .clickable { click("/${pers.index}")}
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = myGreen)) {
            pers.name?.let {
                Text(
                    text = it.substringBefore(" "),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = myBrown,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                )
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween)
            {
                pers.gender?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = myBrown,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 4.dp)
                    )
                }
                pers.species?.let {
                    Text(
                        text = " | " + it.substring(0, 5),
                        fontSize = 12.sp,
                        color = myBrown,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 4.dp)
                    )
                }
                pers.status?.let {
                    Text(
                        text = " | " + it,
                        fontSize = 12.sp,
                        color = myBrown,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 4.dp)
                    )
                }
            }
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(pers.image)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_book_96),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop
            )
        }
    }
}