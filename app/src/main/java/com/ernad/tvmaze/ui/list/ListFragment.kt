package com.ernad.tvmaze.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ernad.tvmaze.databinding.FragmentListBinding


class FragmentList : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.listRecyclerView.adapter = TvMazeListAdapter(viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner

        setupNavigation()
    }

    private fun setupNavigation() {

        viewModel.openTaskEvent.observe(viewLifecycleOwner, {

            if(it != null) {
                openMovieDetails(it)
            }
        })
    }


    private fun openMovieDetails(movieId: Int) {
        val action = FragmentListDirections.actionFragmentListToDetailsFragment2(movieId)
        findNavController().navigate(action)
    }


}