package com.stdio.effectivemobile.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.stdio.core.common.flow.observe
import com.stdio.effectivemobile.R
import com.stdio.effectivemobile.app.App
import com.stdio.effectivemobile.common.extensions.viewBinding
import com.stdio.effectivemobile.databinding.FragmentFavoritesBinding
import com.stdio.effectivemobile.di.FavoritesViewModelFactory
import com.stdio.effectivemobile.di.HomeViewModelFactory
import com.stdio.effectivemobile.ui.adapter.CoursesAdapter
import com.stdio.effectivemobile.ui.home.HomeViewModel
import javax.inject.Inject
import kotlin.getValue

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    @Inject
    @FavoritesViewModelFactory
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    private val adapter = CoursesAdapter(
        onFavoriteClick = { course ->
            viewModel.toggleFavorite(course)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        setupRecyclerView()
        viewModel.favorites.observe(viewLifecycleOwner, result = {
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        binding.rvFavorites.adapter = adapter
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
    }
}