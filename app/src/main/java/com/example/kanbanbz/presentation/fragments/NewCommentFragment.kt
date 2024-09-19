package com.example.kanbanbz.presentation.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kanbanbz.app.App
import com.example.kanbanbz.databinding.FragmentNewCommentBinding
import com.example.kanbanbz.presentation.viewmodels.NewCommentViewModel
import com.example.kanbanbz.utils.Factory
import kotlinx.coroutines.launch

class NewCommentFragment : Fragment() {

    private var _binding: FragmentNewCommentBinding? = null
    private val binding get() = _binding!!
    private val id by lazy { requireArguments().getString("id") }
    private val viewModel by viewModels<NewCommentViewModel> {
        Factory {
            App.appComponent.newCommentComponent().create(requireNotNull(id)).viewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.specificToolbars.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveNewCommentButton.setOnClickListener {
            val text = binding.editTextTextMultiLine.text.toString()
            if (!id.isNullOrBlank() && text.isNotBlank()) {
                viewModel.addComment(id!!.toInt(), text)
                findNavController().popBackStack()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    state.success?.let { it ->
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