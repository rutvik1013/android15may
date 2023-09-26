package com.example.framzoomblink

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.example.framzoomblink.databinding.FragmentBlinkBinding
import com.example.framzoomblink.databinding.FragmentFrambyFramBinding


class Fragment_FrambyFram : Fragment() {
    private var _binding: FragmentFrambyFramBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFrambyFramBinding.inflate(inflater,container,false)
        val view=binding.root



        setupAnimation()

        return view
    }

    private fun setupAnimation()
    {
        animationDrawable=AnimationDrawable().apply {
            addFrame(resources.getDrawable(R.drawable.one),300)
            addFrame(resources.getDrawable(R.drawable.two),300)
            addFrame(resources.getDrawable(R.drawable.three),300)
            addFrame(resources.getDrawable(R.drawable.four),300)
            addFrame(resources.getDrawable(R.drawable.five),300)
            addFrame(resources.getDrawable(R.drawable.six),300)
            addFrame(resources.getDrawable(R.drawable.seven),300)
            addFrame(resources.getDrawable(R.drawable.eight),300)
            addFrame(resources.getDrawable(R.drawable.nine),300)
            addFrame(resources.getDrawable(R.drawable.ten),300)

            isOneShot=false



        }
        binding.framimage.setImageDrawable(animationDrawable)


    }

    override fun onResume() {
        super.onResume()
        animationDrawable.start()
    }

    override fun onPause() {
        super.onPause()
        animationDrawable.stop()
    }

}


