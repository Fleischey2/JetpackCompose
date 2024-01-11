package com.example.uebersetzer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uebersetzer.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UebersetzungsScreen(modifier: Modifier = Modifier,
                        uebersetzungsViewModel: UebersetzungsViewModel = viewModel()

) {

    val uebersetzungsUiState by uebersetzungsViewModel.uiState.collectAsState()


    Card(modifier = modifier) {
        Column(modifier = Modifier) {

            Text(text = stringResource(R.string.score_d, uebersetzungsUiState.score))

            Text(text = stringResource(R.string.uebersetze_englisch_zu_deutsch))

            Text(text = uebersetzungsUiState.currentTranslation)

            OutlinedTextField(value = uebersetzungsViewModel.userGuess,
                onValueChange = { uebersetzungsViewModel.updateUserTranslation(it) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { uebersetzungsViewModel.checkTranslation() }
                    ))



        }
    }

}

@Preview(showBackground = true)
@Composable
fun DisplayScreen() {
    
    UebersetzungsScreen()
    
}

