package com.extreme.zebra.util

import java.util.regex.Pattern

/****************************************************************
 * Validation.kt
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
 * Class Name - Validation
 * Description - For input validations.
 *********************************************************************/
object Validation {
    private val PHONE_NUMBER_PATTERN = Pattern.compile("^[0-9]+\$")

    private val EMAIL_ADDRESS_PATTERN =
        Pattern.compile("^[_a-zA-Z0-9]+(\\.[_a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,4})\$")

    private val PATTERN_IP_ADDRESS =
        Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])[.]){0,3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])?$")

    /*****************************************************************
     * Method Name : isEmailValid
     * Description : This method is used for validating email
     *
     * @param email
     ******************************************************************/
    fun isEmailValid(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    /*****************************************************************
     * Method Name : isEmailValid
     * Description : This method is used for validating phone number
     *
     * @param phoneNumber
     ******************************************************************/

    fun isPhoneNoValid(phoneNumber: String): Boolean {
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()
    }

    /*****************************************************************
     * Method Name : main
     * Description : This method is used for validating ipAddress
     ******************************************************************/
    fun isIpAddressValid(ipAddress: String): Boolean {
        return PATTERN_IP_ADDRESS.matcher(ipAddress).matches()
    }
}

