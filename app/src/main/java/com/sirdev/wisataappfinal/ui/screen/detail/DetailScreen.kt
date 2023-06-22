package com.sirdev.wisataappfinal.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.sirdev.wisataappfinal.data.Repository
import com.sirdev.wisataappfinal.model.Wisata
import com.sirdev.wisataappfinal.ui.common.UiState
import com.sirdev.wisataappfinal.viewmodel.ViewModelFactory
import com.sirdev.wisataappfinal.R
import com.sirdev.wisataappfinal.ui.theme.WisataJetTheme

@Composable
fun DetailScreen(
    wisataId: Long,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Repository())),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getWisataId(wisataId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.wisata,
                    onBackClick = navigateBack,
                )
            }
            is UiState.Error -> {
                // Handle error state here
                val error = uiState.errorMessage
                // Display an error message or perform any other actions
                // based on the error state
            }
        }
    }
}



@Composable
fun DetailContent(
    data: Wisata,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box{
                AsyncImage(
                    model = data.photo,
                    contentDescription = stringResource(R.string.photo_wisata),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    modifier = modifier
                        .height(350.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = data.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = data.desc,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                )
            }

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailPreview() {
    WisataJetTheme {
        DetailContent(
            data = Wisata(
                2,
                "Raja Ampat",
                "Kepulauan Raja Ampat adalah gugusan kepulauan yang berlokasi di barat bagian Semenanjung Kepala Burung (Vogelkoop) Pulau Papua. Secara administrasi, gugusan ini berada di bawah Kabupaten Raja Ampat dan Kota Sorong, Provinsi Papua Barat Daya. Kepulauan ini sekarang menjadi tujuan para penyelam yang tertarik akan keindahan pemandangan bawah lautnya. Empat gugusan pulau yang menjadi anggotanya dinamakan menurut empat pulau terbesarnya, yaitu Pulau Waigeo, Pulau Misool, Pulau Salawati, dan Pulau Batanta.",
                "https://www.wisataidn.com/wp-content/uploads/2021/10/Tempat-Wisata-di-Raja-Ampat.jpeg"


            ),
            onBackClick = {},
        )
    }

}