package com.godston.vfapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godston.vfapplication.model.Country
import com.godston.vfapplication.repository.CountryRepository
import com.godston.vfapplication.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {
    private val _fetchCountry = MutableLiveData<DataStatus<List<Country>>>()
    val fetchCountry: LiveData<DataStatus<List<Country>>>
        get() = _fetchCountry

    fun getAllCountries() = viewModelScope.launch {
        countryRepository.getAllCountries().collect {
            _fetchCountry.value = it
        }
    }
}
