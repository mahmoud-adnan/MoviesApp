package com.movies.task.data.model.detailsModel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rate(
    @SerializedName("status_message")
    val status_message: String?,
    @SerializedName("success")
    val success: Boolean?

): Serializable