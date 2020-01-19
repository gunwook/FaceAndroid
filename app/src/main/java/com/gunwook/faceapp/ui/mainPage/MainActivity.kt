package com.gunwook.faceapp.ui.mainPage

import androidx.fragment.app.Fragment
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPActivity
import com.gunwook.faceapp.databinding.ActivityMainBinding
import com.gunwook.faceapp.ui.feedPage.FeedFragment
import com.gunwook.faceapp.ui.loginPage.LoginActivity
import com.gunwook.faceapp.ui.mainPage.callback.MainCallback
import com.gunwook.faceapp.ui.mainPage.presenter.MainPresenter
import com.gunwook.faceapp.ui.writePage.WriteActivity
import com.gunwook.faceapp.utils.ActivityUtils
import com.gunwook.faceapp.utils.GalleryUtils
import com.yanzhenjie.album.AlbumFile
import java.util.ArrayList

class MainActivity : BaseMVPActivity<ActivityMainBinding , MainPresenter>() , MainCallback.View {

    private val feedFragment : FeedFragment by lazy { FeedFragment.newInstance() }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        binding?.homeIv?.isSelected = true
        binding?.homeIv?.setOnClickListener {
            feedFragment.presenter.getData()
        }
        binding?.cameraIv?.setOnClickListener {
            presenter.showGallery(this)
        }

        supportFragmentManager.run {
            this.beginTransaction().apply {
                this.add(R.id.parentView,feedFragment)
                this.commit()
            }
        }
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.run {
            this.beginTransaction().apply {
                if (fragment.isAdded) {
                    show(fragment)
                } else {
                    add(R.id.parentView, fragment)
                }

                supportFragmentManager.fragments.forEach {
                    if (it != fragment && it.isAdded) {
                        hide(it)
                    }
                }
            }.commit()
        }
    }


    override fun moveLoginPage() {
        ActivityUtils.startActivity(this, LoginActivity::class.java , null)
    }

    override fun moveWritePage(images : ArrayList<AlbumFile>) {
        ActivityUtils.startWriteActivity(this  , images)
    }
}
