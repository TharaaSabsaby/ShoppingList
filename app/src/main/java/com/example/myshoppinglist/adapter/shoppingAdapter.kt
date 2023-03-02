package com.example.myshoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppinglist.R
import com.example.myshoppinglist.database.entity.Item
import com.example.myshoppinglist.ui.listViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class shoppingAdapter(var list: List<Item>, val viewModel: listViewModel) :
    RecyclerView.Adapter<shoppingAdapter.shoppingListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shoppingListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listadapter, parent, false)
        return shoppingListViewHolder(view)
    }

    // This function is used to return total number of size of list.
    override fun getItemCount(): Int {
        return list.size
    }

    // In onBindViewHolder we will bind our itemViews with adapter
    override fun onBindViewHolder(holder: shoppingListViewHolder, position: Int) {
        var currentItem = list[position]
        holder.tvItemName.text = currentItem.itemName
        holder.deleteBtn.setOnClickListener { viewModel.delete(currentItem)
            notifyItemRemoved(holder.adapterPosition)
        }

    }


    // Inner class for viewHolder
    inner class shoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvItemName : TextView = itemView.findViewById(R.id.tvItemName)
        val deleteBtn : TextView = itemView.findViewById(R.id.deleteBtn)
    }
}
