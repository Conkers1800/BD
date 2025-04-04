/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens
/**
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.model.ApiRate
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.DatabaseProvider
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

/**
 * UI state for the Home screen
 */
sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel (application: Application): AndroidViewModel(application) {
    private val exchangeRateDao = DatabaseProvider.getDatabase(application).exchangeRateDao()
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos()
                val timestamp = System.currentTimeMillis()
                val fecha= Date(timestamp)
                val formato=SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val fechaCompleta=formato.format(fecha)
                listResult.conversionRates.forEach { (targetCode, rate) ->
                    exchangeRateDao.insert(
                        ApiRate(
                        baseCode = listResult.baseCode,
                        targetCode = targetCode,
                        rate = rate,
                        timestamp = timestamp
                    )
                    )
                }
                MarsUiState.Success(
                    "Success: ${listResult.baseCode}, " +
                            "Rates ${listResult.conversionRates.getValue(listResult.baseCode)}, " +
                            " Actualizacion anterior ${listResult.timeLastUpdateUtc}, " +
                            " Siguiente actualizacion ${listResult.timeNextUpdateUtc}"
                )
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
}
*/