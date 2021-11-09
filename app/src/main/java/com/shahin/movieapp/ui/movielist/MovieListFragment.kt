package com.shahin.movieapp.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.shahin.movieapp.model.MainViewState
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieListBinding
import com.shahin.movieapp.di.movielist.MovieListComponent
import com.shahin.movieapp.di.movielist.inject
import com.shahin.movieapp.intent.MainViewIntent
import com.shahin.movieapp.model.SingleEventObserver
import com.shahin.movieapp.ui.base.BaseFragment
import com.shahin.movieapp.ui.utils.marginItemDecoration
import javax.inject.Inject

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list),
    MovieListAdapter.MovieItemClickListener {

    @Inject
    lateinit var viewModel : MovieListViewModel

    lateinit var adapter: MovieListAdapter
    lateinit var sliderAdapter: SliderAdapter

    private lateinit var movieListComponent: MovieListComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        movieListComponent = inject()
        movieListComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendIntent()
        setupAdapter()
        setupRecycler()
        setupSlider()
        render()

    }

    private fun sendIntent() {
        lifecycleScope.launchWhenResumed {
            viewModel.mainIntent.send(MainViewIntent.GetList)
            viewModel.mainIntent.send(MainViewIntent.SetupWorkManager(requireContext().applicationContext))
        }
    }

    private fun setupAdapter() {
        adapter = MovieListAdapter(viewLifecycleOwner, this)
    }

    private fun setupRecycler() {
        binding.recyclerList.run {
            adapter = this@MovieListFragment.adapter
            addItemDecoration(marginItemDecoration(marginBottom = 4))
        }
    }

    private fun setupSlider() {
        sliderAdapter = SliderAdapter(viewLifecycleOwner)
        binding.apply {
            vpSlider.adapter = sliderAdapter
            indicatorSlider.setViewPager2(vpSlider)
            vpSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    vpSlider.currentItem = position
                }
            })
        }

    }

    private fun render() {
        viewModel.singleState.observe(viewLifecycleOwner,SingleEventObserver{
            when(it){
                is MainViewState.NavigateToDetail -> {
                    findNavController().navigate(
                        MovieListFragmentDirections.movieListToMovieDetail(
                            it.movieId
                        )
                    )
                }
            }
        })

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewState.Success -> {
                    adapter.submitList(it.data)
                    if (it.data.size > 5) {
                        sliderAdapter.submitList(it.data.subList(0, 5))
                        binding.indicatorSlider.setViewPager2(binding.vpSlider)
                        binding.indicatorSlider.refreshDots()
                    }
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