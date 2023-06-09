package com.sirdev.wisataappfinal.ui.screen.aboutpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirdev.wisataappfinal.R
import com.sirdev.wisataappfinal.ui.theme.WisataJetTheme

@Composable
fun AboutPageScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.nasir),
            contentDescription = "Photo Ahmad Nasir",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(32.dp)
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = R.string.nama_about),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        Text(
            text = stringResource(id = R.string.email_about),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AboutPreview () {
    WisataJetTheme {
        AboutPageScreen()
    }
}