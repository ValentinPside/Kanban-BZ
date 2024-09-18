package com.example.kanbanbz.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kanbanbz.R
import com.example.kanbanbz.databinding.TaskItemBinding
import com.example.kanbanbz.domain.models.Task

class Adapter(private val onClick: (id: String) -> Unit): ListAdapter<Task, Adapter.ViewHolder>(DiffUtill()) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TaskItemBinding.bind(itemView)

        fun bind(item: Task) {
            binding.taskName.text = item.name
            binding.taskDate.text = item.date
            binding.root.setOnClickListener {
                onClick.invoke(item.id.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    private class DiffUtill : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}