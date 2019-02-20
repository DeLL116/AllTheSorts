package com.demo.chris.allthesorts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.chris.allthesorts.sorts.SortAlgo
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
                    SortAlgo.create("2", IntRange(0, 1)),
                    SortAlgo.create("3", IntRange(0, 2)),
                    SortAlgo.create("10", IntRange(0, 9)),
                    SortAlgo.create("10 (No Shuffle)", IntRange(0, 9), false),
                    SortAlgo.create("15", IntRange(0, 14)),
                    SortAlgo.create("20", IntRange(0, 19)),
                    SortAlgo.create("50", IntRange(0, 49)),
                    SortAlgo.create("100", IntRange(0, 99)),
                    SortAlgo.create("540", IntRange(0, 539)),
                    SortAlgo.create("1000", IntRange(0, 999)),
                    SortAlgo.create("1080", IntRange(0, 1079)),
                    SortAlgo.create("1081", IntRange(0, 1080)),
                    SortAlgo.create("10000", IntRange(0, 9999)),
                    SortAlgo.create("100000", IntRange(0, 99999))
                )
            )
        }
    }

    override fun onItemClicked(item: SortAlgo) {
        Timber.d("onItemClicked :: Clicked SortAlgo %s", item.name)

        // If the SortAlgo is shuffleable...shuffle it before creating a new SortAlgoFragment
        if (item.shuffleable) {
            item.data.shuffle()
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