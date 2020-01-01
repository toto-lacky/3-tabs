package com.example.project1_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project1_java.ui.main.GamePlayFragment;
import com.example.project1_java.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final FixableViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int position) {
                //Log.d("Page Change","changed to position: "+position+" class: "+sectionsPagerAdapter.getRegisteredFragment(position).getClass());
                Fragment frag = sectionsPagerAdapter.getRegisteredFragment(2);
                if (frag.getClass() != GamePlayFragment.class)
                    return;
                if (position == 2) {
                    if (((GamePlayFragment) frag).getPaused()) {
                        viewPager.setPageFixed(false);
                    } else {
                        viewPager.setPageFixed(true);
                    }
                } else {
                    if (!((GamePlayFragment) frag).getPaused())
                        ((GamePlayFragment) frag).pauseGame();
                    viewPager.setPageFixed(false);
                }
            }
        });
    }
}
