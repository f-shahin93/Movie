package com.shahin.movieapp.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieListBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.base.BaseFragment
import javax.inject.Inject

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}