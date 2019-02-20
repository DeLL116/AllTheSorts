package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Defines a base type for a Sorting Algorithm class
 */
@Parcelize
data class SortData(
    val name: String,
    val data: MutableList<Int>,
    val shuffleable: Boolean
) : Parcelable {
    companion object {
        fun create(name: String, range: IntRange, shuffle: Boolean = true): SortData {
            val sortData = SortData(name, range.toMutableList(), shuffle)
            if (shuffle) {
                sortData.data.shuffle()
            }
            return sortData
        }
    }
}