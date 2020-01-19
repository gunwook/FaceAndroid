package com.gunwook.faceapp.base

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerAdapter<T>(val diffUtil : DiffUtil.ItemCallback<T> , val context: Context, private val viewModel: BaseAdapterPresenter) : ListAdapter<T,RecyclerView.ViewHolder>(diffUtil){
    override fun getItemViewType(position: Int): Int {
        return viewModel.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BaseViewHolder<*>)?.onBindViewHolder(viewModel.getItem(position))
    }
}