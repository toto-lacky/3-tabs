package com.example.project1_java;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project1_java.ui.main.GamePlayFragment;
import com.example.project1_java.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}

            @Override
            public void onPageSelected(int position) {
                //Log.d("Page Change","changed to position: "+position+" class: "+sectionsPagerAdapter.getRegisteredFragment(position).getClass());
                Fragment frag = sectionsPagerAdapter.getRegisteredFragment(2);
                if(frag.getClass() != GamePlayFragment.class)
                    return;
                if (position == 2){
                    if (((GamePlayFragment) frag).getPaused()){
                        viewPager.setPageFixed(false);
                    } else {
                        viewPager.setPageFixed(true);
                    }
                } else {
                    if(!((GamePlayFragment) frag).getPaused())
                        ((GamePlayFragment) frag).pauseGame();
                    viewPager.setPageFixed(false);
                }
            }
        });

//        String[] reqPermissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};
//        setPermission(this, reqPermissions);
    }

//    /* 권한 확인 및 요청 함수 */
//    public static void setPermission(Context context, String[] permissions){
//        /* 추가적으로 요청해야 하는 권한들 */
//        ArrayList<String> ReqPerm = new ArrayList<>();
//
//        /* 현재 권한이 있는지 확인, 없으면 ReqPerm에 추가 */
//        for (String perm : permissions) {
//            if(ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED);
//                //Log.d("Permission","Already have permission: " + perm);
//            else
//                ReqPerm.add(perm);
//        }
//
//        /* 권한 요청 */
//        if(ReqPerm.size() != 0)
//            ActivityCompat.requestPermissions((Activity) context, ReqPerm.toArray(new String[0]),0);
//
//        /* 권한을 받을 때까지 기다리기 */
//        for (String perm : permissions) {
//            while (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
//                //Log.d("Permission","waiting for permission " + perm);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        /* 다시 요청해야 하는 권한들 */
//        ArrayList<String> ReqPerm = new ArrayList<>();
//        for (int i = 0; i < permissions.length; i++) {
//            if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
//                Log.d("Permission","permission " + permissions[i] + " granted");
//            } else {
//                ReqPerm.add(permissions[i]);
//                Log.d("Permission","permission " + permissions[i] + " denied");
//            }
//        }
//
//        if(ReqPerm.size() > 0)
//            ActivityCompat.requestPermissions((Activity) getApplicationContext(), ReqPerm.toArray(new String[0]),0);
//    }
//
//    public void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container, fragment).commit();
//    }
}
