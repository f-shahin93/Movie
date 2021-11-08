package com.shahin.movieapp.ui.movielist

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shahin.movieapp.model.MainViewState
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieListBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.intent.MainViewIntent
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.base.BaseFragment
import com.shahin.movieapp.ui.utils.marginItemDecoration
import javax.inject.Inject

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list),
    MovieListAdapter.MovieItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }

    lateinit var adapter: MovieListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendIntent()
        setupAdapter()
        setupRecycler()
        render()

    }

    private fun sendIntent() {
        lifecycleScope.launchWhenResumed {
            viewModel.mainIntent.send(MainViewIntent.GetList)
        }
    }

    private fun setupAdapter() {
        adapter = MovieListAdapter(viewLifecycleOwner, this)
    }

    private fun setupRecycler() {
        binding.list.run {
            adapter = this@MovieListFragment.adapter
            addItemDecoration(marginItemDecoration(marginBottom = 4))
        }
    }

    private fun render() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewState.NavigateToDetail -> {
                    findNavController().navigate(
                        MovieListFragmentDirections.movieListToMovieDetail(
                            it.movieId
                        )
                    )
                }
                is MainViewState.Success -> {
                    adapter.submitList(it.data)
                }
            }
        }
    }

    override fun onItemClicked(movieId: Long) {
        lifecycleScope.launchWhenResumed {
            viewModel.mainIntent.send(MainViewIntent.IsNavigateToDetail(movieId))
        }
    }

}