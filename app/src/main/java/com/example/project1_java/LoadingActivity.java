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

public class LoadingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String[] reqPermissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(this, reqPermissions,0);
    }

    /* 권한 확인 및 요청 함수 */
    public static void setPermission(Context context, String[] permissions){
        ActivityCompat.requestPermissions((Activity) context, permissions,0);
    }

    /* 권한을 모두 획득하면 appActivity 실행, 그렇지 않으면 다시 권한 요청 */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        /* 다시 요청해야 하는 권한들 */
        int cnt = 0;
        for (int i = 0; i < permissions.length; i++) {
            if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                Log.d("Permission","permission " + permissions[i] + " granted");
            } else {
                cnt++;
                Log.d("Permission","permission " + permissions[i] + " denied");
            }
        }

        if(cnt > 0) {
            ActivityCompat.requestPermissions(this, permissions, 0);
        } else {
            switchActivity();
        }
    }

    private void switchActivity(){
        Intent appIntent = new Intent(this, MainActivity.class);
        startActivity(appIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
