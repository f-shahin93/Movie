package com.shahin.movieapp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieDetailBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.base.BaseFragment
import com.shahin.movieapp.ui.detail.intent.MovieDetailViewIntent
import javax.inject.Inject

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MovieDetailViewModel> { viewModelFactory }

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendIntent()
        render()

    }

    private fun sendIntent() {
        lifecycleScope.launchWhenResumed {
            viewModel.mainIntent.send(MovieDetailViewIntent.GetDetail(args.movieId))
        }
    }

    private fun render() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MovieDetailViewState.Success -> {
                    binding.movie = it.data
                }
                is MovieDetailViewState.Error -> {

                }
                is MovieDetailViewState.IsLoading -> {

                }
            }
        }
    }


}