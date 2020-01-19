package com.gunwook.faceapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gunwook.faceapp.R
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.AlbumLoader


class MediaLoader : AlbumLoader {

    override fun load(imageView: ImageView?, albumFile: AlbumFile?) {
        load(imageView, albumFile?.path);
    }

    override fun load(imageView: ImageView?, url: String?) {
        imageView?.context?.let {
            Glide.with(it)
                .load(url)
                .transition(DrawableTransitionOptions().crossFade())
                .error(R.drawable.corner_10_solid_aaaaaa)
                .placeholder(R.drawable.corner_10_solid_aaaaaa)
                .into(imageView)
        }
    }
}