package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class SortAlgo(val sortData: SortData) : Parcelable {

    protected var listIterationCount = 0

    protected var currentIndex = 0

    val name = sortData.name

    fun shuffle() {
        sortData.data.shuffle()
    }

    open fun sort(sortData: SortData){}
    open fun swap() {}

}