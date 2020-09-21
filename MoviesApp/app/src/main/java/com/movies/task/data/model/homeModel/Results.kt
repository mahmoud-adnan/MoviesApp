package com.movies.task.data.model.homeModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Results(
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("id")
    val id: String?=null,
    @SerializedName("vote_average")
    val vote_average: String?=null,
    @SerializedName("release_date")
    val release_date: String?=null,
    @SerializedName("poster_path")
    val poster_path: String?=null
):Serializable