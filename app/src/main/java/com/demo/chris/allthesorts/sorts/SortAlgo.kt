package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import androidx.annotation.CallSuper
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import timber.log.Timber

@Parcelize
open class SortAlgo(val sortData: SortData) : Parcelable {

    @IgnoredOnParcel
    protected var comparisonCount = 0

    @IgnoredOnParcel
    protected var currentIndex = 0

    @IgnoredOnParcel
    val name = sortData.name

    @IgnoredOnParcel
    var sortAlgoEventListener: SortAlgoEventListener? = null

    private fun reset() {
        currentIndex = 0
        comparisonCount = 0
    }

    @CallSuper
    fun shuffle() {
        reset()
        Timber.d("Shuffling....")
        Timber.d("Old data [${sortData.data}]")
        sortData.data.shuffle()
        sortAlgoEventListener?.onSortAlgoDataChanged(sortData)
        Timber.d("New data [${sortData.data}]")
    }

    fun startSort() {
        onSortStart()
    }

    @CallSuper
    protected open fun onSortStart(){
        Timber.d("Sorting....")
    }

    open fun onSortEnded() {
        Timber.d("Sort finished....")
        reset()
    }

    @CallSuper
    open fun onSwap() {
        Timber.d("Swapping....")
        sortAlgoEventListener?.onSortAlgoDataChanged(sortData)
    }

    interface SortAlgoEventListener {
        fun onSortAlgoDataChanged(updatedSortData: SortData)
    }
}