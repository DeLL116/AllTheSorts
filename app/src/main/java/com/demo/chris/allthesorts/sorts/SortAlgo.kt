package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Defines a base type for a Sorting Algorithm class
 */
@Parcelize
data class SortAlgo(val name: String, val data: MutableList<Int>) : Parcelable {
    companion object {
        fun create(name: String, range: IntRange): SortAlgo {
            return SortAlgo(name, range.toMutableList())
        }
    }
}