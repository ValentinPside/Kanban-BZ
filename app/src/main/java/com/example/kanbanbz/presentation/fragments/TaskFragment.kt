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
import com.example.kanbanbz.databinding.FragmentTaskBinding
import com.example.kanbanbz.presentation.viewmodels.TaskViewModel

import com.example.kanbanbz.utils.Factory
import kotlinx.coroutines.launch

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private val id by lazy { requireArguments().getString("id") }
    private val viewModel by viewModels<TaskViewModel> {
        Factory {
            App.appComponent.taskComponent().create(requireNotNull(id)).viewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.specificToolbars.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.action_taskFragment_to_deskFragment
            )
        }

        binding.addCommentButton.setOnClickListener {
            val bundle = bundleOf("id" to id)
            findNavController().navigate(
                R.id.action_taskFragment_to_newCommentFragment, bundle
            )
        }

        binding.changeStatusButton.setOnClickListener {
            val bundle = bundleOf("id" to id)
            findNavController().navigate(
                R.id.action_taskFragment_to_newStatusFragment, bundle
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    binding.specificToolbars.title = state.taskName
                    binding.commentsText.text = state.comments
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}