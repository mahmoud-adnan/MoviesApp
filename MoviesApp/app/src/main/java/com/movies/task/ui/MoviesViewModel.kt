package com.movies.task.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.task.common.Resource
import com.movies.task.data.MoviesRepository
import com.movies.task.data.model.detailsModel.DetailsResponse
import com.movies.task.data.model.detailsModel.Rate
import com.movies.task.data.model.detailsModel.RecommendedResult
import com.movies.task.data.model.detailsModel.Session
import com.movies.task.data.model.homeModel.Data
import com.movies.task.data.sources.remoteApi.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class MoviesViewModel   @ViewModelInject constructor (private val moviesRepository: MoviesRepository, private val service: ApiService) : ViewModel() {

    private var homeData = MutableLiveData<Resource<Data>>()
    private var detailsData = MutableLiveData<Resource<DetailsResponse>>()
    private var sessionData =  MutableLiveData<Resource<Session>>()
    private var rateData = MutableLiveData<Resource<Rate>>()
    private var recommendedData = MutableLiveData<Resource<RecommendedResult>>()

    private var homeResponse: Data? = null
    private var detailsResponse: DetailsResponse? = null
    private var sessionResponse: Session? = null
    private var rateResponse: Rate? = null
    private var recommendedResponse: RecommendedResult? = null

    var movie_id= ""

    init {
        getHomeData()
    }

    // get data for home page
     fun getHomeData() {
        homeData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val result = moviesRepository.getHomeCharacters(1)
            homeData.postValue(handleHomeData(result))
        }
    }

    // get data for movie details
    fun getDetailsData(movieId : String, pageNumber : Int){
        detailsData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            var result = moviesRepository.getMovieDetails(movieId, pageNumber)
            detailsData.postValue(handleDetailsData((result)))
        }
    }

    // get user session
    fun getSessionToken(pageNumber : Int){
        sessionData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            var result = moviesRepository.getSessionToken(1)
            sessionData.postValue(handleSessionData(result))
        }
    }

    // set movie rate
    fun setRate(sessionId : String, starsCount: Int){
        rateData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            var result = moviesRepository.setRate(sessionId, starsCount)
            rateData.postValue(handleSettingRate(result))
        }
    }

    // get recommended movies
    fun getRecommendedMovies(movieId : String){
        recommendedData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            var result = moviesRepository.getRecommendedMovies(movieId)
            recommendedData.postValue(handleRecommendedMovies((result)))
        }
    }

    // init live data
    fun getData() : LiveData<Resource<Data>> = homeData
    fun getDetailsData () : LiveData<Resource<DetailsResponse>> = detailsData
    fun getSession () : LiveData<Resource<Session>> = sessionData
    fun getRate () : LiveData<Resource<Rate>> = rateData
    fun getRecommendedMovies () : LiveData<Resource<RecommendedResult>> = recommendedData


    // response handlers
    // handle response for details
    private fun handleDetailsData(response: Response<DetailsResponse>) : Resource<DetailsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                detailsResponse = null
                if(detailsResponse == null) {
                    detailsResponse = resultResponse

                } else {

                }
                return Resource.Success(detailsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // handle response for session
    private fun handleSessionData(response: Response<Session>) : Resource<Session> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                sessionResponse = null
                if(sessionResponse == null) {
                    sessionResponse = resultResponse

                } else {

                }
                return Resource.Success(sessionResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // handle setting movie rate
    private fun handleSettingRate(response: Response<Rate>) : Resource<Rate> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                rateResponse = null
                if(rateResponse == null) {
                    rateResponse = resultResponse

                } else {

                }
                return Resource.Success(rateResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // handle recommended movies
    private fun handleRecommendedMovies(response: Response<RecommendedResult>) : Resource<RecommendedResult> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                recommendedResponse = null
                if(recommendedResponse == null) {
                    recommendedResponse = resultResponse

                } else {

                }
                return Resource.Success(recommendedResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // handle response for home page
    private fun handleHomeData(response: Response<Data>) : Resource<Data> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                homeResponse = null
                if(homeResponse == null) {
                    homeResponse = resultResponse
                } else {

                }
                return Resource.Success(homeResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
