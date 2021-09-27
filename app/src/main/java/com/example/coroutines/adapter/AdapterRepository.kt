package com.example.coroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutines.R
import com.example.coroutines.databinding.ItemRepositoryBinding
import com.example.coroutines.model.Repository

class AdapterRepository : RecyclerView.Adapter<RepositoryViewHolder>() {

    private val listOfRepo: MutableList<Repository> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {

        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false).let {
                RepositoryViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        listOfRepo[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOfRepo.size

    fun refesh(newList: List<Repository>) {
        listOfRepo.addAll(newList)
        notifyDataSetChanged()
    }
}

class RepositoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    private var binding: ItemRepositoryBinding = ItemRepositoryBinding.bind(item)

    fun bind(reposi: Repository) {

        binding.nameRepository.text = reposi.nameRepository
        binding.descriptionRepository.text = reposi.description
        binding.starsRepository.text = reposi.stars.toString()
        binding.nameOwner.text = reposi.owner.login
        binding.forkRepository.text = reposi.forks.toString()
        reposi.owner?.let {
            Glide.with(itemView.context)
                .load(it.avatarUrlOwner)
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .into(binding.avatarUser)
        }
    }
}