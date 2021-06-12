package com.pn.aluminium.catalogue.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.pn.aluminium.catalogue.MyApplication


/*****************************************************************
 * Method Name : showLog
 * Description : This method is used for showing logs
 ******************************************************************/
fun showLog(tag_en_utils: String, message: String) {
    Log.d(tag_en_utils, message)
}

/*****************************************************************
 * Method Name : showErrorLog
 * Description : This method is used for showing error logs
 ******************************************************************/
fun showErrorLog(tag_en_utils: String, message: String) {
    Log.e(tag_en_utils, message)
}

/*****************************************************************
 * Method Name : isInternetAvailable
 * Description : This method is used for checking internet connectivity
 ******************************************************************/
fun isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager =
        MyApplication.mInstance?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.let {
        it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                else -> false
            }
        }
    }
    return result
}
