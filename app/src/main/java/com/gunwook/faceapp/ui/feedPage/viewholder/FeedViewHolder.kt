package com.gunwook.faceapp.ui.feedPage.viewholder

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gunwook.faceapp.base.BaseViewHolder
import com.gunwook.faceapp.databinding.CellListFeedBinding
import com.gunwook.faceapp.ext.setUrl
import com.gunwook.faceapp.ui.feedPage.model.FeedModel

class FeedViewHolder(context : Context, layout : Int, parent : ViewGroup) : BaseViewHolder<FeedModel>(context, layout, parent) {
    private val binding : CellListFeedBinding   = DataBindingUtil.bind(itemView)!!
    override fun onViewCreated(item: FeedModel?) {
        item?.let {
            binding.nameTv.text = it.user?.nickname
            binding.contentTv.text = it.story_message
            Glide.with(context).load(it.user?.profile_img?.setUrl()).centerCrop().apply(
                RequestOptions.circleCropTransform()).into(binding.profileIv)

            if (it.story_imgs.isNotEmpty()) Glide.with(context).load(it.story_imgs[0].setUrl()).into(binding.feedIv)
        }
    }
}