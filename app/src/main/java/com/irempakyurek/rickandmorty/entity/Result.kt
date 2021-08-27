package com.irempakyurek.rickandmorty.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Result(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("species")
    var species: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("origin")
    var origin: Origin,
    @SerializedName("location")
    var location: Location,
    @SerializedName("image")
    var image: String,
    @SerializedName("episode")
    var episode: List<String>,
    @SerializedName("url")
    var url: String,
    @SerializedName("created")
    var created: String
) : Serializable{
}