package com.coba.aja.clean_architecture_mvvm_dagger.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login") val login : String
    )