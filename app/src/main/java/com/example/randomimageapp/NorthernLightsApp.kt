package com.example.randomimageapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomimageapp.ui.screens.HomeScreen
import com.example.randomimageapp.viewmodel.NLUiState
import com.example.randomimageapp.viewmodel.NLViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NLApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { NLTopBar(scrollBehavior = scrollBehavior)}
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()) {
            val nlViewModel: NLViewModel = viewModel()
            HomeScreen(nlUiState = nlViewModel.nlUiState, contentPadding = it)
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NLTopBar(scrollBehavior: TopAppBarScrollBehavior,modifier:Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )

}