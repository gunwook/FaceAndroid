package com.gunwook.faceapp.ui.feedPage.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseAdapterPresenter
import com.gunwook.faceapp.base.BaseRecyclerAdapter
import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import com.gunwook.faceapp.ui.feedPage.presenter.FeedPresenter
import com.gunwook.faceapp.ui.feedPage.viewholder.FeedViewHolder

class FeedAdapter(context : Context , presenter : FeedPresenter) : BaseRecyclerAdapter<FeedModel>(diffCallback,context,presenter) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedViewHolder(context , R.layout.cell_list_feed,  parent)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FeedModel>() {
            override fun areItemsTheSame(oldItem: FeedModel, newItem: FeedModel) =
                oldItem.story_id == newItem.story_id // check uniqueness

            override fun areContentsTheSame(oldItem: FeedModel, newItem: FeedModel) =
                oldItem.story_title == newItem.story_title // check contents
        }
    }
}