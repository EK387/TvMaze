package com.ernad.tvmaze.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ernad.tvmaze.network.MazeApi
import com.ernad.tvmaze.ui.list.movieListObject.MazeObject
import kotlinx.coroutines.launch

enum class MazeApiStatus { LOADING, ERROR, DONE }

class ListViewModel : ViewModel() {

    private val _status = MutableLiveData<MazeApiStatus>()

    val status: LiveData<MazeApiStatus> = _status

    private val _mazeItems = MutableLiveData<List<MazeObject>>()

    val mazeItems: LiveData<List<MazeObject>> = _mazeItems


    private val _openTaskEvent = MutableLiveData<Int>()
    val openTaskEvent: LiveData<Int> = _openTaskEvent


    init {
        getMazeItems()
    }

    private fun getMazeItems() {
        viewModelScope.launch {
            _status.value = MazeApiStatus.LOADING
            try {
                _mazeItems.value = MazeApi.retrofitService.getItems("golden girls")
                _status.value = MazeApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MazeApiStatus.ERROR
                _mazeItems.value = listOf()
            }

        }
    }



    fun openMovieDetails(movieId: Int) {
        _openTaskEvent.value = movieId

    }


}