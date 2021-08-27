package com.irempakyurek.rickandmorty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.irempakyurek.rickandmorty.databinding.SplashItemsBinding
import com.irempakyurek.rickandmorty.entity.SplashItemsModel

class SplashScreenAdapter(var mContext: Context, var itemList: List<SplashItemsModel>) :
    RecyclerView.Adapter<SplashScreenAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(splashItemsBinding: SplashItemsBinding) :
        RecyclerView.ViewHolder(splashItemsBinding.root) {
        var view: SplashItemsBinding

        init {
            this.view = splashItemsBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = SplashItemsBinding.inflate(layoutInflater, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = itemList.get(position)

        holder.view.itemObject = item

        holder.view.imgSplash.setImageResource(
            mContext.resources.getIdentifier(
                item.itemImageName,
                "drawable",
                mContext.packageName
            )
        )

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}