package com.example.framzoomblink

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.framzoomblink.databinding.FragmentBlinkBinding


class FragmentBlink : Fragment() {
    private var _binding: FragmentBlinkBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var isimagevisible=true
    private var blinktimemills=600

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBlinkBinding.inflate(inflater,container,false)
        val view=binding.root
        var imageview=binding.blinkimage

        startblinkImage()
        return view

    }
    private val blinkRunnable=object :Runnable{
        override fun run() {
            isimagevisible=!isimagevisible
            binding.blinkimage.visibility=if (isimagevisible) View.VISIBLE else View.INVISIBLE

            handler.postDelayed(this,blinktimemills.toLong())
        }

    }
    private val handler=Handler()

    override fun onDestroy() {
        super.onDestroy()
        stopblinking()
    }
    private fun startblinkImage()
    {
        handler.post(blinkRunnable)
    }
    private fun stopblinking()
    {
        handler.removeCallbacks(blinkRunnable)
    }
}