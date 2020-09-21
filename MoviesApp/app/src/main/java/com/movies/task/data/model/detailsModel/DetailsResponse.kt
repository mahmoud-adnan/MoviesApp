package com.movies.task.data.model.detailsModel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DetailsResponse(
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("vote_average")
    val vote_average: String?,
    @SerializedName("genres")
    val genres: List<Geners>?,
    @SerializedName("credits")
    val credits: Credits?
): Serializable