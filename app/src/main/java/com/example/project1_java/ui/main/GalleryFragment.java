package com.example.project1_java.ui.main;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
    //private String path[] = new String[]{"default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default", "default"};

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

        imageview[0].setOnLongClickListener(new ImageLongClickListener(0));
        imageview[1].setOnLongClickListener(new ImageLongClickListener(1));
        imageview[2].setOnLongClickListener(new ImageLongClickListener(2));
        imageview[3].setOnLongClickListener(new ImageLongClickListener(3));
        imageview[4].setOnLongClickListener(new ImageLongClickListener(4));
        imageview[5].setOnLongClickListener(new ImageLongClickListener(5));
        imageview[6].setOnLongClickListener(new ImageLongClickListener(6));
        imageview[7].setOnLongClickListener(new ImageLongClickListener(7));
        imageview[8].setOnLongClickListener(new ImageLongClickListener(8));
        imageview[9].setOnLongClickListener(new ImageLongClickListener(9));
        imageview[10].setOnLongClickListener(new ImageLongClickListener(10));
        imageview[11].setOnLongClickListener(new ImageLongClickListener(11));
        imageview[12].setOnLongClickListener(new ImageLongClickListener(12));
        imageview[13].setOnLongClickListener(new ImageLongClickListener(13));
        imageview[14].setOnLongClickListener(new ImageLongClickListener(14));
        imageview[15].setOnLongClickListener(new ImageLongClickListener(15));
        imageview[16].setOnLongClickListener(new ImageLongClickListener(16));
        imageview[17].setOnLongClickListener(new ImageLongClickListener(17));
        imageview[18].setOnLongClickListener(new ImageLongClickListener(18));
        imageview[19].setOnLongClickListener(new ImageLongClickListener(19));
        imageview[20].setOnLongClickListener(new ImageLongClickListener(20));
        imageview[21].setOnLongClickListener(new ImageLongClickListener(21));
        imageview[22].setOnLongClickListener(new ImageLongClickListener(22));
        imageview[23].setOnLongClickListener(new ImageLongClickListener(23));

        return root;
    }

    public class ImageClickListener2 implements View.OnClickListener{
        int index;
        public ImageClickListener2(int index){
            this.index = index;
        }
        public void onClick(View v){
            if(uris[index] == null) return;

            Intent intent = new Intent(getContext(), ImageActivity.class);
            intent.putExtra("uri",uris[index].toString());
            startActivity(intent);
        }
    }

    public class ImageLongClickListener implements  View.OnLongClickListener{

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
            switch (requestCode){
                case 0:
                    uris[0] = data.getData();
                    imageview[0].setImageURI(uris[0]);
                    break;
                case 1:
                    uris[1] = data.getData();
                    imageview[1].setImageURI(uris[1]);
                    break;
                case 2:
                    uris[2] = data.getData();
                    imageview[2].setImageURI(uris[2]);
                    break;
                case 3:
                    uris[3] = data.getData();
                    imageview[3].setImageURI(uris[3]);
                    break;
                case 4:
                    uris[4] = data.getData();
                    imageview[4].setImageURI(uris[4]);
                    break;
                case 5:
                    uris[5] = data.getData();
                    imageview[5].setImageURI(uris[5]);
                    break;
                case 6:
                    uris[6] = data.getData();
                    imageview[6].setImageURI(uris[6]);
                    break;
                case 7:
                    uris[7] = data.getData();
                    imageview[7].setImageURI(uris[7]);
                    break;
                case 8:
                    uris[8] = data.getData();
                    imageview[8].setImageURI(uris[8]);
                    break;
                case 9:
                    uris[9] = data.getData();
                    imageview[9].setImageURI(uris[9]);
                    break;
                case 10:
                    uris[10] = data.getData();
                    imageview[10].setImageURI(uris[10]);
                    break;
                case 11:
                    uris[11] = data.getData();
                    imageview[11].setImageURI(uris[11]);
                    break;
                case 12:
                    uris[12] = data.getData();
                    imageview[12].setImageURI(uris[12]);
                    break;
                case 13:
                    uris[13] = data.getData();
                    imageview[13].setImageURI(uris[13]);
                    break;
                case 14:
                    uris[14] = data.getData();
                    imageview[14].setImageURI(uris[14]);
                    break;
                case 15:
                    uris[15] = data.getData();
                    imageview[15].setImageURI(uris[15]);
                    break;
                case 16:
                    uris[16] = data.getData();
                    imageview[16].setImageURI(uris[16]);
                    break;
                case 17:
                    uris[17] = data.getData();
                    imageview[17].setImageURI(uris[17]);
                    break;
                case 18:
                    uris[18] = data.getData();
                    imageview[18].setImageURI(uris[18]);
                    break;
                case 19:
                    uris[19] = data.getData();
                    imageview[19].setImageURI(uris[19]);
                    break;
                case 20:
                    uris[20] = data.getData();
                    imageview[20].setImageURI(uris[20]);
                    break;
                case 21:
                    uris[21] = data.getData();
                    imageview[21].setImageURI(uris[21]);
                    break;
                case 22:
                    uris[22] = data.getData();
                    imageview[22].setImageURI(uris[22]);
                    break;
                case 23:
                    uris[23] = data.getData();
                    imageview[23].setImageURI(uris[23]);
                    break;
            }
        }
    }
}