package com.shahin.movieapp.ui.detail

import android.content.Context
import androidx.fragment.app.viewModels
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieDetailBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.base.BaseFragment
import javax.inject.Inject

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MovieDetailViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }


}