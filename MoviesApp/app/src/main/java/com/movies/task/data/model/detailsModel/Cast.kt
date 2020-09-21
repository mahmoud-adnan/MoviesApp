package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cast(
    @SerializedName("cast_id")
    val cast_id: Int?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    val profile_path: String?

): Serializable