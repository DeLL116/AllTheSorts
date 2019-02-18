package com.demo.chris.allthesorts

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.demo.chris.allthesorts.sorts.SortAlgo
import com.nochino.support.androidui.views.SwappableImageCardView
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener
import com.nochino.support.androidui.views.recyclerview.BaseViewHolder
import com.nochino.support.androidui.views.recyclerview.adapters.BaseRecyclerViewAdapter

class SortingAlgoAdapter(context: Context) :
    BaseRecyclerViewAdapter<SortAlgo, BaseRecyclerViewClickListener<SortAlgo>, SortingAlgoViewHolder>(context) {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): SortingAlgoViewHolder {
        return SortingAlgoViewHolder(inflate(R.layout.recycler_view_card_item_layout, parent))
    }
}

class SortingAlgoViewHolder(itemView: View) : BaseViewHolder<SortAlgo, BaseRecyclerViewClickListener<SortAlgo>>
    (itemView) {

    private val swappableImageCardView: SwappableImageCardView = itemView.findViewById(R.id.rv_card_view)

    override fun onBind(item: SortAlgo, listener: BaseRecyclerViewClickListener<SortAlgo>?) {
        swappableImageCardView.setOnClickListener { listener?.onItemClicked(item) }
        swappableImageCardView.setData(item.name, null, null, null)
    }
}