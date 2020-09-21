package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecommendedResult(
    @SerializedName("results")
    val results: List<RecommendedMovie>?

): Serializable