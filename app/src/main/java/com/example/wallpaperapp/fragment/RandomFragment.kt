package com.example.wallpaperapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.WallpaperAdapter
import com.example.wallpaperapp.database.Image
import com.example.wallpaperapp.databinding.FragmentRandomBinding
import com.example.wallpaperapp.util.ObjectList
import java.util.*


class RandomFragment : Fragment() {
    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!
    private val randomAdapter by lazy { WallpaperAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomAdapter.imageList = ObjectList.wallpaperList
        binding.rvRandom.apply {
            adapter = randomAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
//            random()
            randomAdapter.notifyDataSetChanged()
        }
    }

//    private fun random(): Int {
//        val randomList = mutableListOf<Int>()
//        val random = Random()
//        randomList.add(R.drawable.bigpicture_12)
//        randomList.add(R.drawable.bigpicture_11)
//        randomList.add(R.drawable.bigpicture_13)
//        randomList.add(R.drawable.bigpicture_14)
//        randomList.add(R.drawable.bigpicture_16)
//        randomList.add(R.drawable.bigpicture_1)
//
//        return randomList[random.nextInt(randomList.size)]
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}