package com.gunwook.faceapp.utils

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.gunwook.faceapp.ui.writePage.WriteActivity
import com.yanzhenjie.album.AlbumFile
import java.util.ArrayList

object ActivityUtils {

    fun <T : Class<*>> startActivity(context : Activity, activity : T , data : HashMap<String, *>?){
        val intent = Intent(context, activity)

        data?.let {
            for((k , v) in it){
                if(v is String) intent.putExtra(k,v)
                else if (v is Boolean) intent.putExtra(k, v)
                else if (v is Int) intent.putExtra(k , v)
                else if (v is Long) intent.putExtra(k , v)
                else if (v is Float) intent.putExtra(k,v)
            }
        }

        context.startActivity(intent)
    }


    fun startWriteActivity(context : Activity , list : ArrayList<AlbumFile>) {
        val intent = Intent(context , WriteActivity::class.java)

        intent.putParcelableArrayListExtra(CodeUtils.ActivityCode.ALBUM_KEY, list)

        context.startActivity(intent)
    }



}