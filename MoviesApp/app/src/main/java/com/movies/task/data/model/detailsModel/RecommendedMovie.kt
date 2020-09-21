package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecommendedMovie(
    @SerializedName("title")
    val title: String?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("vote_average")
    val vote_average: Float?,
    @SerializedName("poster_path")
    val poster_path: String?

): Serializable