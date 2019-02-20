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

    @CallSuper
    fun shuffle() {
        Timber.d("Shuffling....")
        sortData.data.shuffle()
    }

    @CallSuper
    open fun sort(){
        Timber.d("Sorting....")
    }

    @CallSuper
    open fun swap() {
        Timber.d("Swapping....")
    }
}