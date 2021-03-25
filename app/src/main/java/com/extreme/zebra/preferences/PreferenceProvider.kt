package com.extreme.zebra.preferences

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.extreme.zebra.NewsAppApplication


object PreferenceProvider {

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(
            NewsAppApplication.mInstance?.applicationContext
        )
    private val editor: SharedPreferences.Editor
        get() = preference.edit()

    /*****************************************************************
     * Method Name : setLoginStatus
     * Description : This method is used for setting the login status
     *
     * @loginStatus
     ******************************************************************/
    /*fun setLoginStatus(loginStatus: Boolean) {
        editor.putBoolean(
            LOGIN_STATUS,
            loginStatus
        ).apply()
    }*/

    /*****************************************************************
     * Method Name : isLoggedIn
     * Description : This method is used for checking whether the user is logged in or not
     ******************************************************************/
    /*fun isLoggedIn(): Boolean {
        return preference.getBoolean(LOGIN_STATUS, false)
    }*/

    /*****************************************************************
     * Method Name : setClientToken
     * Description : This method is used for setting the token.
     ******************************************************************/
    /*fun setClientToken(token: String) {
        editor.putString(CLIENT_TOKEN, token).apply()
    }*/

    /*****************************************************************
     * Method Name : getClientToken
     * Description : This method is used for getting client token.
     ******************************************************************/
    /*fun getClientToken(): String {
        return preference.getString(CLIENT_TOKEN, "").toString()
    }*/
}