package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geners(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
): Serializable