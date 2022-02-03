package ru.semwai.android_lab6_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.semwai.android_lab6_2.databinding.ActivityMainBinding
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivityCoroutine : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("image", "coroutine")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            setImage("https://thiscatdoesnotexist.com/")
        }
        setContentView(binding.root)
    }

    private fun setImage(url: String) {
        lifecycleScope.launchWhenCreated {
            val newUrl = URL(url)
            val mIcon = withContext(Dispatchers.IO) {
                BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            }
            binding.image.post {
                binding.image.setImageBitmap(mIcon)
            }
        }
    }
}