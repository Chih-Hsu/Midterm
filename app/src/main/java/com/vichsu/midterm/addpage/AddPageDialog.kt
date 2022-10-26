package com.vichsu.midterm.addpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vichsu.midterm.databinding.FragmentAddArticleBinding

class AddPageDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddArticleBinding.inflate(inflater,container,false)






        return binding.root
    }
}