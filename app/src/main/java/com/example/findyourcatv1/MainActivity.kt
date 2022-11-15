package com.example.findyourcatv1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.findyourcatv1.databinding.ActivityMainBinding
import com.example.findyourcatv1.models.UsersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        usersViewModel.userLiveData.observe(this) {
            if (it == null) {
                menu.findItem(R.id.action_logout).isVisible = false
                menu.findItem(R.id.action_login).isVisible = true
            } else {
                menu.findItem(R.id.action_logout).isVisible = true
                menu.findItem(R.id.action_login).isVisible = false
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.SettingsFragment)
                true
            }
            R.id.action_login -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.LoginFragment)
                true
            }
            R.id.action_logout -> {
                usersViewModel.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
