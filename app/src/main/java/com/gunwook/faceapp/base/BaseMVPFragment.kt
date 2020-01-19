package com.gunwook.faceapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable


abstract class BaseMVPFragment<T : ViewDataBinding, P : BasePresenter> : Fragment() {

    protected var compositeDisposable = CompositeDisposable()
    protected var binding : T? = null
    lateinit var presenter: P
    abstract fun getLayoutId() : Int
    abstract fun initView()
    abstract fun initPresenter() : P
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = initPresenter()
        presenter.attach()
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)

        initView()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
        presenter.detach()
    }


}
