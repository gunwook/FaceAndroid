package com.gunwook.faceapp.ui.writePage

import com.bumptech.glide.Glide
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPActivity
import com.gunwook.faceapp.databinding.ActivityWriteBinding
import com.gunwook.faceapp.ext.toast
import com.gunwook.faceapp.ui.writePage.callback.WriteCallback
import com.gunwook.faceapp.ui.writePage.presenter.WritePresenter
import com.gunwook.faceapp.utils.CodeUtils
import com.gunwook.faceapp.utils.Logd
import kotlinx.android.synthetic.main.activity_write.view.*

class WriteActivity : BaseMVPActivity<ActivityWriteBinding , WritePresenter>() , WriteCallback.View{


    override fun getLayoutId(): Int {
        return R.layout.activity_write
    }

    override fun initView() {
        presenter.imageList = intent.getParcelableArrayListExtra(CodeUtils.ActivityCode.ALBUM_KEY)

        binding?.let { view ->
            Glide.with(this).load(presenter.imageList[0].thumbPath).into(view.writeIv)

            view.saveTv.setOnClickListener {
                presenter.checkData(view.titleEt.text.toString() , view.tagEt.text.toString() , view.contsEt.text.toString())
            }
        }
    }

    override fun initPresenter(): WritePresenter {
        return WritePresenter(this)
    }

    override fun doNext() {
        finish()
    }

    override fun doSave(title: String, tag: String, message: String) {
        presenter.saveData(title , tag , message)

    }

    override fun doToast(res: Int) {
        getString(res).toast(this)
    }
}