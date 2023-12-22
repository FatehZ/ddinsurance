package com.ktxdevelopment.ddinsurance.result

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModel : ViewModel() {

    val residenceSites: List<String> =
        listOf(
            "Binagadi District",
            "Garadagh District",
            "Khatai District",
            "Khazar District",
            "Nizami District",
            "Nasimi District",
            "Sabail District",
            "Sabunchu District",
            "Surakhani District",
            "Yasamal District",
            "Pirallahi District",
            "Shuvelan District"
        )

    private val hospitals = hashMapOf<String, Array<String>>().apply {
        for (i in residenceSites) {
            put(i, arrayOf("DIN Hospital", "MedEra Hospital", "Omur Clinics", "Medilux Hospital", "Saglam Aile Hospital"))
        }
    }



    private val _shownHospitals = mutableStateOf<Array<String>>(arrayOf())
    val shownHospitals: Array<String> get() = _shownHospitals.value

    fun onDistrictChanged(dist: String) {
        _shownHospitals.value = hospitals[dist] ?: arrayOf()
    }



    @Suppress("UNCHECKED_CAST")
    class ResultViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ResultViewModel::class.java)) { return ResultViewModel() as T }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
