package com.example.project1_java;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class GalleryViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private String uri_s[];

    public GalleryViewPagerAdapter(Context context, String[] uri_s){
        this.mContext = context;
        this.uri_s = uri_s;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null ;

        if (mContext != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.oneimage, container, false);

            ImageView imageView = view.findViewById(R.id.imageView);
            if(uri_s[position] == null) imageView.setImageResource(R.drawable.ic_image);
            else    imageView.setImageURI(Uri.parse(uri_s[position]));
        }

        container.addView(view) ;

        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 24;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}
