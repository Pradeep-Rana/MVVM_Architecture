package com.wipro.news.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.wipro.news.app.NewsAppApplication
import com.wipro.news.app.db.VideoDatabase
import com.wipro.news.app.model.NewsDataModel
import com.wipro.news.app.util.showErrorLog
import com.wipro.news.app.util.showLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class AuthViewModel() : ViewModel() {
    var successNewsResponse = MutableLiveData<ArrayList<NewsDataModel>>()
    var failureNewsResponse = MutableLiveData<String>()
    var successTitle = MutableLiveData<String>()

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
            .addHeaders("Content-Type", "application/json")
            .setTag("NewsFeedApi")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    showLog(tag, response.toString())
                    val model = Gson().fromJson(response.toString(), VideoResponseModel::class.java)
                    successNewsResponse.postValue(model?.rows)
                    successTitle.postValue(model?.title)
                }

                override fun onError(anError: ANError?) {
                    showErrorLog(tag, anError?.message.toString())
                    failureNewsResponse.postValue("Failed")
                }
            })
    }


    /*****************************************************************
     * Method Name : getHistoryVideoList
     * Description : This method is used for calling get video list API.
     ******************************************************************/
    fun getHistoryVideoList() {
        GlobalScope.launch(Dispatchers.IO) {
            val chapterDatabase: VideoDatabase? =
                VideoDatabase.getDatabase(NewsAppApplication.mInstance?.applicationContext!!)
            val result = chapterDatabase?.videoDao()?.getHistoryVideo()!!
            historyList.postValue(result)
        }
    }
}