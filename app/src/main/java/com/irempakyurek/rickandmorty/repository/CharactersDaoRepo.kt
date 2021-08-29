package com.irempakyurek.rickandmorty.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.irempakyurek.jewellux.retrofit.CharactersDaoInterface
import com.irempakyurek.rickandmorty.MySharedPreference
import com.irempakyurek.rickandmorty.entity.CharacterModel
import com.irempakyurek.rickandmorty.entity.Episode
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.retrofit.ApiUtils
import com.irempakyurek.rickandmorty.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersDaoRepo {
    private val charactersList: MutableLiveData<List<Result>>
    private val cdaoi: CharactersDaoInterface
    private var lastSeenEpisodeName: MutableLiveData<String>
    private var lastSeenEpisodeAirDate: MutableLiveData<String>

    init {
        charactersList = MutableLiveData()
        cdaoi = ApiUtils.getCharactersDaoInterface()
        lastSeenEpisodeName = MutableLiveData<String>("")
        lastSeenEpisodeAirDate = MutableLiveData<String>("")
    }

    fun bringCharacter(): MutableLiveData<List<Result>> {
        return charactersList
    }

    fun bringLastSeenEpisodeName(): MutableLiveData<String> {
        return lastSeenEpisodeName
    }

    fun bringLastSeenEpisodeAirDate(): MutableLiveData<String> {
        return lastSeenEpisodeAirDate
    }

    fun getAllCharacters(current_page: Int) {
        cdaoi.getCharacters(current_page).enqueue(object : Callback<CharacterModel> {
            override fun onResponse(
                call: Call<CharacterModel>?,
                response: Response<CharacterModel>
            ) {
                val list: List<Result> = response.body()!!.result
                charactersList.value = list
            }

            override fun onFailure(call: Call<CharacterModel>?, t: Throwable?) {
            }
        })
    }

    fun getLastSeenEpisode(lastSeenEpisodeNum: Int) {
        cdaoi.getEpisodes(lastSeenEpisodeNum)
            .enqueue(object : Callback<Episode> {
                override fun onResponse(call: Call<Episode>?, response: Response<Episode>) {
                    lastSeenEpisodeName.value = response.body()!!.name
                    lastSeenEpisodeAirDate.value = response.body()!!.airDate
                }

                override fun onFailure(call: Call<Episode>?, t: Throwable?) {
                }

            })
    }

    fun addItem(context: Context, character: Result) {
        val builder = GsonBuilder()
        val gson = builder.create()
        val sharedPreference = MySharedPreference.MySharedPreference(context)
        val mFavoritesList = sharedPreference.retrieveFavorites()
        mFavoritesList.add(character)
        val addNewItem = gson.toJson(mFavoritesList)
        sharedPreference.saveFavoritesMarkers(addNewItem)
    }

    fun removeItem(context: Context, character: Result) {
        val builder = GsonBuilder()
        val gson = builder.create()
        val sharedPreference = MySharedPreference.MySharedPreference(context)
        val mFavoritesList = sharedPreference.retrieveFavorites()
        for (i in mFavoritesList.indices) {
            if (mFavoritesList.get(i).id.equals(character.id)) {
                mFavoritesList.removeAt(i)
                val removeItem = gson.toJson(mFavoritesList)
                sharedPreference.saveFavoritesMarkers(removeItem)
                break
            }
        }
    }
}