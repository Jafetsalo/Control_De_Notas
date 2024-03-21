package com.example.control_de_notas;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.control_de_notas.ui.main.CrearAlumnoFragment;
import com.example.control_de_notas.ui.main.MenuMateriasFragment;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    public ScreenSlidePagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        if (position == 0) {
            return new CrearAlumnoFragment();
        } else {
            return new MenuMateriasFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // The number of pages
    }
}

//After we created our Activity and added a ViewPager2 to its XML, now we build this class

//I'm trying to parse it's meaning - I see it is a FragmentStateAdapter
//So it likely takes two methods and overrides them. THis is essentially a fragmentStateAdapter
//Also has a strange constructor, with a method I DON't UNDERSTAND

