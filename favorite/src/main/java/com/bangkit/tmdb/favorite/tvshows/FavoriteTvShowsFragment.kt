package com.bangkit.tmdb.favorite.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.tmdb.core.domain.model.Movie
import com.bangkit.tmdb.core.ui.MoviesAdapter
import com.bangkit.tmdb.detail.DetailActivity
import com.bangkit.tmdb.favorite.FavoriteViewModel
import com.bangkit.tmdb.favorite.R
import com.bangkit.tmdb.favorite.databinding.FragmentFavoriteTvShowsBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : Fragment() {

    private var _fragmentFavoriteTvShowsBinding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _fragmentFavoriteTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavoriteTvShowsBinding =
            FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var tvShowsAdapter: MoviesAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvFavoriteTvshows)

        tvShowsAdapter = MoviesAdapter()

        binding.progressBar.visibility = View.VISIBLE
        binding.notFoundText.visibility = View.GONE
        setList()

        with(binding.rvFavoriteTvshows) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }

        tvShowsAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = tvShowsAdapter.getSwipedData(swipedPosition)
                var state = tvShowEntity.favorite
                viewModel.setFavorite(tvShowEntity, !state)
                state = !state
                val snackBar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_ok) {
                    viewModel.setFavorite(tvShowEntity, !state)
                }
                snackBar.show()
            }
        }
    })

    private fun setList() {
        viewModel.getFavoriteTvShows().observe(this, tvShowsObserver)
    }

    private val tvShowsObserver = Observer<List<Movie>> { tvShows ->
        if (tvShows.isNullOrEmpty()){
            binding.progressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.notFoundText.visibility = View.GONE
        }
        tvShowsAdapter.setData(tvShows)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteTvShowsBinding = null
    }
}