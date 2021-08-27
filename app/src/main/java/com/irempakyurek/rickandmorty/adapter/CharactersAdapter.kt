package com.irempakyurek.rickandmorty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.irempakyurek.rickandmorty.MySharedPreference
import com.irempakyurek.rickandmorty.R
import com.irempakyurek.rickandmorty.databinding.CharacterGridBinding
import com.irempakyurek.rickandmorty.databinding.CharacterListBinding
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.fragment.CharactersFragmentDirections
import com.irempakyurek.rickandmorty.utils.Constants
import com.irempakyurek.rickandmorty.viewmodel.CharactersFragmentViewModel
import com.squareup.picasso.Picasso
import java.util.*

class CharactersAdapter(
    var mContext: Context,
    var charactersList: List<Result>
)

    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mFavoritesList : List<Result> = listOf()
    var itemExists : Boolean = false

    inner class ListCharactersAdapterViewHolder(characterListDesignBinding: CharacterListBinding) :
        RecyclerView.ViewHolder(characterListDesignBinding.root) {

        var characterListDesignBinding: CharacterListBinding

        init {
            this.characterListDesignBinding = characterListDesignBinding
        }
    }

    inner class GridCharactersAdapterViewHolder(characterGridDesignBinding: CharacterGridBinding) :
        RecyclerView.ViewHolder(characterGridDesignBinding.root) {

        var characterGridDesignBinding: CharacterGridBinding

        init {
            this.characterGridDesignBinding = characterGridDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return if (Constants.isGrid) {
            GridCharactersAdapterViewHolder(
                CharacterGridBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        } else {
            ListCharactersAdapterViewHolder(
                CharacterListBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = charactersList.get(position)
        val url = character.image

        loadFavoritesData()

        if (Constants.isGrid) {
            val view = (holder as GridCharactersAdapterViewHolder).characterGridDesignBinding
            view.characterObject = character
            Picasso.get().load(url).into(view.imgCharacterImage)

            view.cvCharacter.setOnClickListener {
                val transition = CharactersFragmentDirections
                    .actionCharactersFragmentToCharacterDetailsFragment(character,
                        checkAvailability(mFavoritesList, charactersList.get(position)))
                Navigation.findNavController(it).navigate(transition)
            }
            if (mFavoritesList.size != 0) {
                itemExists = checkAvailability(mFavoritesList, charactersList.get(position))
                if (itemExists) {
                    view.favoriteStar.setImageResource(R.drawable.ic_star)
                } else {
                    view.favoriteStar.setImageResource(R.drawable.ic_star_border)
                }
            }
        } else {
            val view = (holder as ListCharactersAdapterViewHolder).characterListDesignBinding
            view.characterObject = character
            Picasso.get().load(url).into(view.imgCharacterImage)

            view.cvCharacter.setOnClickListener {
                val transition = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(character,
                    checkAvailability(mFavoritesList, charactersList.get(position)))
                Navigation.findNavController(it).navigate(transition)
            }
            if (mFavoritesList.size != 0) {
                itemExists = checkAvailability(mFavoritesList, charactersList.get(position))
                if (itemExists) {
                    view.favoriteStar.setImageResource(R.drawable.ic_star)
                } else {
                    view.favoriteStar.setImageResource(R.drawable.ic_star_border)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    private fun checkAvailability(rickMortyList: List<Result>, rickMorty: Result): Boolean {
        for (p in rickMortyList) {
            if (p.id.equals(rickMorty.id)) {
                return true
            }
        }
        return false
    }
    private fun loadFavoritesData() {
        val sharedPreference = MySharedPreference.MySharedPreference(mContext)
        mFavoritesList = sharedPreference.retrieveFavorites()
        if (mFavoritesList == null) {
            mFavoritesList = ArrayList<Result>()
        }
    }
}