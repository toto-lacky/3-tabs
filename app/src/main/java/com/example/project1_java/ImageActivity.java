package com.example.project1_java;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

public class ImageActivity extends Activity {

    private static int currentIndex = 0;
    private static String uri_s[];

    private ViewPager viewPager;
    private GalleryViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_viewpager);

        Intent receivedIntent = getIntent();
        uri_s = receivedIntent.getExtras().getStringArray("uri");
        currentIndex = receivedIntent.getExtras().getInt("current");

        viewPager = findViewById(R.id.galleryViewPager);
        pagerAdapter = new GalleryViewPagerAdapter(this, uri_s);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(currentIndex);
    }
}