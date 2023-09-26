package com.example.framzoomblink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.example.framzoomblink.databinding.FragmentBlinkBinding
import com.example.framzoomblink.databinding.FragmentMoveBinding


class FragmentMove : Fragment() {

    private var _binding: FragmentMoveBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentMoveBinding.inflate(inflater,container,false)
        val view=binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      binding.buttonmove.setOnClickListener {
            moveImage()
        }
    }

    private fun moveImage() {
        val animation = TranslateAnimation(0f, 200f, 0f, 200f)
        animation.duration = 1000 // Duration in milliseconds
        animation.fillAfter = true
        binding.moveimage.startAnimation(animation)
    }
}
