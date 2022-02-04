package ru.semwai.android_lab6_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.semwai.android_lab6_2.databinding.ActivityMainBinding
import android.util.Log
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso


class MainActivityPicasso : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("image", "picasso")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            setImage("https://thiscatdoesnotexist.com/")
        }
        setContentView(binding.root)
    }


    private fun setImage(url: String) {
        Log.v("image", "setImage")
        Picasso.get().load(url).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(binding.image)
    }


}