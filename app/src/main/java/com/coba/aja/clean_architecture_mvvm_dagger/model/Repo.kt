package com.coba.aja.clean_architecture_mvvm_dagger.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("owner") val owner : User,
    @SerializedName("stargazers_count") val stars : Long,
    @SerializedName("forks_count") val forks_count : Long
)