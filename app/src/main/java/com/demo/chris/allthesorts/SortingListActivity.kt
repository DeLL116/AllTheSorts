package com.demo.chris.allthesorts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.chris.allthesorts.sorts.BubbleSortAlgo
import com.demo.chris.allthesorts.sorts.SortAlgo
import com.demo.chris.allthesorts.sorts.SortData
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class SortingListActivity :
    AppCompatActivity(),
    BaseRecyclerViewClickListener<SortAlgo>,
    SortAlgoFragment.OnSortAlgoFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        sorting_algo_rv.layoutManager = LinearLayoutManager(this)

        sorting_algo_rv.adapter = SortingAlgoAdapter(this).apply {
            setListener(this@SortingListActivity)
            setItems(
                listOf(
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 2", IntRange(0, 1))),
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 3", IntRange(0, 2))),
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 10", IntRange(0, 9))),
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 10 *(No Shuffle)", IntRange(0, 9), false)),
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 50", IntRange(0, 49))),
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 100", IntRange(0, 99))),
                    BubbleSortAlgo(SortData.create("BubbleSortAlgo - 1080", IntRange(0, 1079)))
                )
            )
        }
    }

    override fun onItemClicked(item: SortAlgo) {
        Timber.d("onItemClicked :: Clicked SortData %s", item.name)

        // Shuffle the data before creating the fragment (if the SortData is able to be shuffled)
        if (item.sortData.shuffleable) {
            item.shuffle()
        }

        // Create and add the new SortAlgoFragment to display the SortAlgo
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, SortAlgoFragment.newInstance(item), item.name)
            .addToBackStack(item.name)
            .commit()
    }

    override fun onSortAlgoFragmentInteraction(sortAlgo: SortAlgo?, data: Int) {
        Timber.d("onSortAlgoFragmentInteraction :: Clicked [${sortAlgo?.name}] at data point [$data]")
    }
}