package com.extreme.zebra.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.extreme.zebra.BuildConfig.IS_DEBUGGING
import com.extreme.zebra.NewsAppApplication
import com.extreme.zebra.R
import com.google.android.material.snackbar.Snackbar


/****************************************************************
 * ExtremeUtils.kt
 * Created on Dec 28, 2020
 * Copyright (c) 2020, VVDN Technologies Pvt Ltd.
 * B-22, Info City-1, Sec-34, Gurgaon, Haryana.
 * All rights reserved.
 * This software is the confidential and proprietary information of
 * VVDN ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with VVDN.
 *
 * @author VVDN
 * Class Name - DeltaUtils
 * Description - Utils of this application use static methods of application.
 ****************************************************************/

/*****************************************************************
 * Method Name : toast
 * Description : This method is used for showing toast
 ******************************************************************/
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/*****************************************************************
 * Method Name : showLog
 * Description : This method is used for showing logs
 ******************************************************************/
fun showLog(tag_en_utils: String, message: String) {
    if (IS_DEBUGGING.toBoolean())
        Log.d(tag_en_utils, message)
}

/*****************************************************************
 * Method Name : showErrorLog
 * Description : This method is used for showing error logs
 ******************************************************************/
fun showErrorLog(tag_en_utils: String, message: String) {
    if (IS_DEBUGGING.toBoolean())
        Log.e(tag_en_utils, message)
}

var mCustomProgressDialog: Dialog? = null

/*****************************************************************
 * Method Name : showProgressBar
 * Description : This method is used for showing progress bar
 ******************************************************************/
fun showProgressDialog(
    mActivity: Activity?,
    msg: String?, isCancelable: Boolean
) {
    try {
        if (mActivity != null && mCustomProgressDialog != null && mCustomProgressDialog?.isShowing!! && !mActivity.isFinishing) {
            mCustomProgressDialog?.dismiss()
            mCustomProgressDialog = null
        }
        mCustomProgressDialog = null
        if (mActivity != null && !mActivity.isFinishing) {
            mActivity.runOnUiThread(Runnable {
                try {
                    mCustomProgressDialog = getCustomProgressDialog(mActivity, msg, isCancelable)
                    if ((!mActivity.isFinishing) && (mCustomProgressDialog != null) && !mCustomProgressDialog?.isShowing!!) {
                        mCustomProgressDialog?.show()
                        showLog(tag_en_utils, " Displaying  mCustomProgressDialog")
                    }
                } catch (e: Exception) {
                    showErrorLog(tag_en_utils, e.message.toString())
                }
            })
        } else showLog(
            tag_en_utils,
            " Progress Dialog Already registered"
        )
        if ((mActivity != null) && (mCustomProgressDialog != null) && (!(mCustomProgressDialog?.isShowing!!)) && (!mActivity.isFinishing)) {
            mCustomProgressDialog?.show()
        }
    } catch (e: Exception) {
        showErrorLog(tag_en_utils, e.message.toString())
    }
}

const val tag_en_utils = "AppUtils"

/*****************************************************************
 * Method Name : hideProgressBar
 * Description : This method is used for hiding progress bar
 ******************************************************************/
fun hideProgressDialog(): Unit {
    if (mCustomProgressDialog != null && mCustomProgressDialog?.isShowing!!) {
        try {
            mCustomProgressDialog?.dismiss()
            mCustomProgressDialog = null
        } catch (e: java.lang.Exception) {
            showErrorLog(tag_en_utils, e.message.toString())
        }
    }
}

/**
 * Method Name : getCustomProgressDialog
 * Description : method used to create custom progress dialog
 *
 * @param mActivity    : Instance of current Activity.    context
 * @param message      msg to show
 * @param isCancelable cancelable
 * @return dialog instance
 */
fun getCustomProgressDialog(
    mActivity: Activity,
    message: String?,
    isCancelable: Boolean
): Dialog {
    val progressDialog = Dialog(mActivity)
    progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    progressDialog.setContentView(R.layout.custom_progress_dialog)
    progressDialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT
    )
    progressDialog.window?.setGravity(Gravity.CENTER)
    progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val messageTV = progressDialog.findViewById<TextView>(R.id.message)
    messageTV.text = message
    progressDialog.setCancelable(isCancelable)
    return progressDialog
}

/*****************************************************************
 * Method Name : snackbar
 * Description : This method is used showing snackbar
 ******************************************************************/
fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

/*****************************************************************
 * Method Name : isInternetAvailable
 * Description : This method is used for checking internet connectivity
 ******************************************************************/
fun isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager =
        NewsAppApplication.mInstance?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
    }
    return result
}
