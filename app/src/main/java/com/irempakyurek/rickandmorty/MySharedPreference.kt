package com.irempakyurek.rickandmorty

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.utils.Constants
import java.util.ArrayList

class MySharedPreference {
    class MySharedPreference(context: Context) {
        val context: Context
        val prefs: SharedPreferences

        init {
            this.context = context
            prefs = context.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        }

        fun saveFavoritesMarkers(item: String?) {
            prefs.edit()
                .putString(Constants.FAVORITE_ID, item)
                .apply()
        }

        fun retrieveFavorites(): ArrayList<Result> {
            val emptyList = Gson().toJson(ArrayList<Result>())
            return Gson().fromJson(
                prefs.getString(Constants.FAVORITE_ID, emptyList),
                object : TypeToken<ArrayList<Result>>() {
                }.type
            )
        }

        fun splashScreenVisibled(isVisible: Boolean) {
            prefs.edit()
                .putBoolean(Constants.SPLASH_SCREEN_VISIBLE, isVisible)
                .apply()
        }
    }

}