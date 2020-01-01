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

public class ImageActivity extends Activity {

    private int currentIndex = 0;
    private String uri_s[];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneimage);
        final ImageView imageView = findViewById(R.id.imageView);
        LinearLayout ll = findViewById(R.id.onell);

        Intent receivedIntent = getIntent();
        uri_s = receivedIntent.getExtras().getStringArray("uri");
        currentIndex = receivedIntent.getExtras().getInt("current");

        setImage(imageView, currentIndex);

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
                if(Math.abs(e1.getY() - e2.getY()) > 800)
                    return false;
                if(e1.getX() - e2.getX() > 120 && Math.abs(velocityX) > 200){
                    if(currentIndex > 22) return false;
                    currentIndex++;
                    setImage(imageView, currentIndex);
                }
                else if(e2.getX() - e1.getX() > 120 && Math.abs(velocityX) > 200){
                    if(currentIndex < 1) return false;
                    currentIndex--;
                    setImage(imageView, currentIndex);
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        ll.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }
    private void setImage(ImageView imageView, int index){
        if(uri_s[index] == null) imageView.setImageResource(R.drawable.ic_image);
        else {
            imageView.setImageURI(Uri.parse(uri_s[index]));
        }
    }
}