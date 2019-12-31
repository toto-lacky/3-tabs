package com.example.project1_java;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.project1_java.ui.main.SectionsPagerAdapter;

public class FixableViewPager extends ViewPager {

    private boolean isFixed;

    public FixableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.isFixed = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.isFixed) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.isFixed) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    //@Override
    public void setAdapter(@Nullable SectionsPagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Nullable
    @Override
    public SectionsPagerAdapter getAdapter() {
        return (SectionsPagerAdapter) super.getAdapter();
    }

    public void setPageFixed(boolean fix) {
        this.isFixed = fix;
    }

    public boolean getPageFixed(){
        return this.isFixed;
    }
}
