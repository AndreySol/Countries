package com.example.countries.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CountryItemBinding
import com.example.countries.domain.entity.Country

class CountriesListAdapter :
    ListAdapter<Country, CountriesListAdapter.CountryViewHolder>(CountriesDiff()) {

    inner class CountryViewHolder(val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item, parent, false)

        return CountryViewHolder(CountryItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val binding = holder.binding

        val country = currentList[position]

        binding.tvName.text = String.format("%s, %s", country.name, country.region)
        binding.tvCode.text = country.code
        binding.tvCapital.text = country.capital
    }
}

private class CountriesDiff : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}