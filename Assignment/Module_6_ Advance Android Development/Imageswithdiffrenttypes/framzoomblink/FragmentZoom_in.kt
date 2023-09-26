 package com.example.framzoomblink

 import android.animation.Animator
 import android.animation.AnimatorListenerAdapter
 import android.graphics.Matrix
 import android.os.Bundle
 import android.os.Handler
 import android.view.GestureDetector
 import android.view.LayoutInflater
 import android.view.MotionEvent
 import android.view.View
 import android.view.ViewGroup
 import android.view.animation.AccelerateDecelerateInterpolator
 import android.view.animation.ScaleAnimation
 import androidx.fragment.app.Fragment
 import com.example.framzoomblink.databinding.FragmentBlinkBinding
 import com.example.framzoomblink.databinding.FragmentZoomInBinding


 class FragmentZoom_in :Fragment()
 {
     private var _binding: FragmentZoomInBinding? = null
     // This property is only valid between onCreateView and
// onDestroyView.
     private val binding get() = _binding!!
     private var isZoomedIn = false

     override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         // Inflate the layout for this fragment
         _binding = FragmentZoomInBinding.inflate(inflater, container, false)
         val view = binding.root

         // Set an OnClickListener to the ImageView
         binding.zoomableImage.setOnClickListener {
             if (isZoomedIn)
             {
                 // Zoom out animation
                 zoomOut()
             }
             else
             {
                 // zoom in animation
                 zoomin()
             }
         }


         return view

     }
     private fun zoomin()
     {
         binding.zoomableImage.animate()
             .scaleX(2.0f)// adjusting scale factor
             .scaleY(2.0f)
             .setDuration(400)// i have set animation duration
             .setInterpolator(AccelerateDecelerateInterpolator())
             .setListener(object :AnimatorListenerAdapter(){
                 override fun onAnimationEnd(animation: Animator) {
                     isZoomedIn = true
                 }
             })
     }
     private fun zoomOut() {
         binding.zoomableImage.animate()
             .scaleX(1.0f)
             .scaleY(1.0f)
             .setDuration(300)
             .setInterpolator(AccelerateDecelerateInterpolator())
             .setListener(object : AnimatorListenerAdapter() {

                 override fun onAnimationEnd(animation: Animator) {
                     isZoomedIn = false
                 }
             })

     }
             }
