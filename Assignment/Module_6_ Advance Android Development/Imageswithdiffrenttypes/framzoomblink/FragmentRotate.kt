package com.example.framzoomblink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Button
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable

class FragmentRotate: Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var rotateButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rotate, container, false)
        imageView = view.findViewById(R.id.imageView)
        rotateButton = view.findViewById(R.id.rotateButton)

        rotateButton.setOnClickListener {
            rotateImage()
        }

        return view
    }

    private fun rotateImage() {
        val drawable = imageView.drawable
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val matrix = Matrix()
            matrix.postRotate(90f) // Rotate the image by 90 degrees clockwise
            val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            imageView.setImageBitmap(rotatedBitmap)
        }
    }
}

