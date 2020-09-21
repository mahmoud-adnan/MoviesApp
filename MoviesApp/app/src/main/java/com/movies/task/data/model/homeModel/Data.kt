package com.movies.task.data.model.homeModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results")
    val results: MutableList<Results>?=null
)