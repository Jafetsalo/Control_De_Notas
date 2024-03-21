package com.example.control_de_notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class AlumnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);


        ViewPager2 viewPager = findViewById(R.id.viewPager);
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

    }
    //My second activity :)

    //It grasps our XML viewpager
    //Then It assigns an instance of pageradapter to it


}