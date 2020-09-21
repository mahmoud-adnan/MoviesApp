package com.movies.task.ui.fragment.homeFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.movies.task.R
import com.movies.task.common.Resource
import com.movies.task.common.gone
import com.movies.task.common.show
import com.movies.task.common.showToast
import com.movies.task.data.model.homeModel.Results
import com.movies.task.ui.MainActivity
import com.movies.task.ui.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), HomeAdapter.Interaction {

    private val homeAdapter by lazy { HomeAdapter(this) }

    private var isLoading = false

    private lateinit var navController:NavController
    lateinit var viewModel: MoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        navController=Navigation.findNavController(view)

        setupRecyclerView()
        observeHomeData()
    }

    private fun setupRecyclerView() {

        homeCharactersRecycler.apply {
            adapter=homeAdapter
        }
    }

    private fun observeHomeData() {

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Error -> {
                    ProgressBar.gone()
                   isLoading=false
                    it.msg?.let { msg -> showToast(msg) }
                }
                is Resource.Loading -> {
                    ProgressBar.show()
                    isLoading=true
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        ProgressBar.gone()
                        isLoading=false
                        it.data.results?.let { it1 ->
                            it1.sortByDescending  { item -> item.vote_average }
                            homeAdapter.submitList(it1)
                        }
                    }
                }
            }
        })
    }

    override fun onItemSelected(position: Int, item: Results) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
        viewModel.movie_id = item.id.toString()
        findNavController().navigate(action)
    }
}