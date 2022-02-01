package ru.semwai.android_lab6_2

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import android.graphics.BitmapFactory
import android.util.Log
import java.net.URL
import ru.semwai.android_lab6_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var backgroundThread: Future<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            setImage("https://thiscatdoesnotexist.com/")
        }

        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundThread.cancel(true)
    }

    private fun setImage(url: String) {
        Log.v("image", "start")
        backgroundThread = (applicationContext as ExecutorClass).executor.submit {
            val newUrl = URL(url)
            val mIcon = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            Log.v("image", mIcon.byteCount.toString())
            binding.image.post {
                binding.image.setImageBitmap(mIcon)
            }
        }
    }

    class ExecutorClass : Application() {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
    }
}