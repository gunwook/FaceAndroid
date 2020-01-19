package com.gunwook.faceapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected var binding : T? = null
    protected var compositeDisposable = CompositeDisposable()
    abstract fun getLayoutId() : Int
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@BaseActivity,getLayoutId())
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}