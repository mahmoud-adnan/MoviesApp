package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Casts(
    @SerializedName("cast")
    val cast: ArrayList<Cast>?
): Serializable