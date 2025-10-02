package com.stdio.effectivemobile.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.stdio.core.common.flow.observe
import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData
import com.stdio.effectivemobile.R
import com.stdio.effectivemobile.app.App
import com.stdio.effectivemobile.common.extensions.viewBinding
import com.stdio.effectivemobile.databinding.FragmentHomeBinding
import com.stdio.effectivemobile.di.HomeViewModelFactory
import com.stdio.effectivemobile.model.CoursesUIState
import com.stdio.effectivemobile.ui.adapter.CoursesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    @HomeViewModelFactory
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private val adapter = CoursesAdapter(
        onFavoriteClick = { course ->
            viewModel.toggleFavorite(course)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        setupRecyclerView()
        viewModel.uiState.observe(viewLifecycleOwner, result = this::updateView)
        viewModel.errorFlow.observe(viewLifecycleOwner, result = {
            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCourses()
    }

    private fun updateView(uiState: CoursesUIState) {
        when (uiState.courses) {
            is LoadableData.Success<List<Course>> -> adapter.submitList(uiState.courses.data)
            else -> {}
        }
        binding.progressBar.isVisible = uiState.courses is LoadableData.Loading
    }

    private fun setupRecyclerView() {
        binding.rvCourses.adapter = adapter
        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
    }

}