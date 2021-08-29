package com.irempakyurek.rickandmorty.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.irempakyurek.rickandmorty.R
import com.irempakyurek.rickandmorty.adapter.CharactersAdapter
import com.irempakyurek.rickandmorty.databinding.FragmentCharactersBinding
import com.irempakyurek.rickandmorty.utils.Constants
import com.irempakyurek.rickandmorty.viewmodel.CharactersFragmentViewModel

class CharactersFragment : Fragment() {
    private lateinit var adapter: CharactersAdapter
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var viewModel: CharactersFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)

        binding.charactersFragment = this

        binding.pageNumber = 1

        setAdapter()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CharactersFragmentViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.uploadCharacters(1)
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

    private fun setAdapter(){
        if (Constants.isGrid) {
            binding.rvCharacter.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        } else {
            binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.charactersList.observe(viewLifecycleOwner, { charactersList ->
            adapter = CharactersAdapter(requireContext(), charactersList)
            binding.charactersAdapter = adapter
        })
    }

    fun decreaseClicked(page_number: Int){
        var currentPage = page_number
        if (currentPage > 1) {
            currentPage--
            binding.pageNumber = currentPage
            viewModel.uploadCharacters(currentPage)
            setAdapter()
        }
    }

    fun increaseClicked(page_number: Int){
        var currentPage = page_number
        if (currentPage < 34) {
            currentPage++
            binding.pageNumber = currentPage
            viewModel.uploadCharacters(currentPage)
            setAdapter()
        }
    }
}