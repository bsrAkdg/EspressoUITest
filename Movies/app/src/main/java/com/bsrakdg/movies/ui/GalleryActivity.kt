package com.bsrakdg.movies.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bsrakdg.movies.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    private val tag: String = "AppDebug"

    private val code = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        button_open_gallery.setOnClickListener {
            pickFromGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d(tag, "RESULT_OK")
            when (requestCode) {

                code -> {
                    Log.d(tag, "GALLERY_REQUEST_CODE detected.")
                    data?.data?.let { uri ->
                        Log.d(tag, "URI: $uri")
                        Glide.with(this)
                            .load(uri)
                            .into(image)
                    }
                }
            }
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, code)
    }

}