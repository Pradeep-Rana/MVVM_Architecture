package com.wipro.news.app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extreme.zebra.R
import com.wipro.news.app.model.NewsDataModel
import com.wipro.news.app.view.adapters.NewsHistoryAdapter
import com.wipro.news.app.viewmodel.AuthViewModel

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {
    private var model: AuthViewModel? = null
    private var isApiCalling = false
    private var mAdapter: NewsHistoryAdapter? = null
    private var noDataFound: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var historyVideoList: ArrayList<NewsDataModel> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history_fragment, container, false)

        noDataFound = view.findViewById(R.id.noDataFound)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(this).get(AuthViewModel::class.java)
        getDataFromApi()
        observeData()
    }

    private fun observeData() {
        model?.historyList?.observe(requireActivity(), {
            if (it.isNullOrEmpty()) {
                noDataFound?.text = getString(R.string.no_data_found)
                noDataFound?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
            } else {
                historyVideoList.clear()
                historyVideoList.addAll(it)
                noDataFound?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
                updateAdapter()
            }
        })
    }

    private fun updateAdapter() {
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = NewsHistoryAdapter(historyVideoList)
        recyclerView?.adapter = mAdapter
    }

    private fun getDataFromApi() {
        //if (!isApiCalling) {
        isApiCalling = true
        progressBar?.visibility = View.GONE
        model?.getHistoryVideoList()
        //}
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            progressBar?.visibility = View.VISIBLE
            // Load your data here or do network operations here
            getDataFromApi()
        }
    }
}