package com.example.kanbanbz.presentation.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kanbanbz.R
import com.example.kanbanbz.app.App
import com.example.kanbanbz.databinding.FragmentNewTaskBinding
import com.example.kanbanbz.presentation.viewmodels.NewTaskViewModel
import com.example.kanbanbz.utils.Factory
import kotlinx.coroutines.launch

class NewTaskFragment : Fragment() {
    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NewTaskViewModel> {
        Factory {
            App.appComponent.newTaskComponent().viewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.specificToolbars.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.button.setOnClickListener {
            val name = binding.newTaskNameTV.text.toString()
            if (name.isNotBlank()){
                viewModel.addNewTask(name)
                findNavController().navigate(
                    R.id.action_newTaskFragment_to_deskFragment
                )
            } else{
                Toast.makeText(
                    requireContext(),
                    getString(R.string.empty_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    state.success?.let {
                        Toast.makeText(
                            requireContext(),
                            getString(it),
                            Toast.LENGTH_SHORT
                        ).show()
                        state.error?.let {
                            Toast.makeText(
                                requireContext(),
                                getString(it),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}