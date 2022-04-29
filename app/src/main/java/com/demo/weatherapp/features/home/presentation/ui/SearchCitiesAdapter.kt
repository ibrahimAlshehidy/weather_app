package com.demo.weatherapp.features.home.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.weatherapp.databinding.ItemCityBinding
import com.demo.weatherapp.features.home.data.models.CitiesData

class SearchCitiesAdapter(
    private val actionListener: ActionsListener
) :
    RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder>() {

    private var dataList: ArrayList<CitiesData>? = null

    init {
        dataList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCityName.text = dataList?.get(position)?.name
        holder.binding.tvCityRegion.text = dataList?.get(position)?.region

        holder.itemView.setOnClickListener {
            actionListener.onCityClickClick(dataList?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<CitiesData>?) {
        dataList!!.clear()
        dataList!!.addAll(data!!)
        notifyDataSetChanged()
    }


    class ViewHolder(var binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ActionsListener {
        fun onCityClickClick(city: CitiesData?)
    }
}