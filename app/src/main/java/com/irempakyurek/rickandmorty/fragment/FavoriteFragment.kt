package com.irempakyurek.rickandmorty.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.irempakyurek.rickandmorty.MySharedPreference
import com.irempakyurek.rickandmorty.R
import com.irempakyurek.rickandmorty.adapter.FavoritesAdapter
import com.irempakyurek.rickandmorty.databinding.FragmentFavoriteBinding
import com.irempakyurek.rickandmorty.entity.Result
import com.irempakyurek.rickandmorty.utils.Constants

class FavoriteFragment : Fragment() {

    private lateinit var adapter: FavoritesAdapter
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var mFavoritesList: ArrayList<Result>
    private lateinit var sharedPreference: MySharedPreference.MySharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        setAdapter()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreference = MySharedPreference.MySharedPreference(requireContext())
        mFavoritesList = sharedPreference.retrieveFavorites()
    }

    override fun onResume() {
        super.onResume()

        val img: ImageView = requireActivity().findViewById(R.id.back)
        img.visibility = View.GONE
        val imgGrid: ImageView = requireActivity().findViewById(R.id.grid_view)
        val imgList: ImageView = requireActivity().findViewById(R.id.list_view)
        if (Constants.isGrid){
            imgGrid.visibility = View.GONE
            imgList.visibility = View.VISIBLE
        }else{
            imgGrid.visibility = View.VISIBLE
            imgList.visibility = View.GONE
        }
        imgGrid.setOnClickListener {
            imgGrid.visibility = View.GONE
            imgList.visibility = View.VISIBLE
            Constants.isGrid = true
            setAdapter()
        }
        imgList.setOnClickListener {
            imgGrid.visibility = View.VISIBLE
            imgList.visibility = View.GONE
            Constants.isGrid = false
            setAdapter()
        }

    }

    private fun setAdapter() {
        if (Constants.isGrid) {
            binding.rvCharacter.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        } else {
            binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        }
        sharedPreference = MySharedPreference.MySharedPreference(requireContext())
        mFavoritesList = sharedPreference.retrieveFavorites()

        if(mFavoritesList.size == 0){
            binding.txtNoCharacter.visibility = View.VISIBLE
        }else{
            binding.txtNoCharacter.visibility = View.GONE
            adapter = FavoritesAdapter(requireContext(), mFavoritesList)
            binding.favoritesAdapter = adapter
        }


    }
}