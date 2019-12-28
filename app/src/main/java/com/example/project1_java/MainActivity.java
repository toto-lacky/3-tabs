package com.example.project1_java;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.project1_java.ui.main.SectionsPagerAdapter;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        setPermission(Manifest.permission.READ_CONTACTS);
        setPermission(Manifest.permission.CALL_PHONE);
    }

    /* 권한 확인 및 요청 함수 */
    void setPermission(String permission){
        if(ContextCompat.checkSelfPermission(this, permission) ==
                PackageManager.PERMISSION_GRANTED) {
            // 권한이 있을 때 알림 띄우기
            //Toast.makeText(this, "Permission Set", Toast.LENGTH_SHORT).show();
            Log.d("Permission","Permission set");
        } else {
            // 권한이 없을 때 권한 요청하기
            String[] permissions = {permission};
            ActivityCompat.requestPermissions(this, permissions,0);
        }

        // 권한을 받을 때까지 기다리기
        while(ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED){}
    }
}