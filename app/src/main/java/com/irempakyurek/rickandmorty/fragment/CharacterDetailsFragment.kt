package com.irempakyurek.rickandmorty.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.irempakyurek.rickandmorty.R
import com.irempakyurek.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.retrofit.ApiUtils
import com.irempakyurek.rickandmorty.utils.Utils
import com.irempakyurek.rickandmorty.viewmodel.CharacterDetailsFragmentViewModel

class CharacterDetailsFragment : Fragment() {

    private lateinit var view: FragmentCharacterDetailsBinding
    private lateinit var viewModel: CharacterDetailsFragmentViewModel
    private lateinit var lastSeenEpisodeNum: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_details, container, false)

        val bundle: CharacterDetailsFragmentArgs by navArgs()
        val character = bundle.characterObject
        var isFavorite = bundle.isFavorite

        view.character = character

        Utils.loadImage(view.imgCharacterImage, character.image)

        lastSeenEpisodeNum = character.episode.get(character.episode.size - 1)

        lastSeenEpisodeNum = lastSeenEpisodeNum.replace(ApiUtils.BASE_URL + "episode/", "")

        viewModel.getLastSeenEpisode(Integer.valueOf(lastSeenEpisodeNum))

        viewModel.lastEpisodeName.observe(viewLifecycleOwner, { name ->
            view.lastSeenEpisodeName = name
        })
        viewModel.lastEpisodeAirDate.observe(viewLifecycleOwner, { airDate ->
            view.lastSeenEpisodeAirDate = airDate
        })

        val imgBack: ImageView = requireActivity().findViewById(R.id.back)
        val imgGrid: ImageView = requireActivity().findViewById(R.id.grid_view)
        val imgList: ImageView = requireActivity().findViewById(R.id.list_view)
        imgBack.visibility = View.VISIBLE
        imgGrid.visibility = View.GONE
        imgList.visibility = View.GONE

        imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        if (isFavorite) {
            view.favoriteStar.setImageResource(R.drawable.ic_star)
        } else {
            view.favoriteStar.setImageResource(R.drawable.ic_star_border)
        }

        view.favoriteStar.setOnClickListener { v: View? ->
            if (isFavorite) {
                view.favoriteStar.setImageResource(R.drawable.ic_star_border)
                removeItemFromFavoriteList(requireContext(), character)
                isFavorite = false
            } else {
                view.favoriteStar.setImageResource(R.drawable.ic_star)
                addItemToFavoriteList(requireContext(), character)
                isFavorite = true
            }
        }

        when (character.status) {
            "Alive" -> view.statusLayout.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark, null))
            "Dead" -> view.statusLayout.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark, null))
            "unknown" -> view.statusLayout.setBackgroundColor(resources.getColor(android.R.color.holo_orange_dark, null))
        }
        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CharacterDetailsFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun addItemToFavoriteList(context: Context, character: Result) {
        viewModel.addItem(context, character)
    }

    fun removeItemFromFavoriteList(context: Context, character: Result) {
        viewModel.removeItem(context, character)
    }
}