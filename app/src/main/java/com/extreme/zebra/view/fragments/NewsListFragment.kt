package com.extreme.zebra.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extreme.zebra.R
import com.extreme.zebra.model.NewsDataModel
import com.extreme.zebra.util.isInternetAvailable
import com.extreme.zebra.view.adapters.NewsListAdapter
import com.extreme.zebra.viewmodel.AuthViewModel


/**
 * A fragment representing a list of Items.
 */
class NewsListFragment : Fragment() {
    private var model: AuthViewModel? = null
    private var isApiCalling = false
    private var mAdapter: NewsListAdapter? = null
    private var noDataFound: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var historyVideoList: ArrayList<NewsDataModel> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video_list, container, false)

        noDataFound = view.findViewById(R.id.noDataFound)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(this).get(AuthViewModel::class.java)
        getVideoList()
        observeResponse()
    }

    private fun observeResponse() {
        model?.successVideoResponse?.observe(requireActivity(), {
            isApiCalling = false
            progressBar?.visibility = View.GONE
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
        model?.failureVideoResponse?.observe(requireActivity(), {
            noDataFound?.text = getString(R.string.something_went_wrong)
            noDataFound?.visibility = View.VISIBLE
            recyclerView?.visibility = View.GONE
            isApiCalling = false
            progressBar?.visibility = View.GONE
        })
    }

    private fun updateAdapter() {
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        mAdapter = NewsListAdapter(requireContext(), historyVideoList)
        recyclerView?.adapter = mAdapter
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            getVideoList()
        }
    }

    private fun getVideoList() {
        if (isInternetAvailable()) {
            progressBar?.visibility = View.VISIBLE
            // Load your data here or do network operations here
            model?.getNewsFeedList()
        } else
            Toast.makeText(
                activity,
                getString(R.string.no_internet_connection_message),
                Toast.LENGTH_LONG
            ).show()
    }
}