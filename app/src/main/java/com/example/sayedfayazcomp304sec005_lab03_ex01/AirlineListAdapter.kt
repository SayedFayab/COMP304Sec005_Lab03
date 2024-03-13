package com.example.sayedfayazcomp304sec005_lab03_ex01

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sayedfayazcomp304sec005_lab03_ex01.database.schedule.Schedule
import com.example.sayedfayazcomp304sec005_lab03_ex01.databinding.AirlineListItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class AirlineListAdapter(
    private val onItemClicked: (Schedule) -> Unit
) : ListAdapter<Schedule, AirlineListAdapter.AirlineViewHolder>(DiffCallback)
{

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlineViewHolder {
        val viewHolder = AirlineViewHolder(
            AirlineListItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AirlineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirlineViewHolder(
        private var binding: AirlineListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            binding.airlineNameTextView.text = schedule.airlineName
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a").format(
                Date(schedule.arrivalTime.toLong() * 1000)
            )
            binding.terminalTextView.text = schedule.terminal
        }
    }
}