package com.example.listycity

import android.R
import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity.ui.theme.ListyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cityRepository = CityList()
        //cityRepository.selectToRemove("loool")
        setContent {
            ListyCityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityListScreen(
                        cities = cityRepository.cities,
                        cityToRemove = cityRepository.citytoremove,
                        onAddCity = {cityRepository.addCity(it)},
                        onSelectToRemove = {cityRepository.selectToRemove(it)},
                        onRemoveCity = {cityRepository.removeCity(it)},
                        modifier = Modifier.padding(paddingValues =  innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CityListScreen(
    cities: List<String>,
    cityToRemove: List<String>,
    onAddCity: (String) -> Unit,
    onSelectToRemove: (String) -> Unit,
    onRemoveCity: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newCityName by remember { mutableStateOf("")}
    var clickedCity by remember { mutableStateOf("")}
    //var youAreSelected by remember { mutableStateOf(false)}
    var modifyList by remember { mutableStateOf(false)}
    var youAreSelected = false

    Column(modifier= modifier.fillMaxSize()){
        //onSelectToRemove("hehehe")
        if (modifyList == false){
            Row(modifier = Modifier.padding(all = 16.dp)) {
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    modifyList = true
                }
                ) { Text("Add City") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    // add remove city functionality here
                    onRemoveCity(cityToRemove[0])
                }
                ) { Text("Remove City") }
            }
        }
        else {
            Row(modifier = Modifier.padding(all = 16.dp)) {
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City Name") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (newCityName.isNotBlank()) {
                        onAddCity(newCityName)
                        newCityName = ""
                        modifyList = false
                    }
                }
                ) { Text("Confirm") }
            }
        }

        LazyColumn(modifier = modifier.fillMaxSize()){
            items(cities) {
                    city ->
                    CityRow(city = city, _cityToRemove = cityToRemove[0], _onSelectToRemove = onSelectToRemove)
            }
        }
    }

}
@Composable
fun CityRow(city: String, _cityToRemove: String, _onSelectToRemove: (String) -> Unit,){
    if(city != _cityToRemove) {
        Text(
            text = city,
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .clickable(onClick = { _onSelectToRemove(city) })
        )
    } else {
        Text(
            text = city,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .clickable(onClick = { _onSelectToRemove("") })
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListyCityTheme {
        Greeting("Android")
    }
}

