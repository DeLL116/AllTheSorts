package com.demo.chris.allthesorts.sorts

/**
 * Defines a base type for a Sorting Algorithm class
 */
interface SortAlgo {

    /** Generic name of the sorting Algorithm */
    val name: String

    /** Backing data to be sorted */
    val data: IntArray
}