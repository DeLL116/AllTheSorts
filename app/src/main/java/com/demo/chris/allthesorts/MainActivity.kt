package com.demo.chris.allthesorts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.chris.allthesorts.sorts.BubbleSortAlgo
import com.demo.chris.allthesorts.sorts.SortAlgo
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity :
    AppCompatActivity(),
    BaseRecyclerViewClickListener<SortAlgo> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sorting_algo_rv.layoutManager = LinearLayoutManager(this)
        sorting_algo_rv.adapter = SortingAlgoAdapter(this).apply {
            setListener(this@MainActivity)
            setItems(
                listOf(
                    BubbleSortAlgo("Bubble Sort"),
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    },
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    },
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    },
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    },
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    },
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    },
                    object : SortAlgo {
                        override val name: String = "PlaceHolder"
                    }

                )
            )
        }
    }

    override fun onItemClicked(item: SortAlgo) {
        Timber.d("Clicked SortAlgo %s", item.name)
    }
}
