package com.android.moallemtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.moallemtask.R
import com.android.moallemtask.interfaces.OnVideoClickListener
import com.android.moallemtask.model.VideoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_item.view.*

class VideoAdapter(
    private val videoList: List<VideoItem>,
    private val onVideoClickListener: OnVideoClickListener
) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view,onVideoClickListener)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentItem = videoList[position]

        Picasso.get().load(currentItem.videoThumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.videoPreview)
    }

    override fun getItemCount() = videoList.size

    class VideoViewHolder(itemView: View, onVideoClickListener: OnVideoClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val videoPreview: ImageView = itemView.video_preview
        private val onClickListener = onVideoClickListener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onVideoClick(adapterPosition)
        }
    }
}