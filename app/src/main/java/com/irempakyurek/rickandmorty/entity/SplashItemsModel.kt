package com.irempakyurek.rickandmorty.entity

import java.io.Serializable

data class SplashItemsModel(
    var itemId: Int, var itemDescription: String, var itemImageName: String
): Serializable{
}