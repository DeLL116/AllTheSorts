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
import com.demo.chris.allthesorts.sorts.SortData
import com.google.android.material.snackbar.Snackbar
import com.nochino.support.androidui.views.recyclerview.BaseRecyclerViewClickListener
import com.nochino.support.androidui.views.recyclerview.adapters.ScaleAxis
import kotlinx.android.synthetic.main.fragment_sort_algo.view.*

/**
 * A fragment that depicts a list of [SortData] objects.
 */
class SortAlgoFragment : Fragment(), View.OnClickListener, SortAlgo.SortAlgoEventListener {

    private var sortAlgo: SortAlgo? = null

    private var listener: OnSortAlgoFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            sortAlgo = it.getParcelable(ARG_SORT_ALGO)
            sortAlgo?.sortAlgoEventListener = this
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_algo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.sort_algo_frag_button_sort.setOnClickListener(this)
        view.sort_algo_frag_button_shuffle.setOnClickListener(this)

        // Set the adapter
        if (view.sort_algo_frag_rv is RecyclerView) {
            with(view.sort_algo_frag_rv) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                sortAlgo?.let {
                    view.sort_algo_frag_rv.post {
                        adapter = SortAlgoAdapter(
                            context,
                            ScaleAxis.X,
                            view.sort_algo_frag_rv.width,
                            it.sortData
                        ).apply {
                            setListener(
                                object : BaseRecyclerViewClickListener<Int> {
                                    override fun onItemClicked(item: Int) {
                                        Snackbar.make(getView()!!, Integer.toString(item), Snackbar.LENGTH_SHORT).show()
                                        listener?.onSortAlgoFragmentInteraction(sortAlgo, item)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
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
        sortAlgo?.sortAlgoEventListener = null
        sortAlgo = null
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.sort_algo_frag_button_sort -> sortAlgo?.startSort()
            v?.id == R.id.sort_algo_frag_button_shuffle -> sortAlgo?.shuffle()
        }
    }

    override fun onSortAlgoDataChanged(updatedSortData: SortData) {
        val sortAlgoAdapter = (view?.sort_algo_frag_rv?.adapter as SortAlgoAdapter)
        val adapterDiffUtil = SortAlgoDiffUtil(sortAlgoAdapter.items, updatedSortData.data)
        sortAlgoAdapter.update(updatedSortData.data, adapterDiffUtil)
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
