package com.demo.chris.allthesorts.sorts

import timber.log.Timber

class BubbleSortAlgo(sortData: SortData) : SortAlgo(sortData) {

    override fun sort() {
        super.sort()

        Timber.d("List was ${sortData.data}")
        Timber.d("List iteration count is $listIterationCount")
        Timber.i("List start index is $currentIndex")

        var i = currentIndex
        var swapped = false
        while (i < sortData.data.size) {

            listIterationCount ++

            val curVal = sortData.data[i]
            val nextVal = if (i + 1 < sortData.data.size) sortData.data[i + 1] else -1

            if (nextVal != -1 && curVal > nextVal) {
                sortData.data.removeAt(i+1)
                sortData.data.add(i, nextVal)
                swapped = true
            }
            i++
        }

        if (swapped) {
            return sort()
        }

        Timber.i("Sorted a list of ${sortData.data.size} elements in $listIterationCount iterations!")
        Timber.d("${sortData.data}")

        // Alert that sorting has finished
        onSortEnded()

    }

    override fun swap() {
        super.swap()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}