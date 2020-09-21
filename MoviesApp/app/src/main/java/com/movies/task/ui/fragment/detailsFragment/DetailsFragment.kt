package com.movies.task.ui.fragment.detailsFragment

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.movies.task.BuildConfig
import com.movies.task.R
import com.movies.task.common.*
import com.movies.task.data.model.detailsModel.Cast
import com.movies.task.data.model.detailsModel.RecommendedMovie
import com.movies.task.ui.MainActivity
import com.movies.task.ui.MoviesViewModel
import com.movies.task.ui.fragment.detailsFragment.adapters.CastsAdapter
import com.movies.task.ui.fragment.detailsFragment.adapters.RecommendedAdapter
import kotlinx.android.synthetic.main.details_toolbar.*
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.*


class DetailsFragment : Fragment(R.layout.fragment_details){

    lateinit var viewModel: MoviesViewModel
    private lateinit var navController: NavController
    private var castsAdapter: CastsAdapter? = null
    private var recommendedAdapter: RecommendedAdapter? = null
    private val castList = ArrayList<Cast>()
    private val recommendedMoviesList = ArrayList<RecommendedMovie>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        viewModel = (activity as MainActivity).viewModel

        backButton.setOnClickListener {
            navController.navigateUp()//navigateUp()
        }

        observeDetailsData()
        observeSessionData()
        observeRateData()
        observeRecommendedData()

        castsAdapter = CastsAdapter(castList)
        recommendedAdapter = RecommendedAdapter(recommendedMoviesList)

        actorsRecycler.adapter = castsAdapter
        recommendedMoviewRecycler.adapter = recommendedAdapter

        ratingBar.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                viewModel.getSessionToken(1)
            }
        }

        getMovieDetails()
        getRecommendedMovies()
    }

    private fun getMovieDetails() {
        viewModel.getDetailsData(viewModel.movie_id, 1)
    }

    private fun getRecommendedMovies(){
        viewModel.getRecommendedMovies(viewModel.movie_id)
    }

    private fun observeDetailsData() {

        viewModel.getDetailsData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Error -> {
                    DetailsProgressBar.gone()
                    it.msg?.let { msg -> showToast(msg) }
                }
                is Resource.Loading -> {
                    DetailsProgressBar.show()
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        DetailsProgressBar.gone()
                        castList.clear()
                        var image = BuildConfig.IMAGE_BASE_URL + it.data.poster_path
                        posterImage.loadImage(image)
                        releaseDateText.text = "Release Date : " + it.data.release_date
                        rateText.text = it.data.vote_average
                        detailsDescriptionValueTv.text = it.data.overview
                        detailsTitleText.text = it.data.title
                        for ( gener in it.data.genres!!)
                        genresText.text =  genresText.text.toString() + " , " + gener.name

                        castsAdapter.apply {
                            it.data.credits?.casts?.let { it1 -> castList.addAll(it1) }
                        }

                        castsAdapter?.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun observeRecommendedData() {

        viewModel.getRecommendedMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Error -> {
                    DetailsProgressBar.gone()
                    it.msg?.let { msg -> showToast(msg) }
                }
                is Resource.Loading -> {
                    DetailsProgressBar.show()
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        DetailsProgressBar.gone()
                        recommendedMoviesList.clear()

                        recommendedAdapter.apply {
                            it.data.results?.let { it1 -> recommendedMoviesList.addAll(it1) }
                        }

                        recommendedAdapter?.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun observeSessionData (){
        viewModel.getSession().observe(this.viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Error -> {
                    DetailsProgressBar.gone()
                    it.msg?.let { msg -> showToast(msg) }
                }
                is Resource.Loading -> {
                    DetailsProgressBar.show()
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        viewModel.setRate(it.data?.guest_session_id.toString(), ratingBar.numStars)
                    }
                }
            }
        })
    }

    private fun observeRateData (){
        viewModel.getRate().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Error -> {
                    DetailsProgressBar.gone()
                    it.msg?.let { msg -> showToast(msg) }
                }
                is Resource.Loading -> {
                    DetailsProgressBar.show()
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        if (it.data?.success!!){
                            DetailsProgressBar.gone()
                            showToast(it.data?.status_message.toString())
                        }
                    }
                }
            }
        })
    }
}