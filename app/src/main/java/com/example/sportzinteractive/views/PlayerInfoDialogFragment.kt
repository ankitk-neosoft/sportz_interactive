package com.example.sportzinteractive.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.sportzinteractive.databinding.FragmentPlayerinfoBinding
import com.example.sportzinteractive.model.PlayerDetails
import com.example.sportzinteractive.repository.AppRepository
import com.example.sportzinteractive.viewmodels.MainViewModel
import com.example.sportzinteractive.viewmodels.ViewModelProviderFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlayerInfoDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPlayerinfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val args: PlayerInfoDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerinfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        updateViews(args. playerDetails)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // fetch view modal instance from base activity
    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(requireActivity().application, repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }

    // updating the views with data
    private fun updateViews(playerDetails: PlayerDetails) {
        binding.playerName.text = playerDetails.nameFull

        binding.style.text = playerDetails.batting?.style ?: ""
        binding.average.text = playerDetails.batting?.average ?: ""
        binding.sr.text = playerDetails.batting?.strikerate ?: ""
        binding.runs.text = playerDetails.batting?.runs ?: ""

        binding.bstyle.text = playerDetails.bowling?.style ?: ""
        binding.baverage.text = playerDetails.bowling?.average ?: ""
        binding.bsr.text = playerDetails.bowling?.economyrate ?: ""
        binding.bruns.text = playerDetails.bowling?.wickets ?: ""
    }



}