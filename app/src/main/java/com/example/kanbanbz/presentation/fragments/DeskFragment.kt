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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kanbanbz.R
import com.example.kanbanbz.app.App
import com.example.kanbanbz.databinding.FragmentDeskBinding
import com.example.kanbanbz.presentation.Adapter
import com.example.kanbanbz.presentation.viewmodels.DeskViewModel
import com.example.kanbanbz.utils.Factory
import kotlinx.coroutines.launch

class DeskFragment : Fragment() {

    private var _binding: FragmentDeskBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DeskViewModel> {
        Factory {
            App.appComponent.deskComponent().viewModel()
        }
    }

    private lateinit var startAdapter: Adapter
    private lateinit var inProgressAdapter: Adapter
    private lateinit var doneAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers()
        binding.addNewTaskImg.setOnClickListener {
            findNavController().navigate(
                R.id.action_deskFragment_to_newTaskFragment
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    startAdapter.submitList(state.startList)
                    inProgressAdapter.submitList(state.inProgressList)
                    doneAdapter.submitList(state.doneList)
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

    private fun setupRecyclers() {
        startAdapter = Adapter { id ->
            findNavController().navigate(
                R.id.action_deskFragment_to_taskFragment,
                bundleOf("id" to id)
            )
        }
        inProgressAdapter = Adapter { id ->
            findNavController().navigate(
                R.id.action_deskFragment_to_taskFragment,
                bundleOf("id" to id)
            )
        }
        doneAdapter = Adapter { id ->
            findNavController().navigate(
                R.id.action_deskFragment_to_taskFragment,
                bundleOf("id" to id)
            )
        }
        binding.startRv.adapter = startAdapter
        binding.inProgressRv.adapter = inProgressAdapter
        binding.doneRv.adapter = doneAdapter
        binding.startRv.layoutManager = LinearLayoutManager(requireContext())
        binding.inProgressRv.layoutManager = LinearLayoutManager(requireContext())
        binding.doneRv.layoutManager = LinearLayoutManager(requireContext())
        binding.startRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.inProgressRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.doneRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.HORIZONTAL
            )
        )
        binding.startRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding.inProgressRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding.doneRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}