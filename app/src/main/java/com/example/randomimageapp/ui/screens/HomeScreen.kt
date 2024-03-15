package com.example.randomimageapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomimageapp.R
import com.example.randomimageapp.viewmodel.NLUiState


@Composable
fun HomeScreen(
    nlUiState: NLUiState,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    when(nlUiState){
        is NLUiState.Loading -> LoadingScreen(modifier=modifier.fillMaxSize())
        is NLUiState.Success -> ResultScreen(photos = nlUiState.photos,
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

@Preview
@Composable
fun HomeScreenPreview(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        //HomeScreen()
    }

}
