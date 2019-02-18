package com.demo.chris.allthesorts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.chris.allthesorts.sorts.SortAlgo
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener

/**
 * A fragment representing a list of [SortAlgo] objects.
 */
class SortAlgoFragment : Fragment() {

    private var sortAlgo: SortAlgo? = null

    private var listener: OnSortAlgoFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            sortAlgo = it.getParcelable(ARG_SORT_ALGO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sort_algo, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                sortAlgo?.let {
                    adapter = SortAlgoAdapter(context, container!!.measuredWidth, it).apply {
                        setListener(
                            object : BaseRecyclerViewClickListener<Int> {
                                override fun onItemClicked(item: Int) {
                                    listener?.onSortAlgoFragmentInteraction(sortAlgo, item)
                                }
                            }
                        )
                    }
                }
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSortAlgoFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Interface implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnSortAlgoFragmentInteractionListener {
        fun onSortAlgoFragmentInteraction(sortAlgo: SortAlgo?, data: Int)
    }

    companion object {

        const val ARG_SORT_ALGO = "sort-algo"

        @JvmStatic
        fun newInstance(sortAlgo: SortAlgo) =
            SortAlgoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SORT_ALGO, sortAlgo)
                }
            }
    }
}
