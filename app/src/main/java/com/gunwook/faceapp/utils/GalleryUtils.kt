package com.gunwook.faceapp.utils

import android.app.Activity
import android.content.Intent
import com.sembozdemir.permissionskt.askPermissions
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile


object GalleryUtils {

    fun startGallery(activity : Activity , callback : (list : ArrayList<AlbumFile>) -> Unit) {
        activity.askPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE) {
            onGranted {
                Album.image(activity) // Image selection.
                    .multipleChoice()
                    .camera(true)
                    .columnCount(2)
                    .selectCount(3)
                    .onResult {
                        callback(it)
                    }
                    .start()
            }
        }
    }

    fun singleGallery(activity : Activity , callback : (list : ArrayList<AlbumFile>) -> Unit){
        activity.askPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE){
            onGranted {
                Album.image(activity) // Image selection.
                    .multipleChoice()
                    .camera(true)
                    .columnCount(2)
                    .selectCount(1)
                    .onResult {
                        callback(it)
                    }
                    .start()
            }
        }
    }


}