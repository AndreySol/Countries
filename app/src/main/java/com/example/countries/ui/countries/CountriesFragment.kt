package com.example.countries.ui.countries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.CountriesApp
import com.example.countries.R
import com.example.countries.common.ErrorCode
import com.example.countries.databinding.FragmentCountriesBinding
import com.example.countries.domain.entity.Country
import kotlinx.coroutines.launch

class CountriesFragment : Fragment(R.layout.fragment_countries) {

    private lateinit var binding: FragmentCountriesBinding
    private lateinit var viewModel: CountriesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountriesBinding.bind(view)

        val adapter = CountriesListAdapter()
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = LinearLayoutManager(context)

        viewModel = initViewModel()

        observe(adapter)
    }

    private fun initViewModel(): CountriesViewModel {
        val requestCountriesUseCase =
            (context?.applicationContext as CountriesApp).appComposition.fetchRequestCountriesUseCase
        return ViewModelProvider(
            this,
            CountriesViewModelFactory(requestCountriesUseCase)
        )[CountriesViewModel::class.java]
    }

    private fun observe(adapter: CountriesListAdapter) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.flow.collect { state ->
                    when (state) {
                        is CountriesUiState.Loaded -> showContent(adapter, state.data)
                        CountriesUiState.Loading -> showLoadingState(true)
                        is CountriesUiState.Failure -> showErrorState(state.errorCode)
                    }
                }
            }
        }
    }

    private fun showErrorState(errorCode: ErrorCode) {
        binding.progressBar.visibility = View.GONE
        binding.rvCountries.visibility = View.GONE
        binding.tvError.text = errorCode.toMessage(context)
        binding.tvError.visibility = View.VISIBLE
    }

    private fun showContent(adapter: CountriesListAdapter, data: List<Country>) {
        adapter.submitList(data)
        showLoadingState(false)
    }

    private fun showLoadingState(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}


