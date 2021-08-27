package com.irempakyurek.rickandmorty.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
) : Serializable {
}