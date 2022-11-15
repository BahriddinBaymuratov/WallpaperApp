package com.example.wallpaperapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.wallpaperapp.database.Wallpaper
import com.example.wallpaperapp.database.WallpaperDatabase
import com.example.wallpaperapp.databinding.ActivityMainBinding
import com.example.wallpaperapp.util.ObjectList
import com.example.wallpaperapp.util.toByteArray

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var image: ImageView
    private val database by lazy { WallpaperDatabase.invoke(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav()
        navButton()
        drawerNav()
//        DataBaseTask()

    }

    private fun bottomNav() {
        // bottom navigation  PASTDAGI NAVIGATION BILAN FRAGMENTLARNI BOSHQARISH
        navController = findNavController(R.id.nav_host_fragment_home)
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_,destination,_ ->
            if (destination.id == R.id.nav_host_fragment_home){
                binding.bottomNav.visibility = View.GONE
                binding.navigationView.visibility = View.GONE
            }else{
                binding.bottomNav.visibility = View.VISIBLE
                binding.navigationView.visibility = View.VISIBLE

            }
        }
    }

    private fun navButton(){
        //Navigation up BUTTON 1  FRAGMENDAN FRAGMENTGA OTGANDA ORTGA QAYTISH UCHUN SHUNAQA <-  BUTTON CHIQARIB BERADI
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        navController = findNavController(R.id.nav_host_fragment_home)
        binding.bottomNav.setupWithNavController(navController)

    }
//     NAVIGATION UP BUTTON 2  FRAGMENDAN FRAGMENTGA OTGANDA ORTGA QAYTISH UCHUN SHUNAQA <-  BUTTON CHIQARIB BERADI
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun drawerNav() {
        // drawer navigation  DRAWER NAVIGATION BILAN BUTTON NAVIGATIONDI ULAB BERADI
        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }

//    inner class DataBaseTask: AsyncTask<Wallpaper, Unit, Unit>(){
//
//        override fun doInBackground(vararg wallpaper: Wallpaper?) {
//            image = findViewById(R.id.image)
//            database.dao().saveImg(
//                Wallpaper(
//                    1,
//                    "photo",
//                    image.toByteArray()
//                )
//            )
//        }
//
//        override fun onPostExecute(result: Unit?) {
//            super.onPostExecute(result)
//            Toast.makeText(this@MainActivity, "Photos saved!", Toast.LENGTH_SHORT).show()
//
//        }
//    }


}