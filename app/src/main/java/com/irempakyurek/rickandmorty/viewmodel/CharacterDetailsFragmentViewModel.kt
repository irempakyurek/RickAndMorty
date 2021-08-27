package com.irempakyurek.rickandmorty.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.rickandmorty.entity.CharacterModel
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.repository.CharactersDaoRepo

class CharacterDetailsFragmentViewModel: ViewModel() {
    private val crepo = CharactersDaoRepo()
    var lastEpisodeName = MutableLiveData<String>()
    var lastEpisodeAirDate = MutableLiveData<String>()

    init {
        lastEpisodeName = crepo.bringLastSeenEpisodeName()
        lastEpisodeAirDate = crepo.bringLastSeenEpisodeAirDate()
    }

    fun getLastSeenEpisode(lastSeenEpisodeNum: Int) {
        crepo.getLastSeenEpisode(lastSeenEpisodeNum)
    }

    fun addItem(context: Context, character: Result) {
        crepo.addItem(context, character)
    }
    fun removeItem(context: Context, character: Result) {
        crepo.removeItem(context, character)
    }
}