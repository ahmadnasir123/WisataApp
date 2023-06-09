package com.sirdev.wisataappfinal.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sirdev.wisataappfinal.R

@Composable
fun WisataListItem(
    id: Long,
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Card(Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.clickable{navigateToDetail(id)},
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Photo Wisata",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Column(modifier = modifier) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )

            }
        }
    }
}