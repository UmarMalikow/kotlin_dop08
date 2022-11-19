package com.example.kotlin_dop8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin_dop8.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment(), OnItemClickListener {

    private var binding: FragmentRecyclerBinding? = null
    private val repository = AddRepository()
    private val adapter = AddAdapter(this)
    private var model: AddModel? = null
    private var list: ArrayList<AddModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = repository.getListOfCelebrities()
        adapter.setData(list)
        initialize()
        setOnClickListener()
        addData()
    }

    private fun initialize() {
        binding?.rvListOfName?.adapter = adapter
    }

    private fun setOnClickListener() {
        binding?.btnAdd?.setOnClickListener {
            findNavController().navigate(R.id.action_recyclerFragment_to_thirdFragment)
        }
    }

    override fun onClick(model: AddModel?) {
        val bundle = Bundle()
        bundle.putSerializable("key", model)
        findNavController().navigate(R.id.action_recyclerFragment_to_detailFragment, bundle)
            .toString()
    }

    private fun addData() {
        arguments.let {
            adapter.setData2(it?.getSerializable("OK") as AddModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}