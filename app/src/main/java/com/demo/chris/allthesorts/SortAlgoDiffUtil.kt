package com.demo.chris.allthesorts

import com.nochino.support.androidui.views.recyclerview.adapters.BaseRecyclerViewDiffUtil

class SortAlgoDiffUtil(oldList: List<Int>, newList: List<Int>) : BaseRecyclerViewDiffUtil<Int>(oldList, newList) {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}