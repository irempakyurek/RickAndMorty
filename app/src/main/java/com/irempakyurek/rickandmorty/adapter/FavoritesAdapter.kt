package com.irempakyurek.rickandmorty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.irempakyurek.rickandmorty.R
import com.irempakyurek.rickandmorty.databinding.CharacterGridBinding
import com.irempakyurek.rickandmorty.databinding.CharacterListBinding
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.fragment.FavoriteFragmentDirections
import com.irempakyurek.rickandmorty.utils.Constants
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    var mContext: Context,
    var mFavoritesList: List<Result>
)

    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        val character = mFavoritesList.get(position)
        val url = character.image

        if (Constants.isGrid) {
            val view = (holder as GridCharactersAdapterViewHolder).characterGridDesignBinding
            view.characterObject = character
            Picasso.get().load(url).into(view.imgCharacterImage)

            view.cvCharacter.setOnClickListener {
                val transition = FavoriteFragmentDirections
                    .actionFavoriteFragmentToCharacterDetailsFragment(character,true)
                Navigation.findNavController(it).navigate(transition)
            }
            view.favoriteStar.setImageResource(R.drawable.ic_star)
        } else {
            val view = (holder as ListCharactersAdapterViewHolder).characterListDesignBinding
            view.characterObject = character
            Picasso.get().load(url).into(view.imgCharacterImage)

            view.cvCharacter.setOnClickListener {
                val transition = FavoriteFragmentDirections.actionFavoriteFragmentToCharacterDetailsFragment(character,
                    true)
                Navigation.findNavController(it).navigate(transition)
            }
            view.favoriteStar.setImageResource(R.drawable.ic_star)
        }

    }

    override fun getItemCount(): Int {
        return mFavoritesList.size
    }
}