package com.bangkit.tmdb.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.tmdb.core.data.Resource
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.ui.MoviesAdapter
import com.bangkit.tmdb.databinding.FragmentTvShowsBinding
import com.bangkit.tmdb.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class TvShowsFragment : Fragment() {

    private var fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = fragmentTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: TvShowsViewModel by viewModel()
    private lateinit var tvShowsAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowsAdapter = MoviesAdapter()
        setList()

        with(binding.rvTvShows) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }

        tvShowsAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
    }

    private fun setList() {
        viewModel.getTvShows().observe(this, tvShowsObserver)
    }

    private val tvShowsObserver = Observer<Resource<List<Movie>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                    tvShowsAdapter.setData(tvShow.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.notFoundText.visibility = View.VISIBLE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTvShowsBinding = null
    }

}