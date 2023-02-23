package com.example.sportzinteractive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractive.databinding.ItemPlayerBinding
import com.example.sportzinteractive.model.PlayerDetails
import com.example.sportzinteractive.views.SecondFragmentDirections
import com.hadi.retrofitmvvm.util.Utils

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playerDetails: PlayerDetails) {
            binding.playerName.text = playerDetails.nameFull
            binding.isCaptionKeeper.text = Utils.playerTag(playerDetails)
            binding.root.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(
                    SecondFragmentDirections.actionSecondFragmentToPlayerInfoDialogFragment(
                        playerDetails
                    )
                )
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<PlayerDetails>() {
        override fun areItemsTheSame(oldItem: PlayerDetails, newItem: PlayerDetails): Boolean {
            return oldItem.position == newItem.position
        }

        override fun areContentsTheSame(oldItem: PlayerDetails, newItem: PlayerDetails): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(itemBinding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val playerDetails = differ.currentList[position]
        holder.bind(playerDetails)
    }
}