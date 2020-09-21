package com.movies.task.data.model.detailsModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Session(
    @SerializedName("guest_session_id")
    val guest_session_id: String?

): Serializable