package com.demo.chris.allthesorts

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.demo.chris.allthesorts.sorts.SortData
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener
import com.nochino.support.androidui.views.recyclerview.BaseViewHolder
import com.nochino.support.androidui.views.recyclerview.adapters.DistributedItemSizeAdapter
import com.nochino.support.androidui.views.recyclerview.adapters.ScaleAxis
import timber.log.Timber

/**
 * An adapter that basically defeats the purpose of a RecyclerView by attempting to
 * scale each item view to proportionately fit the size of the containing view.
 * @param context Context used for layout inflation
 * @param containerViewSize The size of the containing view. Used to determine how much
 * @param sortData The data
 * to scale each item view so that all item views are sized proportionately to fill the
 * container view. All items will be visible in the RecyclerView if this is possible.
 */
class SortAlgoAdapter(context: Context, scaleAxis: ScaleAxis, containerViewSize: Int, sortData: SortData) :
    DistributedItemSizeAdapter<
            Int,
            BaseRecyclerViewClickListener<Int>,
            BaseViewHolder<Int, BaseRecyclerViewClickListener<Int>>>(context, scaleAxis, containerViewSize, sortData.data) {

    /** The max value in the data set. Used to set the max of each ProgressBar */
    private val dataMaxValue = items.max()

    /**
     * Removes the insets in the [LayerDrawable]'s layers.
     * Drawable is mutated before changes as to not affect other instances of the Drawable
     */
    private fun removeLayerInsets(progressBar: ProgressBar) {
        val tooSmallLayerDrawable = progressBar.progressDrawable.mutate() as LayerDrawable
        tooSmallLayerDrawable.setLayerInset(1, 0, 0, 0, 0)
        tooSmallLayerDrawable.setLayerInset(2, 0, 0, 0, 0)
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int)
            : BaseViewHolder<Int, BaseRecyclerViewClickListener<Int>> {
        return VerticalProgressBarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_progress_bar_item_layout, parent, false)
        )
    }

    override fun onViewScaled(view: View) {
        // If the view has been scaled down so much that having insets on the progress
        // (and secondaryProgress) causes progress to not even be visible, also remove the insets.
        if (getScaleDistributionViewSize(view, scaleAxis) <= 3) {
            removeLayerInsets(view.findViewById(R.id.rv_vertical_progress_bar))
        }
    }

    inner class VerticalProgressBarViewHolder(mView: View)
        : BaseViewHolder<Int, BaseRecyclerViewClickListener<Int>>(mView) {

        private val progressBar: ProgressBar = mView.findViewById(R.id.rv_vertical_progress_bar)

        override fun onBind(item: Int, listener: BaseRecyclerViewClickListener<Int>?) {

            itemView.setOnClickListener { listener?.onItemClicked(item) }

            progressBar.max = dataMaxValue ?: 0 // Elvis for the win
            progressBar.progress = item

            Timber.d("Bound ProgressBar with ::\nItem [%s] \nTotal Progress [%s]\nMax [%s]",
                item,
                progressBar.progress,
                progressBar.max
            )
        }

        override fun toString(): String {
            return super.toString() + " '" + itemId.toString() + "'"
        }
    }
}