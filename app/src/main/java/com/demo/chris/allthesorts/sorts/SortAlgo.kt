package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Defines a base type for a Sorting Algorithm class
 */
@Parcelize
data class SortAlgo(val name: String, val data: MutableList<Int>, val shuffleable: Boolean) : Parcelable {
    companion object {
        fun create(name: String, range: IntRange, shuffle: Boolean = true): SortAlgo {
            val sortAlgo = SortAlgo(name, range.toMutableList(), shuffle)
            if (shuffle) {
                sortAlgo.data.shuffle()
            }
            return sortAlgo
        }
    }
}