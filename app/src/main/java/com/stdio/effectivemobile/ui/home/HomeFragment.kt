package com.stdio.effectivemobile.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData
import com.stdio.effectivemobile.R
import com.stdio.effectivemobile.app.App
import com.stdio.effectivemobile.common.extensions.viewBinding
import com.stdio.effectivemobile.databinding.FragmentHomeBinding
import com.stdio.effectivemobile.ui.adapter.CoursesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val adapter = CoursesAdapter(
        onFavoriteClick = { courseId, isFavorite ->
            //viewModel.toggleFavorite(courseId, isFavorite)
        }
    )

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        setupRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it.courses) {
                    is LoadableData.Error -> {}
                    is LoadableData.Success<List<Course>> -> adapter.submitList(it.courses.data)
                    else -> {}
                }
                binding.progressBar.isVisible = it.courses is LoadableData.Loading
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCourses.adapter = adapter
        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
    }

}