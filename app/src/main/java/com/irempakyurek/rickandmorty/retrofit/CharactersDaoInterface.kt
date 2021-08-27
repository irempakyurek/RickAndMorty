package com.irempakyurek.jewellux.retrofit

import com.irempakyurek.rickandmorty.entity.CharacterModel
import com.irempakyurek.rickandmorty.entity.Episode
import retrofit2.Call
import retrofit2.http.*

interface CharactersDaoInterface {
    @GET("character")
    fun getCharacters(@Query("page") currentPage: Int): Call<CharacterModel>

    @GET("episode/{episodeNumber}")
    fun getEpisodes(@Path("episodeNumber") episodeId: Int): Call<Episode>

}