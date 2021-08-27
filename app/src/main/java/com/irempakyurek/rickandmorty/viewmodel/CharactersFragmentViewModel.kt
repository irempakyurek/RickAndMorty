package com.irempakyurek.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.repository.CharactersDaoRepo

class CharactersFragmentViewModel: ViewModel() {
    private val crepo = CharactersDaoRepo()
    var charactersList = MutableLiveData<List<Result>>()

    init {
        charactersList = crepo.bringCharacter()
    }

    fun uploadCharacters(current_page:Int) {
        crepo.getAllCharacters(current_page)
    }
}