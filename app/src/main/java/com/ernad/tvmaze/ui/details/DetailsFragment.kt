package com.ernad.tvmaze.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.ernad.tvmaze.R
import com.ernad.tvmaze.databinding.FragmentDetailsBinding
import com.ernad.tvmaze.databinding.FragmentListBinding




class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel

        viewModel.getMovieDetails(args.movieId)

        viewModel.movieItem.observe(viewLifecycleOwner, {
            if(it != null){
                binding.rowImage.load(it.image!!.medium)
                binding.rowName.text = it.name
                binding.rowSummary.text = it.summary
                binding.textPremiereStart.text = it.premiered
                binding.textPremiereEnd.text = it.ended
                binding.textUrl.text = it.url
            }
        })





        return binding.root

    }




}