package com.developeralamin.bloodapp.ui.auth

import android.os.Bundle
import com.developeralamin.bloodapp.R
import io.github.dreierf.materialintroscreen.MaterialIntroActivity
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder

class IntroActivity : MaterialIntroActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(SlideFragmentBuilder()
            .title("Blood App")
            .image(R.drawable.blood)
            .buttonsColor(R.color.colorPrimary)
            .backgroundColor(R.color.black)
            .build())

        addSlide(SlideFragmentBuilder()
            .title("Developer Alamin")
            .image(R.drawable.ic_website)
            .buttonsColor(R.color.colorPrimary)
            .backgroundColor(R.color.black)
            .build())

        addSlide(SlideFragmentBuilder()
            .title("Website")
            .image(R.drawable.ic_website)
            .buttonsColor(R.color.colorPrimary)
            .backgroundColor(R.color.black)
            .build())
    }

}