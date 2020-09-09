package com.coba.aja.clean_architecture_mvvm_dagger.app

import android.content.Context
import android.content.SharedPreferences

class AppPreference(private val context: Context) {
    private val pref : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private val PRIVATE_MODE : Int = 0
    private val PREFF_NAME : String = "cobaaja"

    init {
        pref = context.getSharedPreferences(PREFF_NAME, PRIVATE_MODE)
    }


}