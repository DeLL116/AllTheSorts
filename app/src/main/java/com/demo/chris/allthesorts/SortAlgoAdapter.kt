package com.demo.chris.allthesorts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.demo.chris.allthesorts.sorts.SortAlgo
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener
import com.nochino.support.androidui.views.recyclerview.BaseViewHolder
import com.nochino.support.androidui.views.recyclerview.adapters.DistributedItemSizeAdapter
import com.nochino.support.androidui.views.recyclerview.adapters.DistributionAxis

/**
 * An adapter that basically defeats the purpose of a RecyclerView by attempting to
 * scale each item view to proportionately fit the size of the containing view.
 * @param context Context used for layout inflation
 * @param sortAlgo The data
 * @param containerViewSize The size of the containing view. Used to determine how much
 * to scale each item view so that all item views are sized proportionately to fill the
 * container view. All items will be visible in the RecyclerView if this is possible.
 */
class SortAlgoAdapter(context: Context, distributionAxis: DistributionAxis, containerViewSize: Int, sortAlgo: SortAlgo) :
    DistributedItemSizeAdapter<
            Int,
            BaseRecyclerViewClickListener<Int>,
            BaseViewHolder<Int, BaseRecyclerViewClickListener<Int>>>(context, distributionAxis, containerViewSize, sortAlgo.data) {

    /** The max value in the data set. Used to set the max of each ProgressBar */
    private val dataMaxValue = items.max()

    override fun getViewHolder(parent: ViewGroup, viewType: Int)
            : BaseViewHolder<Int, BaseRecyclerViewClickListener<Int>> {
        return VerticalProgressBarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_progress_bar_item_layout, parent, false)
        )
    }

    inner class VerticalProgressBarViewHolder(mView: View)
        : BaseViewHolder<Int, BaseRecyclerViewClickListener<Int>>(mView) {

        private val progressBar: ProgressBar = mView.findViewById(R.id.rv_vertical_progress_bar)

        override fun onBind(item: Int, listener: BaseRecyclerViewClickListener<Int>?) {

            itemView.setOnClickListener {
                listener?.onItemClicked(item)
            }

            // Set the item view's ProgressBar max
            progressBar.max = dataMaxValue ?: 0 // Elvis for the win

            // Set the item ProgressBar's progress based on the data at the position
            progressBar.progress = item

        }

        override fun toString(): String {
            return super.toString() + " '" + itemId.toString() + "'"
        }
    }
}