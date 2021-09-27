package com.example.coroutines.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coroutines.R
import com.example.coroutines.adapter.AdapterRepository
import com.example.coroutines.databinding.MainFragmentBinding
import com.example.coroutines.model.Repository
import com.example.coroutines.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = AdapterRepository()
    private val LANGUAGE = "Kotlin"

    private val observerGitRepo = Observer<List<Repository>> {
        adapter.refesh(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.bind(view)

        binding.recyclerViewRepo.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewRepo.adapter = adapter

        viewModel.gitRepo.observe(viewLifecycleOwner, observerGitRepo)

        viewModel.getRepositories(LANGUAGE)
        viewModel.getUsers()


        val listOf = CoroutineScope(Dispatchers.Main).async {
            namesOnScreen()
        }

        CoroutineScope(Dispatchers.Main).launch {
            val registros = listOf.await()
            binding.message.text = "Quantidade de nomes ${registros}"
        }

    }

    suspend fun namesOnScreen(): Int {
        val listOfNames = listOf(
            "Jose",
            "Lucas",
            "Pedro",
            "Marcos",
            "Mateus",
            "Lucas",
            "Pedro",
            "Marcos",
            "Pedro",
            "Marcos",
            "Pedro",
            "Marcos",
            "Pedro",
            "Marcos",
            "Pedro",
            "Marcos",
            "Pedro",
            "Marcos",
            "Pedro",
            "Marcos",
        )
        listOfNames.forEach {
            binding.message.text = it
            delay(1000)
        }
        return listOfNames.size
    }

}