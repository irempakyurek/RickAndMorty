package com.irempakyurek.rickandmorty.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.irempakyurek.rickandmorty.MainActivity
import com.irempakyurek.rickandmorty.MySharedPreference
import com.irempakyurek.rickandmorty.R
import com.irempakyurek.rickandmorty.adapter.SplashScreenAdapter
import com.irempakyurek.rickandmorty.databinding.FragmentSplashScreenBinding
import com.irempakyurek.rickandmorty.entity.SplashItemsModel
import com.irempakyurek.rickandmorty.utils.Constants

class SplashScreenFragment : Fragment() {

    private lateinit var view: FragmentSplashScreenBinding
    private lateinit var itemList: ArrayList<SplashItemsModel>
    private lateinit var adapter : SplashScreenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)

        val sharedPreference = MySharedPreference.MySharedPreference(requireContext())
        val isVisible = sharedPreference.prefs.getBoolean(Constants.SPLASH_SCREEN_VISIBLE, true)

        if (!isVisible){
            skipClicked()
        }else{
            view.splashScreenFragment = this

            val i1 = SplashItemsModel(1, getString(R.string.desc1), "image1")
            val i2 = SplashItemsModel(2, getString(R.string.desc2), "image2")
            val i3 = SplashItemsModel(3, getString(R.string.desc3), "image3")

            itemList = ArrayList()
            itemList.add(i1)
            itemList.add(i2)
            itemList.add(i3)

            adapter = SplashScreenAdapter(requireContext(), itemList)
            view.viewPager.adapter = adapter

            val indicator = view.indicator
            indicator.setViewPager(view.viewPager)

            view.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        view.txtSkip.visibility = View.VISIBLE
                        //to see splash screen one time
                        view.txtSkip.setOnClickListener {
                            sharedPreference.splashScreenVisibled(false)
                            skipClicked()
                        }
                    }else{
                        view.txtSkip.visibility = View.GONE
                    }
                    super.onPageSelected(position)
                }
            })
        }

        return view.root
    }

    fun skipClicked(){
        val intent = Intent(requireContext(),MainActivity::class.java)
        startActivity(intent)
    }
}