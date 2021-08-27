package com.irempakyurek.rickandmorty.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Episode(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("air_date")
    var airDate: String,
    @SerializedName("episode")
    var episode: String,
    @SerializedName("characters")
    var characters: List<String>,
    @SerializedName("url")
    var url: String,
    @SerializedName("created")
    var created: String
) : Serializable {
}