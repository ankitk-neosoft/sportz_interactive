package com.example.sportzinteractive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractive.databinding.ItemMatchBinding
import com.example.sportzinteractive.model.MatchDetailsResponse
import com.example.sportzinteractive.views.FirstFragmentDirections

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MatchDetailsResponse) {
            binding.firstTeam.text = data.teams?.entries?.first()?.value?.nameFull ?: "No data"
            binding.secondTeam.text = data.teams?.entries?.last()?.value?.nameFull ?: "No data"
            binding.seriesName.text = data.matchdetail?.series?.name ?: "No data"
            binding.venue.text = data.matchdetail?.venue?.name ?: "No data"
            binding.dateTime.text = data.matchdetail?.match?.date.plus(" ${data.matchdetail?.match?.time}") ?: "No data"

            binding.buttonFirst.setOnClickListener {
                Navigation.findNavController(binding.buttonFirst).navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(data))
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<MatchDetailsResponse>() {
        override fun areItemsTheSame(oldItem: MatchDetailsResponse, newItem: MatchDetailsResponse): Boolean {
            return oldItem.matchdetail?.match?.id == newItem.matchdetail?.match?.id
        }

        override fun areContentsTheSame(oldItem: MatchDetailsResponse, newItem: MatchDetailsResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemBinding =
            ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(itemBinding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = differ.currentList[position]
        holder.bind(match)
    }
}