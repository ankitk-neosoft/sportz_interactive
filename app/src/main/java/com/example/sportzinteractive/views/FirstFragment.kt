package com.example.sportzinteractive.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportzinteractive.adapter.MatchAdapter
import com.example.sportzinteractive.databinding.FragmentFirstBinding
import com.example.sportzinteractive.model.MatchDetailsResponse
import com.example.sportzinteractive.repository.AppRepository
import com.example.sportzinteractive.util.Resource
import com.example.sportzinteractive.util.errorSnack
import com.example.sportzinteractive.viewmodels.MainViewModel
import com.example.sportzinteractive.viewmodels.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        getMatchDetails()

        //navigate to screen 2 with action data

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.rvMatchList.setHasFixedSize(true)
        binding.rvMatchList.layoutManager = LinearLayoutManager(activity)
        matchAdapter = MatchAdapter()
        setupViewModel()
    }

    //fetch the view modal instance from main activity/
    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }

    //observe the data for various responses
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

    // render the data on UI
    private fun updateViews(data: List<MatchDetailsResponse>) {
        matchAdapter.differ.submitList(data)
        binding.rvMatchList.adapter = matchAdapter
    }

    private fun hideProgressBar() {
        binding.progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }

}