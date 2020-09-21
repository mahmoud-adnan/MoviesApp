package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Credits(
    @SerializedName("cast")
    val casts: List<Cast>?
): Serializable