package com.shahin.movieapp.ui.movielist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.shahin.movieapp.model.MainViewState
import com.shahin.movieapp.R
import com.shahin.movieapp.databinding.FragmentMovieListBinding
import com.shahin.movieapp.di.ViewModelFactory
import com.shahin.movieapp.intent.MainViewIntent
import com.shahin.movieapp.model.SingleEventObserver
import com.shahin.movieapp.ui.MainActivity
import com.shahin.movieapp.ui.base.BaseFragment
import com.shahin.movieapp.ui.utils.marginItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list),
    MovieListAdapter.MovieItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }

    lateinit var adapter: MovieListAdapter
    lateinit var sliderAdapter: SliderAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubComponent.inject(this)
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
        binding.list.run {
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
        viewModel.singleState.observe(viewLifecycleOwner, SingleEventObserver {
            when (it) {
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
            setupProgressBar()
            when (it) {
                is MainViewState.Success -> {
                    adapter.submitList(it.data)
                    if (it.data.size > 5) {
                        sliderAdapter.submitList(it.data.subList(0, 5))
                        binding.indicatorSlider.setViewPager2(binding.vpSlider)
                        binding.indicatorSlider.refreshDots()
                    }
                }
                is MainViewState.Error -> {
                    // can retry req
                }
                is MainViewState.IsLoading -> {

                }
            }
        }
    }

    private fun setupProgressBar() {
        binding.progressBar.visibility = if (adapter.currentList.isNullOrEmpty()) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun onItemClicked(movieId: Long) {
        lifecycleScope.launchWhenResumed {
            viewModel.mainIntent.send(MainViewIntent.IsNavigateToDetail(movieId))
        }
    }

}