package com.example.listycity

import androidx.compose.runtime.mutableStateListOf

class CityList {
    private val _cities = mutableStateListOf("Edmonton", "Vancouver", "Calgary")

    private val _citytoremove = mutableStateListOf("")

    //access the list of cities in a read-only way
    val cities: List<String>
        get() = _cities

    val citytoremove: List<String>
        get() = _citytoremove

    fun addCity(city: String){
        _cities.add(city)
    }

    fun selectToRemove (city: String) {
        _citytoremove.removeAt(0)
        _citytoremove.add(city)
    }

    fun removeCity(city: String){
        _cities.remove(_citytoremove[0])
        _citytoremove.removeAt(0)
        _citytoremove.add("")
    }

}