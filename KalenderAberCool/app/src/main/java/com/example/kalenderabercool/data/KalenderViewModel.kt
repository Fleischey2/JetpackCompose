package com.example.kalenderabercool.data

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONArray
import java.time.LocalDate
import java.util.Properties

class KalenderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(uiState())
    val uiState: StateFlow<uiState> = _uiState.asStateFlow()

    val connectionProps = Properties()
    fun establishDatabase() {
        connectionProps.put("user", "admin")
        connectionProps.put("password", "admin")
    }




    fun getWeeks(): ArrayList<Int> {


        val currentDisplayedDate: LocalDate = uiState.value.changedDate
        var tempDate: LocalDate

        val day: Int = currentDisplayedDate.dayOfMonth
        val month: Int = currentDisplayedDate.monthValue
        val year: Int = currentDisplayedDate.year

        tempDate = LocalDate.of(year, month, 1)

        val firstDay: Int = tempDate.dayOfWeek.value


        var monthWeeks: ArrayList<Int> = ArrayList<Int>()

        val daysOfMonth: Int = Months.values()[month - 1].getDays()
        tempDate = LocalDate.of(year, month, daysOfMonth)

        val lastDay: Int = tempDate.dayOfWeek.value
        var index: Int = 0
        var days: Int = 1

        while(index < daysOfMonth + (7 - lastDay) + (firstDay - 1)) {
            if(index >= firstDay - 1 && index <= daysOfMonth - 1 + (firstDay - 1) ) {
                monthWeeks.add(days++)
            } else {
                monthWeeks.add(0)
            }
            index += 1
        }

        Log.i("Weeks",monthWeeks.toString())

        return monthWeeks




    }

    fun getEntryWeeks(): ArrayList<Int> {


        val currentDisplayedDate: LocalDate = uiState.value.entryChangedDate
        var tempDate: LocalDate

        val day: Int = currentDisplayedDate.dayOfMonth
        val month: Int = currentDisplayedDate.monthValue
        val year: Int = currentDisplayedDate.year

        tempDate = LocalDate.of(year, month, 1)

        val firstDay: Int = tempDate.dayOfWeek.value


        var monthWeeks: ArrayList<Int> = ArrayList<Int>()

        val daysOfMonth: Int = Months.values()[month - 1].getDays()
        tempDate = LocalDate.of(year, month, daysOfMonth)

        val lastDay: Int = tempDate.dayOfWeek.value
        var index: Int = 0
        var days: Int = 1

        while(index < daysOfMonth + (7 - lastDay) + (firstDay - 1)) {
            if(index >= firstDay - 1 && index <= daysOfMonth - 1 + (firstDay - 1) ) {
                monthWeeks.add(days++)
            } else {
                monthWeeks.add(0)
            }
            index += 1
        }

        Log.i("Weeks",monthWeeks.toString())

        return monthWeeks




    }

    fun changeDate(forward: Boolean) {
        if (forward) {

            var month: Int = uiState.value.changedDate.monthValue - 1
            month = (month + 1) % 12

            var year: Int = uiState.value.changedDate.year

            if(month == 0) {
                year = year + 1
            }



            _uiState.update { currentState ->
                currentState.copy(
                    changedDate = LocalDate.of(year, month + 1, 1)
                )

            }
        } else {
            var month: Int = uiState.value.changedDate.monthValue - 1
            month = (month + 11) % 12

            var year: Int = uiState.value.changedDate.year

            if(month == 11) {
                year = year - 1
            }

            _uiState.update { currentState ->
                currentState.copy(
                    changedDate = LocalDate.of(year, month + 1, 1)
                )

            }
        }

        Log.i("LOGGING", uiState.value.changedDate.toString())

    }

    fun changeEntryDate(forward: Boolean) {
        if (forward) {

            var month: Int = uiState.value.entryChangedDate.monthValue - 1
            month = (month + 1) % 12

            var year: Int = uiState.value.entryChangedDate.year

            if(month == 0) {
                year = year + 1
            }



            _uiState.update { currentState ->
                currentState.copy(
                    entryChangedDate = LocalDate.of(year, month + 1, 1)
                )

            }
        } else {
            var month: Int = uiState.value.entryChangedDate.monthValue - 1
            month = (month + 11) % 12

            var year: Int = uiState.value.entryChangedDate.year

            if(month == 11) {
                year = year - 1
            }

            _uiState.update { currentState ->
                currentState.copy(
                    entryChangedDate = LocalDate.of(year, month + 1, 1)
                )

            }
        }

        Log.i("LOGGING", uiState.value.entryChangedDate.toString())

    }
    //Testet ob firstDate nach secondDate kommt
    fun isBehind(firstDate: LocalDate = uiState.value.currentDate, secondDate: LocalDate = uiState.value.changedDate): Boolean {

        val currentDisplayedDate: LocalDate = firstDate
        val changedDisplayedDate: LocalDate = secondDate

        if(isEqual(firstDate, secondDate)) {
            return false
        }

        return changedDisplayedDate.isBefore(currentDisplayedDate)

    }

    fun getEntryVonDay(): Int {

        return uiState.value.selectedEntryDateVon?.dayOfMonth?:0

    }

    fun getEntryBisDay(): Int {

        return uiState.value.selectedEntryDateBis?.dayOfMonth?:31

    }

    fun resetEntryChangedDate() {

        _uiState.update { currentState ->
            currentState.copy(
                entryChangedDate = uiState.value.changedDate
            )

        }

    }

    fun resetEntry() {

        _uiState.update { currentState ->
            currentState.copy(
                selectedEntryDateVon = null
            )

        }

        _uiState.update { currentState ->
            currentState.copy(
                selectedEntryDateBis = null
            )

        }

    }



    fun getChangedDate(): LocalDate {
        return uiState.value.changedDate
    }

    fun getCurrentDate(): LocalDate {
        return uiState.value.currentDate
    }

    fun getEntryVonDate(): LocalDate? {
        return uiState.value.selectedEntryDateVon
    }

    fun getEntryBisDate(): LocalDate? {
        return uiState.value.selectedEntryDateBis
    }

    fun isEqual(firstDate: LocalDate = uiState.value.currentDate, secondDate: LocalDate = uiState.value.changedDate): Boolean {
        val currentDisplayedDate: LocalDate = firstDate
        val changedDisplayedDate: LocalDate = secondDate

        if(changedDisplayedDate.monthValue == currentDisplayedDate.monthValue &&
            changedDisplayedDate.year == currentDisplayedDate.year) {
            return true
        } else {
            return false
        }
    }

    fun getEntryDate(): LocalDate {
        return uiState.value.entryChangedDate
    }

    fun setSelectedEntryDateVon(date: LocalDate) {

        _uiState.update { currentState ->
            currentState.copy(
                selectedEntryDateVon = date
            )

        }
        Log.i("TESTING",date.toString())
    }

    fun setSelectedEntryDateBis(date: LocalDate) {

        _uiState.update { currentState ->
            currentState.copy(
                selectedEntryDateBis = date
            )

        }
        Log.i("TESTING",date.toString())
    }

    fun getKalenderTodaysDay(): Int {

        val currentDisplayedDate: LocalDate = uiState.value.currentDate


        return currentDisplayedDate.dayOfMonth
    }

    fun getKalenderDayInt(): Int {

        val currentDisplayedDate: LocalDate = uiState.value.changedDate


        return currentDisplayedDate.dayOfMonth
    }

    fun getKalenderMonth(): String {

        val currentDisplayedDate: LocalDate = uiState.value.changedDate


        return Months.values()[currentDisplayedDate.monthValue - 1].toString()
    }

    fun getEntryKalenderMonth(): String {

        val currentDisplayedDate: LocalDate = uiState.value.entryChangedDate


        return Months.values()[currentDisplayedDate.monthValue - 1].toString()
    }

    fun getKalenderMonthInt(): Int {

        val currentDisplayedDate: LocalDate = uiState.value.changedDate


        return currentDisplayedDate.monthValue
    }


    fun getKalenderYear(): String {

        val currentDisplayedDate: LocalDate = uiState.value.changedDate


        return currentDisplayedDate.year.toString()
    }

    fun getEntryKalenderYear(): String {

        val currentDisplayedDate: LocalDate = uiState.value.entryChangedDate


        return currentDisplayedDate.year.toString()
    }

    fun getKalenderInfo() : ArrayList<KalenderInfos> {

        var monthInfos: ArrayList<KalenderInfos> = ArrayList<KalenderInfos>()



        monthInfos.add(KalenderInfos(datum = "22.04.2023", zeitraum = "12:00-16:00", titel = "Zahnarzt"))


        return monthInfos

    }

    //Wird nach drücken von Speichern nach Eintrag aufgerufen und speichert Daten in uiState
    //ruft danach sendEintrag auf
    fun saveEintrag(eintragVon: String, eintragBis: String, titel: String) {
        //Sql aufruf



    }
    //nimmt daten von uiState und sendet aktuellen eintrag nach Datenbank
    fun sendEintrag() {

    }

    //Nimmt aktuelles Datum und wird demnächst in dem ausklappmenü aufgerufen oder uiState
    fun retrieveEintraege(): JSONArray? {


        return null
    }



}

enum class Months {
    JANUAR,
    FEBRUAR,
    MAERZ,
    APRIL,
    MAI,
    JUNI,
    JULI,
    AUGUST,
    SEPTEMBER,
    OKTOBER,
    NOVEMBER,
    DEZEMBER;

    fun getDays(schaltJahr: Boolean = false): Int {
        return when (this) {
            JANUAR, MAERZ, MAI, JULI, AUGUST, OKTOBER, DEZEMBER -> 31
            APRIL, JUNI, SEPTEMBER, NOVEMBER -> 30
            FEBRUAR -> if (schaltJahr) 29 else 28
        }
    }
}