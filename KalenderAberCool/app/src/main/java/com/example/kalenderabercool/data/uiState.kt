package com.example.kalenderabercool.data

import java.time.LocalDate


data class uiState (

        val currentDate: LocalDate = LocalDate.now(),
        val changedDate: LocalDate = currentDate,
        val entryChangedDate: LocalDate = currentDate,
        val selectedEntryDateVon: LocalDate? = null,
        val selectedEntryDateBis: LocalDate? = null
    
)