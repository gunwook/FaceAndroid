package com.gunwook.faceapp.ui.feedPage

import androidx.recyclerview.widget.LinearLayoutManager
import com.gunwook.faceapp.R
import com.gunwook.faceapp.base.BaseMVPFragment
import com.gunwook.faceapp.databinding.FragmentFeedBinding
import com.gunwook.faceapp.ui.feedPage.adapter.FeedAdapter
import com.gunwook.faceapp.ui.feedPage.callback.FeedCallback
import com.gunwook.faceapp.ui.feedPage.model.FeedModel
import com.gunwook.faceapp.ui.feedPage.presenter.FeedPresenter


class FeedFragment : BaseMVPFragment<FragmentFeedBinding , FeedPresenter>() , FeedCallback.View {
    lateinit var adapter : FeedAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_feed
    }

    override fun initView() {

        activity?.let {
            binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(it)
            binding?.feedRecyclerView?.setHasFixedSize(true)

            adapter = FeedAdapter(it, presenter)

            binding?.feedRecyclerView?.adapter = adapter
        }


        presenter.getData()
    }

    override fun initPresenter(): FeedPresenter {
        return FeedPresenter(this)
    }

    companion object {
        fun newInstance() : FeedFragment {
            return FeedFragment()
        }
    }

    override fun notify(list: List<FeedModel>) {
        adapter.submitList(list)
    }
}