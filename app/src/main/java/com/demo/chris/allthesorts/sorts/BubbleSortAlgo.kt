package com.demo.chris.allthesorts.sorts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Defines a Bubble Sort sorting algorithm
 */
@Parcelize
data class BubbleSortAlgo(
    override val name: String,
    override val data: IntArray

) : SortAlgo, Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BubbleSortAlgo

        if (name != other.name) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}