package com.example.uebersetzer.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uebersetzer.data.allWords
import com.example.uebersetzer.data.wordsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UebersetzungsViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(UebersetzungsUIState())
    val uiState: StateFlow<UebersetzungsUIState> =_uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private lateinit var currentWord: allWords


    init {
        updateStateUi(0)
    }



    fun updateUserTranslation(newTranslation: String) {
        userGuess = newTranslation
    }


    fun checkTranslation() {
        val updatedScore: Int
        if(userGuess.equals(currentWord.germanTranslation, ignoreCase = true)) {

            updatedScore = _uiState.value.score.plus(1)


        } else {
            updatedScore = 0
        }
        //update State
        updateStateUi(updatedScore)
    }

    private fun updateStateUi(updatedScore: Int) {

        _uiState.update {currentState ->currentState.copy(
            currentTranslation = pickWord().englishWord,
            score = updatedScore
        )

        }

    }

    fun pickWord():allWords {

        currentWord = wordsList.random()
        return currentWord

    }

}