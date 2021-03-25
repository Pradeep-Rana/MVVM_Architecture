package com.extreme.zebra.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.extreme.zebra.db.VideoDatabase
import com.extreme.zebra.model.NewsDataModel
import com.extreme.zebra.util.CONTENT_TYPE
import com.extreme.zebra.util.showErrorLog
import com.extreme.zebra.util.showLog
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class AuthViewModel() : ViewModel() {
    var successVideoResponse = MutableLiveData<ArrayList<NewsDataModel>>()
    var failureVideoResponse = MutableLiveData<String>()

    private val tag = "AppRepository"
    val historyList = MutableLiveData<List<NewsDataModel>>()

    data class VideoResponseModel(
        val rows: ArrayList<NewsDataModel>,
        val title: String
    )

    /*****************************************************************
     * Method Name : getNewsFeedList
     * Description : This method is used for calling get NewsFeed list API.
     ******************************************************************/
    fun getNewsFeedList() {
        AndroidNetworking.get("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json")
            .addHeaders("Content-Type", CONTENT_TYPE)
            .setTag("NewsFeedApi")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    showLog(tag, response.toString())
                    val model = Gson().fromJson(response.toString(), VideoResponseModel::class.java)
                    successVideoResponse.postValue(model?.rows)
                }

                override fun onError(anError: ANError?) {
                    showErrorLog(tag, anError?.message.toString())
                    failureVideoResponse.postValue("Failed")
                }
            })
    }


    /*****************************************************************
     * Method Name : getHistoryVideoList
     * Description : This method is used for calling get video list API.
     ******************************************************************/
    fun getHistoryVideoList(mContext: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            val chapterDatabase: VideoDatabase? = VideoDatabase.getDatabase(mContext)
            val result = chapterDatabase?.videoDao()?.getHistoryVideo()!!
            historyList.postValue(result)
        }
    }
}