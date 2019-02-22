package com.demo.chris.allthesorts.sorts

import timber.log.Timber

class BubbleSortAlgo(sortData: SortData) : SortAlgo(sortData) {

    override fun onSortStart() {
        super.onSortStart()

        Timber.d("List was ${sortData.data}")
        Timber.d("List iteration count is $comparisonCount")
        Timber.i("List start index is $currentIndex")

        var i = currentIndex
        var swapped = false

        while (i < sortData.data.size) {

            comparisonCount ++

            val curVal = sortData.data[i]
            val nextVal = if (i + 1 < sortData.data.size) sortData.data[i + 1] else -1

            if (nextVal != -1 && curVal > nextVal) {
                sortData.data.removeAt(i+1)
                sortData.data.add(i, nextVal)
                onSwap()
                swapped = true
            }
            i++
        }

        if (swapped) {
            return onSortStart()
        }

        Timber.i("Sorted a list of ${sortData.data.size} elements in $comparisonCount iterations!")
        Timber.d("${sortData.data}")

        // Alert that sorting has finished
        onSortEnded()
    }
}