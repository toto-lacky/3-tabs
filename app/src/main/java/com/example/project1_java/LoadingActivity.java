package com.example.project1_java;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String[] reqPermissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};
        int neededPermissions = setPermission(this, reqPermissions);

        if(neededPermissions == 0)
            switchActivity();
    }

    /* 권한 확인 및 요청 함수 */
    public static int setPermission(Context context, String[] permissions){
        /* 추가적으로 요청해야 하는 권한들 */
        ArrayList<String> ReqPerm = new ArrayList<>();

        /* 현재 권한이 있는지 확인, 없으면 ReqPerm에 추가 */
        for (String perm : permissions) {
            if(ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED);
                //Log.d("Permission","Already have permission: " + perm);
            else
                ReqPerm.add(perm);
        }

        /* 권한 요청 */
        if(ReqPerm.size() != 0)
            ActivityCompat.requestPermissions((Activity) context, ReqPerm.toArray(new String[0]),0);

        return ReqPerm.size();
    }

    /* 권한을 모두 획득하면 appActivity 실행, 그렇지 않으면 다시 권한 요청 */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        Log.d("ReqPermRes","requestCode = "+requestCode);
        Log.d("ReqPermRes","permissions: "+permissions);
        Log.d("ReqPermRes","grantResults: "+grantResults);

        /* 다시 요청해야 하는 권한들 */
        ArrayList<String> ReqPerm = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                Log.d("Permission","permission " + permissions[i] + " granted");
            } else {
                ReqPerm.add(permissions[i]);
                Log.d("Permission","permission " + permissions[i] + " denied");
            }
        }

        if(ReqPerm.size() > 0)
            ActivityCompat.requestPermissions(this, ReqPerm.toArray(new String[0]),0);

        if(ReqPerm.size() == 0){
            switchActivity();
        }
    }

    private void switchActivity(){
        Intent appIntent = new Intent(this, MainActivity.class);
        startActivity(appIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
