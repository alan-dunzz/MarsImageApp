package com.example.randomimageapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.randomimageapp.R
import com.example.randomimageapp.model.MarsPhoto
//import com.example.randomimageapp.model.NLPhoto
import com.example.randomimageapp.viewmodel.NLUiState


@Composable
fun HomeScreen(
    nlUiState: NLUiState,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when(nlUiState){
        is NLUiState.Loading -> LoadingScreen(modifier=modifier.fillMaxSize())
        is NLUiState.Success -> PhotosGridScreen(photos = nlUiState.photos,
            modifier=modifier.fillMaxWidth())
        is NLUiState.Error -> ErrorScreen(modifier=modifier.fillMaxSize())
    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(modifier=modifier,
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.error)
            , contentDescription = "Error")
        Text(text="Error en la conexión")
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(modifier=modifier,
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.loading)
            , contentDescription = "Cargando")
    }
}


@Composable
fun ResultScreen(photos:String, modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Text(text = photos )
    }
}

@Composable
fun MarsPhotoCard(photo:MarsPhoto,modifier: Modifier){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(
                context = LocalContext.current
            ).data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.NL_image),
            modifier = modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            error = painterResource(id = R.drawable.error),
            placeholder = painterResource(id = R.drawable.loading),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PhotosGridScreen(
    photos:List<MarsPhoto>,
    modifier:Modifier=Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier=modifier.padding(horizontal=4.dp),
        contentPadding=contentPadding
    ){
        items(
            items=photos,
            key={
                    photo ->photo.id
            }
        ){
                photo ->
            MarsPhotoCard(photo = photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        //HomeScreen()
    }

}
