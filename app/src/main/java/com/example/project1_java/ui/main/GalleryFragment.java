package com.example.project1_java.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project1_java.ImageActivity;
import com.example.project1_java.R;

public class GalleryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ImageView imageview[] = new ImageView[24];
    private Uri uris[] = new Uri[24];
    private String uris_s[] = new String[24];

    public static GalleryFragment newInstance(int index) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        for(int i = 0; i<24; i++){
            String imgID = "img" + (i+1);
            int resID = getResources().getIdentifier(imgID, "id", getContext().getPackageName());
            imageview[i] = root.findViewById(resID);
            imageview[i].setOnClickListener(new ImageClickListener(i));
            imageview[i].setOnLongClickListener(new ImageLongClickListener(i));
        }
/*
        SharedPreferences sf = getActivity().getSharedPreferences("sFile", Context.MODE_PRIVATE);
        
        for(int i = 0; i<24; i++){
            String uriID = "uri" +i;
            uris_s[i] = sf.getString(uriID,"");
            if(uris_s[i].equals("")) continue;
            uris[i] = Uri.parse(uris_s[i]);
            imageview[i].setImageURI(uris[i]);
        }

        imageview[0].setImageURI(Uri.parse(sf.getString("1234","")));
        Log.d("error_log",""+Uri.parse(sf.getString("1234","")));
        Log.d("error log",sf.getString("1234",""));

         */
        return root;
    }

    public class ImageClickListener implements View.OnClickListener{
        int index;
        public ImageClickListener(int index){
            this.index = index;
        }
        public void onClick(View v){
            Intent intent = new Intent(getContext(), ImageActivity.class);
            intent.putExtra("uri",uris_s);
            intent.putExtra("current",index);
            startActivity(intent);
        }
    }

    public class ImageLongClickListener implements View.OnLongClickListener{
        int index;
        public ImageLongClickListener(int index){
            this.index = index;
        }
        public boolean onLongClick(View v){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, index);

            return true;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (data != null && data.getData() != null){
            uris[requestCode] = data.getData();
            imageview[requestCode].setImageURI(uris[requestCode]);
            uris_s[requestCode] = uris[requestCode].toString();
            Log.d("error log",uris_s[requestCode]);
        }
    }
/*
    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences sf = getActivity().getSharedPreferences("sFile", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sf.edit();
        editor.putString("1234", uris_s[0]);

        for(int i = 0; i<24; i++){
            String uriID = "uri" + i;
            editor.putString(uriID, uris_s[i]);
        }

        if(uris_s[0] != null) {
            Log.d("error log", uris_s[0]);
        }
        editor.commit();
    }

 */
}