package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import androidx.annotation.CallSuper
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import timber.log.Timber

@Parcelize
open class SortAlgo(val sortData: SortData) : Parcelable {

    @IgnoredOnParcel
    protected var listIterationCount = 0

    @IgnoredOnParcel
    protected var currentIndex = 0

    @IgnoredOnParcel
    val name = sortData.name

    private fun reset() {
        currentIndex = 0
        listIterationCount = 0
    }

    @CallSuper
    fun shuffle() {
        reset()
        Timber.d("Shuffling....")
        Timber.d("Old data [${sortData.data}]")
        sortData.data.shuffle()
        Timber.d("New data [${sortData.data}]")
    }

    @CallSuper
    open fun sort(){
        Timber.d("Sorting....")
    }

    open fun onSortEnded() {
        Timber.d("Sort finished....")
        reset()
    }

    @CallSuper
    open fun swap() {
        Timber.d("Swapping....")
    }
}