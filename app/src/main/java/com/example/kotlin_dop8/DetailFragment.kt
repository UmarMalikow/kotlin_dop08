package com.example.kotlin_dop8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kotlin_dop8.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private var model: AddModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data
        setOnClick()
    }

    private fun setOnClick() {
        binding?.bntBackStack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private val data: Unit
        private get() {
            val argument = arguments
            if (argument != null) {
                model = argument.getSerializable("key") as AddModel?
                binding?.ivFullscreen?.let {
                    it.context?.let { it1 ->
                        Glide.with(it1).load(model?.imageUrl).into(binding?.ivFullscreen!!)
                    }
                }
                binding?.tvNamePerson?.text = model?.name
                binding?.tvFamilyPerson?.text = model?.family
                binding?.tvAgePerson?.text = model?.age.toString()
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}