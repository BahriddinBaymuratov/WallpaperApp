package com.example.wallpaperapp.fragment

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.adapter.WallpaperAdapter
import com.example.wallpaperapp.database.Image
import com.example.wallpaperapp.database.Wallpaper
import com.example.wallpaperapp.database.WallpaperDatabase
import com.example.wallpaperapp.databinding.FragmentHomeBinding
import com.example.wallpaperapp.util.ObjectList
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var wallpaperAdapter: WallpaperAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wallpaperAdapter = WallpaperAdapter()
        wallpaperAdapter.imageList = ObjectList.wallpaperList
        binding.recyclerView.apply {
            adapter = wallpaperAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        wallpaperAdapter.onItemClicked = {
            val bundle = bundleOf("image" to it)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

    }

//    inner class RvTask : AsyncTask<Unit, Unit, Unit>() {
//        override fun doInBackground(vararg p0: Unit?) {
//            imageList = database.dao().getAllImg() as MutableList<Wallpaper>
//        }
//    }
}