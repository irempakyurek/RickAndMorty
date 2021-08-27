package com.irempakyurek.rickandmorty.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.irempakyurek.rickandmorty.Info

data class CharacterModel(
    @SerializedName("info")
    @Expose
    var info: Info,
    @SerializedName("results")
    @Expose
    var result: List<Result>
) {
}