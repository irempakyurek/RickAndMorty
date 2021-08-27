package com.irempakyurek.rickandmorty.retrofit

import com.irempakyurek.jewellux.retrofit.CharactersDaoInterface

class ApiUtils {

    companion object{

        val BASE_URL = "https://rickandmortyapi.com/api/"

        fun getCharactersDaoInterface(): CharactersDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(CharactersDaoInterface::class.java)
        }
    }
}



