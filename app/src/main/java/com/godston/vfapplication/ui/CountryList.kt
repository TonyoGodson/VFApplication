package com.godston.vfapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.godston.vfapplication.adapter.CountryAdapter
import com.godston.vfapplication.databinding.FragmentCountryListBinding
import com.godston.vfapplication.utils.DataStatus
import com.godston.vfapplication.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CountryList : Fragment() {
    private val TAG = "Country list"
    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountryViewModel by viewModels()

    @Inject
    lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            binding.apply {
                viewModel.getAllCountries()
                viewModel.fetchCountry.observe(viewLifecycleOwner) {
                    when (it.status) {
                        DataStatus.Status.LOADING -> {
                            listprogress.visibility = View.VISIBLE
                        }
                        DataStatus.Status.SUCCESS -> {
                            listprogress.visibility = View.GONE
                            countryRv.adapter = countryAdapter
                            countryRv.layoutManager = LinearLayoutManager(requireContext())
                            countryAdapter.differ.submitList(it.data)
                            Log.d(TAG, "Here are your countries: ${it.data}")
                        }
                        DataStatus.Status.ERROR -> {
                            listprogress.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
