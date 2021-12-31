package com.ernad.tvmaze.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernad.tvmaze.network.MazeApi
import com.ernad.tvmaze.ui.details.movie.MovieObject
import com.ernad.tvmaze.ui.list.MazeApiStatus
import kotlinx.coroutines.launch


enum class MazeApiStatus { LOADING, ERROR, DONE }

class DetailsViewModel : ViewModel() {

    private val _status = MutableLiveData<MazeApiStatus>()

    val status: LiveData<MazeApiStatus> = _status

    private val _movieItem = MutableLiveData<MovieObject>()

    val movieItem: LiveData<MovieObject> = _movieItem


     fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _status.value = MazeApiStatus.LOADING
            try {

                _movieItem.value = MazeApi.retrofitService.getMovieDetails(id)

                _status.value = MazeApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MazeApiStatus.ERROR
                _movieItem.value = null
            }

        }
    }
}