package com.example.notifications

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(!allPermission()) pushPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        val notificationManage = NotificationManaging(this)
        binding.showBtn.setOnClickListener{
            notificationManage.showMessage(this)
        }
    }
    private var pushPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            Toast.makeText(
                this,
                if(it) "Permission granted" else "Permission NOT granted",
                Toast.LENGTH_SHORT
            ).show()
        }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun allPermission() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

}