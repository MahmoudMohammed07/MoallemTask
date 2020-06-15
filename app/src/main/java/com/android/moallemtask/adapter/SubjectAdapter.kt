package com.android.moallemtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.moallemtask.R
import com.android.moallemtask.model.SubjectItem
import kotlinx.android.synthetic.main.subject_item.view.*

class SubjectAdapter(private val subjectList: List<SubjectItem>) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val currentItem = subjectList[position]

        holder.subjectImg.setImageResource(currentItem.imageResource)
        holder.subjectTitle.text = currentItem.title
    }

    override fun getItemCount() = subjectList.size

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectImg: ImageView = itemView.subject_img
        val subjectTitle: TextView = itemView.subject_title
    }
}