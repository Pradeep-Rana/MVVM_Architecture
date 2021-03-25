package com.extreme.zebra.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.extreme.zebra.R
import com.extreme.zebra.`interface`.ChoiceDialogClickListener


/****************************************************************
 * CustomDialogUtils.kt
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
 * Class Name - CustomDialogUtils
 * Description - This class is used for showing mCustomProgressDialog on success or failure of any operation performed with in the application.
 *********************************************************************/

/***************************************************************************************
 * Function Name :twoButtonsCustomAlertDialog
 * Description : This function will show custom alert dialog box.
 *
 * @param mActivity                    : Activity instance.
 * @param title                        : title
 * @param isToShowTitle                     : button text.
 * @param descriptionText              : description.
 * @param mChoiceDialogClickListener   : listener
 */
fun twoButtonsWithIconCustomAlertDialog(
    mActivity: Activity?,
    title: String?,
    isToShowTitle: Boolean?,
    msgNoBtn: String?,
    msgYesBtn: String?,
    descriptionText: String,
    iconFor: String?,
    mChoiceDialogClickListener: ChoiceDialogClickListener?
) {
    try {
        if (mActivity != null && !mActivity.isFinishing) {
            val dialog = Dialog(mActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_alert_dialog)
            val twoButtonContainerView: LinearLayout? =
                dialog.findViewById(R.id.twoButtonContainerView)
            val btnNo: TextView? = dialog.findViewById(R.id.btnNo)
            val mTvTitle: TextView? = dialog.findViewById(R.id.mTvTitle)
            val mTvDescriptions: TextView? = dialog.findViewById(R.id.mTvDescriptions)
            val btnYes: TextView? = dialog.findViewById(R.id.btnYes)
            val btnOk: TextView? = dialog.findViewById(R.id.btnOk)
            btnOk?.visibility = View.GONE
            btnYes?.text = msgYesBtn
            btnNo?.text = msgNoBtn
            mTvTitle?.text = title
            mTvDescriptions?.text = descriptionText
            if (isToShowTitle!!) {
                mTvTitle?.visibility = View.VISIBLE
            } else {
                mTvTitle?.visibility = View.GONE
            }
            btnNo?.setOnClickListener {
                mChoiceDialogClickListener?.onClickOfNegative()
                dialog.dismiss()
            }

            btnYes?.setOnClickListener {
                mChoiceDialogClickListener?.onClickOfPositive()
                dialog.dismiss()
            }

            btnOk?.visibility = View.GONE
            twoButtonContainerView?.visibility = View.VISIBLE

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            if (!mActivity.isFinishing && !dialog.isShowing) dialog.show()
        }
    } catch (exc: Exception) {
        showLog(tag_en_utils, "Error in showing popup: ${exc.message}")
    }
}