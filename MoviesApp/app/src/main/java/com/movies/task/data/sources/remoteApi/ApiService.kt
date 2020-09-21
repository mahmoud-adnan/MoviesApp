package com.movies.task.data.sources.remoteApi

import com.movies.task.data.model.detailsModel.DetailsResponse
import com.movies.task.data.model.detailsModel.Rate
import com.movies.task.data.model.detailsModel.RecommendedResult
import com.movies.task.data.model.detailsModel.Session
import com.movies.task.data.model.homeModel.Data
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") pageNumber: Int=1
                                     ) : Response<Data>


    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path(value = "movie_id", encoded = true) movie_id:String,
                                @Query("page") pageNumber: Int=1,
                                @Query("append_to_response") credits: String="credits"

                           ): Response<DetailsResponse>

    @GET("/3/authentication/guest_session/new")
    suspend fun getSessionToken(@Query("page") pageNumber: Int=1): Response<Session>

    @POST("/3/movie/{movie_id}/rating")
    suspend fun setRate(
                        @Path(value = "movie_id", encoded = true) movie_id:String,
                        @Query("guest_session_id") sessionId: String="",
                        @Body body: postBody) : Response<Rate>

    @GET("/3/movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(@Path(value = "movie_id", encoded = true) movie_id:String): Response<RecommendedResult>


}

data class postBody (
    @SerializedName("value") val value: Int?
)
