package dev.lesam.androidconferences.slides.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.lesam.androidconferences.R

class HelloWorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_world)
    }
}