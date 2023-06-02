package com.godston.vfapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.godston.vfapplication.R
import com.godston.vfapplication.databinding.FragmentCountryDetailBinding
import com.godston.vfapplication.databinding.FragmentCountryListBinding


class CountryDetail : Fragment() {

    private var _binding: FragmentCountryDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CountryDetailArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            detailCountryNameTv.text = args.currentCountry.name
            detailCapitalTv.text = args.currentCountry.capital
            detailRegionTv.text = args.currentCountry.region
            detailCurrencyTv.text = args.currentCountry.currency
            detailLanguageTv.text = args.currentCountry.language
            Glide.with(detailIv.context).load(args.currentCountry.flag).into(detailIv)
        }
    }
}