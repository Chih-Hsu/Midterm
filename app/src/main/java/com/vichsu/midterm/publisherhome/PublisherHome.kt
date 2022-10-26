package com.vichsu.midterm.publisherhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vichsu.midterm.databinding.FragmentPublisherHomeBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class PublisherHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPublisherHomeBinding.inflate(inflater,container,false)
        val viewModel = ViewModelProvider(this).get(PublisherViewModel::class.java)

        val adapter = PublisherAdapter()
        binding.articleList.adapter = adapter

        viewModel.article.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.addArticle.setOnClickListener {
            this.findNavController().navigate(PublisherHomeDirections.actionPublisherHomeToAddPageDialog())
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }




        return binding.root
    }
}