package com.example.findyourcatv1.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findyourcatv1.R

class MyAdapter(
    private val items: List<Cat>,
    private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewtype: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_cat, viewGroup, false)
        return MyViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {

        viewHolder.textViewCatName.text = items[position].name
        viewHolder.textViewCatDesc.text = items[position].description
        viewHolder.textViewCatPlace.text = items[position].place
        viewHolder.textViewCatReward.text = items[position].reward.toString()
        viewHolder.textViewCatUserId.text = items[position].userId
    }

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewCatId: TextView = itemView.findViewById(R.id.list_cat_id)
        val textViewCatName: TextView = itemView.findViewById(R.id.list_cat_name)
        val textViewCatDesc: TextView = itemView.findViewById(R.id.list_cat_desc)
        val textViewCatPlace: TextView = itemView.findViewById(R.id.list_cat_place)
        val textViewCatReward: TextView = itemView.findViewById(R.id.list_cat_reward)
        val textViewCatUserId: TextView = itemView.findViewById(R.id.list_cat_userId)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            onItemClicked(position)
        }
    }
}

