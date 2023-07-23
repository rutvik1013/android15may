package com.example.navigationdrawa

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView


class slider : Fragment() {
    lateinit var slider :SliderLayout


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_slider, container, false)

        slider=view.findViewById(R.id.slider)

        var file_maps=HashMap<String,Int>()

        file_maps["Butter Milks"]=R.drawable.buttermilk
        file_maps["Dahi"]=R.drawable.dahi
        file_maps["Ghee"]=R.drawable.ghee
        file_maps["Kulfi"]=R.drawable.kulfi

        for (name in file_maps.keys)
        {
            val textSlider=TextSliderView(requireActivity())

            textSlider.description(name)
                .image(file_maps.get(name)!!)
            slider.addSlider(textSlider)

            slider.setPresetTransformer(SliderLayout.Transformer.CubeIn)

        }




        return view
    }


}