package com.example.project1_java.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project1_java.R;

public class GalleryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ImageView imageview[] = new ImageView[24];

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

        /*
        GridView gridViewImages = root.findViewById(R.id.gridView);
        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(getActivity());
        gridViewImages.setAdapter(imageGridAdapter);
        */

        imageview[0] = root.findViewById(R.id.img1);
        imageview[1] = root.findViewById(R.id.img2);
        imageview[2] = root.findViewById(R.id.img3);
        imageview[3] = root.findViewById(R.id.img4);
        imageview[4] = root.findViewById(R.id.img5);
        imageview[5] = root.findViewById(R.id.img6);
        imageview[6] = root.findViewById(R.id.img7);
        imageview[7] = root.findViewById(R.id.img8);
        imageview[8] = root.findViewById(R.id.img9);
        imageview[9] = root.findViewById(R.id.img10);
        imageview[10] = root.findViewById(R.id.img11);
        imageview[11] = root.findViewById(R.id.img12);
        imageview[12] = root.findViewById(R.id.img13);
        imageview[13] = root.findViewById(R.id.img14);
        imageview[14] = root.findViewById(R.id.img15);
        imageview[15] = root.findViewById(R.id.img16);
        imageview[16] = root.findViewById(R.id.img17);
        imageview[17] = root.findViewById(R.id.img18);
        imageview[18] = root.findViewById(R.id.img19);
        imageview[19] = root.findViewById(R.id.img20);
        imageview[20] = root.findViewById(R.id.img21);
        imageview[21] = root.findViewById(R.id.img22);
        imageview[22] = root.findViewById(R.id.img23);
        imageview[23] = root.findViewById(R.id.img24);

        imageview[0].setOnClickListener(new ImageClickListener2(0));
        imageview[1].setOnClickListener(new ImageClickListener2(1));
        imageview[2].setOnClickListener(new ImageClickListener2(2));
        imageview[3].setOnClickListener(new ImageClickListener2(3));
        imageview[4].setOnClickListener(new ImageClickListener2(4));
        imageview[5].setOnClickListener(new ImageClickListener2(5));
        imageview[6].setOnClickListener(new ImageClickListener2(6));
        imageview[7].setOnClickListener(new ImageClickListener2(7));
        imageview[8].setOnClickListener(new ImageClickListener2(8));
        imageview[9].setOnClickListener(new ImageClickListener2(9));
        imageview[10].setOnClickListener(new ImageClickListener2(10));
        imageview[11].setOnClickListener(new ImageClickListener2(11));
        imageview[12].setOnClickListener(new ImageClickListener2(12));
        imageview[13].setOnClickListener(new ImageClickListener2(13));
        imageview[14].setOnClickListener(new ImageClickListener2(14));
        imageview[15].setOnClickListener(new ImageClickListener2(15));
        imageview[16].setOnClickListener(new ImageClickListener2(16));
        imageview[17].setOnClickListener(new ImageClickListener2(17));
        imageview[18].setOnClickListener(new ImageClickListener2(18));
        imageview[19].setOnClickListener(new ImageClickListener2(19));
        imageview[20].setOnClickListener(new ImageClickListener2(20));
        imageview[21].setOnClickListener(new ImageClickListener2(21));
        imageview[22].setOnClickListener(new ImageClickListener2(22));
        imageview[23].setOnClickListener(new ImageClickListener2(23));

        return root;
    }
    public class ImageClickListener2 implements View.OnClickListener{
        int index;
        public ImageClickListener2(int index){
            this.index = index;
        }
        public void onClick(View v){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent, index);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (data != null && data.getData() != null){
            Uri selectedImageUri = data.getData();
            switch (requestCode){
                case 0:
                    imageview[0].setImageURI(selectedImageUri);
                    break;
                case 1:
                    imageview[1].setImageURI(selectedImageUri);
                    break;
                case 2:
                    imageview[2].setImageURI(selectedImageUri);
                    break;
                case 3:
                    imageview[3].setImageURI(selectedImageUri);
                    break;
                case 4:
                    imageview[4].setImageURI(selectedImageUri);
                    break;
                case 5:
                    imageview[5].setImageURI(selectedImageUri);
                    break;
                case 6:
                    imageview[6].setImageURI(selectedImageUri);
                    break;
                case 7:
                    imageview[7].setImageURI(selectedImageUri);
                    break;
                case 8:
                    imageview[8].setImageURI(selectedImageUri);
                    break;
                case 9:
                    imageview[9].setImageURI(selectedImageUri);
                    break;
                case 10:
                    imageview[10].setImageURI(selectedImageUri);
                    break;
                case 11:
                    imageview[11].setImageURI(selectedImageUri);
                    break;
                case 12:
                    imageview[12].setImageURI(selectedImageUri);
                    break;
                case 13:
                    imageview[13].setImageURI(selectedImageUri);
                    break;
                case 14:
                    imageview[14].setImageURI(selectedImageUri);
                    break;
                case 15:
                    imageview[15].setImageURI(selectedImageUri);
                    break;
                case 16:
                    imageview[16].setImageURI(selectedImageUri);
                    break;
                case 17:
                    imageview[17].setImageURI(selectedImageUri);
                    break;
                case 18:
                    imageview[18].setImageURI(selectedImageUri);
                    break;
                case 19:
                    imageview[19].setImageURI(selectedImageUri);
                    break;
                case 20:
                    imageview[20].setImageURI(selectedImageUri);
                    break;
                case 21:
                    imageview[20].setImageURI(selectedImageUri);
                    break;
                case 22:
                    imageview[20].setImageURI(selectedImageUri);
                    break;
                case 23:
                    imageview[20].setImageURI(selectedImageUri);
                    break;
            }
        }
    }
}