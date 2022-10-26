package com.vichsu.midterm.addpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vichsu.midterm.data.Author
import com.vichsu.midterm.databinding.FragmentAddArticleBinding

class AddPageDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddArticleBinding.inflate(inflater,container,false)
        val viewModel = ViewModelProvider(this).get(AddPageViewModel::class.java)

        binding.addArticleButton.setOnClickListener {
            val author1 = Author("wayne@school.appworks.tw","waynechen323","AKA小安老師")
            val author2 = Author("vichsu@gmaiil.com","vic123","許")
            viewModel.addData(author1)
        }

        binding.textTitle.doAfterTextChanged {
            viewModel.setTitle(it.toString())
        }

        binding.textCategory.doAfterTextChanged {
            viewModel.setCategory(it.toString())
        }

        binding.textContent.doAfterTextChanged {
            viewModel.setContent(it.toString())
        }

        viewModel.navigationUp.observe(viewLifecycleOwner, Observer {
            if (it == true){
                viewModel.doneNavigationUp()
                findNavController().navigateUp()

            }
        })

        viewModel.userRegistered.observe(viewLifecycleOwner, Observer {
            if (it == false){
                Toast.makeText(requireContext(),"User Error",Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        })









        return binding.root
    }
}