package com.example.project1_java;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneimage);

        ImageView imageView = findViewById(R.id.imageView);
        setImage(imageView);
    }
    private void setImage(ImageView imageView){
        Intent receivedIntent = getIntent();
        String uri = receivedIntent.getExtras().getString("uri");
        imageView.setImageURI(Uri.parse(uri));
    }
}