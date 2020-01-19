package com.gunwook.faceapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable


abstract class BaseMVPActivity<T : ViewDataBinding, P : BasePresenter> : AppCompatActivity() {
    var binding : T? = null
    protected var compositeDisposable = CompositeDisposable()

    lateinit var presenter: P

    abstract fun getLayoutId() : Int
    abstract fun initView()
    abstract fun initPresenter() : P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = initPresenter()
        presenter.attach()


        binding = DataBindingUtil.setContentView(this@BaseMVPActivity,getLayoutId())




        initView()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
        presenter.detach()
    }
}