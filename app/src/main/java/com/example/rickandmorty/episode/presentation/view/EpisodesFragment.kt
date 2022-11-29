package com.example.rickandmorty.episode.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.episode.domain.model.Episodes
import com.example.rickandmorty.episode.presentation.viewmodel.EpisodesViewModel
import com.example.rickandmorty.main.presentation.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    @Inject
    lateinit var episodesViewModel: EpisodesViewModel
    private var episodesBinding: FragmentEpisodesBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodesBinding.inflate(inflater, container, false).also {
        episodesBinding = it
        initViews()
    }.root


    override fun onDestroyView() {
        super.onDestroyView()
        episodesBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                episodesViewModel.episodes.collect { ep ->
                    if (ep != null) {
                        loadEpisodes(data = ep)
                    }
                }
            }
        }
    }

    private fun loadEpisodes(data: Episodes) {
        (episodesBinding?.rvEpisodesData?.adapter as? EpisodesAdapter)?.updateData(newData = data.results)
    }

    private fun initViews() {
        with(episodesBinding?.rvEpisodesData) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = EpisodesAdapter()
        }
    }

    companion object {
        fun newInstance(): EpisodesFragment = EpisodesFragment().apply { arguments = Bundle() }
    }

}
