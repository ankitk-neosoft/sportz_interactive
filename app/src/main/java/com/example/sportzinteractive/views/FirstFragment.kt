package com.example.sportzinteractive.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportzinteractive.R
import com.example.sportzinteractive.databinding.FragmentFirstBinding
import com.example.sportzinteractive.model.MatchDetailsResponse
import com.example.sportzinteractive.repository.AppRepository
import com.example.sportzinteractive.util.Resource
import com.example.sportzinteractive.viewmodels.MainViewModel
import com.example.sportzinteractive.viewmodels.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import com.example.sportzinteractive.util.errorSnack

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        getMatchDetails()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(viewModel.matchDetailsResponse))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }

    private fun getMatchDetails() {
        viewModel.matchDetailLiveData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    updateViews(response.data!!)
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        binding.root.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun updateViews(data: MatchDetailsResponse) {
        binding.firstTeam.text = data.teams?.entries?.first()?.value?.nameFull ?: "No data"
        binding.secondTeam.text = data.teams?.entries?.last()?.value?.nameFull ?: "No data"
        binding.seriesName.text = data.matchdetail?.series?.name ?: "No data"
        binding.venue.text = data.matchdetail?.venue?.name ?: "No data"
        binding.dateTime.text = data.matchdetail?.match?.date.plus(" ${data.matchdetail?.match?.time}") ?: "No data"
    }

    private fun hideProgressBar() {
        binding.progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }

}