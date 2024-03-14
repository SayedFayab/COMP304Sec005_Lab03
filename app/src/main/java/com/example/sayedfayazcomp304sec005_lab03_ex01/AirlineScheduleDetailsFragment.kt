package com.example.sayedfayazcomp304sec005_lab03_ex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayedfayazcomp304sec005_lab03_ex01.databinding.FragmentAirListBinding
import com.example.sayedfayazcomp304sec005_lab03_ex01.databinding.FragmentAirlineScheduleDetailsBinding
import com.example.sayedfayazcomp304sec005_lab03_ex01.viewmodels.AirlineViewModel
import com.example.sayedfayazcomp304sec005_lab03_ex01.viewmodels.AirlineViewModelFactory
import kotlinx.coroutines.launch


class AirlineScheduleDetailsFragment: Fragment() {

    companion object {
        var AIRLINE_NAME = "airlineName"
    }

    private var _binding: FragmentAirlineScheduleDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var airlineName: String

    private val viewModel: AirlineViewModel by activityViewModels {
        AirlineViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            airlineName = it.getString(AIRLINE_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAirlineScheduleDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airlineListAdapter = AirlineListAdapter({})
        recyclerView.adapter = airlineListAdapter
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForAirlineName(airlineName).collect() {
                airlineListAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


