package com.movies.task.data


import com.movies.task.data.model.homeModel.Data
import com.movies.task.data.sources.remoteApi.ApiService
import com.movies.task.data.sources.remoteApi.postBody
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val service: ApiService ) {

    suspend fun getHomeCharacters(page: Int): Response<Data> =
        service.getNowPlayingMovies(pageNumber = 1)

    suspend fun getMovieDetails(movieId: String, pageNumber: Int)  =
        service.getMovieDetails(movieId, pageNumber)

    suspend fun getSessionToken(pageNumber: Int)  =
        service.getSessionToken(pageNumber)

    suspend fun setRate(sessionId: String, starsCount: Int)  =
        service.setRate(sessionId, body = postBody(value = starsCount))

    suspend fun getRecommendedMovies(movieId: String)  =
        service.getRecommendedMovies(movieId)

}