package com.example.sportzinteractive.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportzinteractive.R
import com.example.sportzinteractive.adapter.PlayerAdapter
import com.example.sportzinteractive.databinding.FragmentSecondBinding
import com.example.sportzinteractive.model.MatchDetailsResponse
import com.example.sportzinteractive.repository.AppRepository
import com.example.sportzinteractive.util.Resource
import com.example.sportzinteractive.viewmodels.MainViewModel
import com.example.sportzinteractive.viewmodels.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import com.example.sportzinteractive.util.errorSnack

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    lateinit var playerAdapter: PlayerAdapter
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        updateView(args.matchDetailResponse, 0)

        binding.filter.setOnClickListener {
            showPopupMenu()
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


    private fun updateView(data: MatchDetailsResponse, filter: Int) {
        when (filter) {
            0 -> {
                binding.filterName.text = data.teams?.entries?.first()?.value?.nameFull
                playerAdapter.differ.submitList(data.teams?.entries?.first()?.value?.players?.values?.toList())
            }
            1 -> {
                binding.filterName.text = data.teams?.entries?.last()?.value?.nameFull
                playerAdapter.differ.submitList(data.teams?.entries?.last()?.value?.players?.values?.toList())
            }
            2 -> {
                binding.filterName.text = getString(R.string.display_all)
                playerAdapter.differ.submitList(data.teams?.entries?.flatMap { it.value.players?.values!! })
            }
        }

        binding.rvPlayerList.adapter = playerAdapter
        binding.rvPlayerList.post {
            // scroll to top on post data rendering
            binding.rvPlayerList.smoothScrollToPosition(0)
        }

    }

    //initialize recycler view with adapter
    private fun init() {
        binding.rvPlayerList.setHasFixedSize(true)
        binding.rvPlayerList.layoutManager = LinearLayoutManager(activity)
        playerAdapter = PlayerAdapter()
        setupViewModel()
    }

    //show popup menu on filter icon click
    private fun showPopupMenu() {
        val popupMenu = PopupMenu(activity, binding.filter)
        popupMenu.inflate(R.menu.menu_main)
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.team_a -> {
                    updateView(args.matchDetailResponse, 0)
                }
                R.id.team_b -> {
                    updateView(args.matchDetailResponse, 1)
                }
                R.id.display_all -> {
                    updateView(args.matchDetailResponse, 2)
                }
            }
            true
        })
    }

}