package com.example.wallpaperapp.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.core.view.drawToBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wallpaperapp.MyImageManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.database.Image
import com.example.wallpaperapp.databinding.FragmentDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class DetailFragment : Fragment() {
    private lateinit var myImageManager: MyImageManager
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // BU CODE ORQALI FRAGMENTDAGI ACTIONBAR NI YASHIRAMIZ
//        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        // Bu CODE ORQALI FRAGMENTDA BOTTOMNAV NI YASHIRIB QOYAMMIZ
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        navBar.isVisible = false

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        binding.btnBackground.setOnClickListener {
            SetImage().execute()
        }
        binding.btnShare.setOnClickListener {
            shareImg()
        }
        binding.btnSaveGallery.setOnClickListener {
            saveToGallery()
            Toast.makeText(requireContext(), "Saqlandi", Toast.LENGTH_SHORT).show()
        }
        binding.btnLike.setOnClickListener {

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews(view: View) {
        // RV DA BOSILGAN ITEMNI RASMINI QABUL QILIB OLISH
        val newImage = arguments?.getParcelable<Image>("image")
        // BU YERDA O'SHA KELGAN RASMNI SHU FRAGMENTDAGI IMGVIEWGA TENGLAB OLISH
        newImage.let { image ->
            binding.imageDetail.setImageResource(image?.image!!)
        }

    }

    // GALAREYAGA RASM SAQLASH
    private fun saveToGallery() {
        val myImage = binding.imageDetail.drawable
        val bitmap = myImage.toBitmap(binding.imageDetail.width, binding.imageDetail.height)
        val path =
            MediaStore.Images.Media.insertImage(
                requireActivity().contentResolver,
                bitmap,
                "My image",
                String()
            )
        Uri.parse(path)
    }

    // RASM JONATISH
    private fun shareImg() {
        val myImage = binding.imageDetail.drawable
        val bitmap = myImage.toBitmap(binding.imageDetail.width, binding.imageDetail.height)
        val path =
            MediaStore.Images.Media.insertImage(
                requireActivity().contentResolver,
                bitmap,
                "My image",
                String()
            )
        val uri = Uri.parse(path)
        val intent = Intent()
        intent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/*"
        }
        startActivity(Intent.createChooser(intent, "Test"))
    }

    inner class SetImage : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            val imageDetail: ImageView = view?.findViewById(R.id.imageDetail)!!
            val btnFon: FloatingActionButton = view?.findViewById(R.id.btnBackground)!!
            myImageManager = MyImageManager(requireActivity())
            myImageManager.setImage(imageDetail.drawable.toBitmap())
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Toast.makeText(requireActivity(), "Set Wallpaper", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}