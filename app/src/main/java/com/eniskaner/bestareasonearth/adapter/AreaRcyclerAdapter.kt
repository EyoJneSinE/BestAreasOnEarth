package com.eniskaner.bestareasonearth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eniskaner.bestareasonearth.R
import com.eniskaner.bestareasonearth.roomdb.Area
import javax.inject.Inject

class AreaRcyclerAdapter @Inject constructor(
    val glide : RequestManager
): RecyclerView.Adapter<AreaRcyclerAdapter.AreaViewHolder>() {
    class AreaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Area>() {
        override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
            return oldItem == newItem
        }

    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var areas: List<Area>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.area_row, parent, false)
        return AreaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val area = areas[position]
        holder.itemView.apply {
            val imageView = findViewById<ImageView>(R.id.areaImageView)
            val areaNameText = findViewById<TextView>(R.id.areaNameText)
            val areaCityNameText = findViewById<TextView>(R.id.areaCityNameText)
            val areaFamousWhyText = findViewById<TextView>(R.id.areaFamousWhyText)
            val areaSpendAmountText = findViewById<TextView>(R.id.areaSpendMoneyText)

            glide.load(area.imageUrl).into(imageView)
            areaNameText.text = "Area Name: ${area.areaName}"
            areaCityNameText.text = "Area City Name: ${area.areaCityName}"
            areaFamousWhyText.text = "Area Why Famous: ${area.areaWhyFamous}"
            areaSpendAmountText.text = "Spend Amount: ${area.areaSpendAmount}"
        }
    }
    override fun getItemCount(): Int {
        return areas.size
    }
}