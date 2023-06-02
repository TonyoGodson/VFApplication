package com.godston.vfapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.godston.vfapplication.databinding.CountryItemBinding
import com.godston.vfapplication.model.Country
import com.godston.vfapplication.ui.CountryListDirections
import javax.inject.Inject

class CountryAdapter @Inject constructor() : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private lateinit var binding: CountryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CountryItemBinding.inflate(inflater, parent, false)
        return CountryViewHolder()
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class CountryViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            binding.apply {
                countryTv.text = item.name
                regionTv.text = item.region
                Glide.with(countryIv.context).load(item.flag).into(countryIv)
                forClick.setOnClickListener {
                    val action = CountryListDirections.actionCountryListToCountryDetail(item)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }



    private val diffCallback = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)
}
