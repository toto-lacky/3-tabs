package com.example.project1_java;

import android.graphics.Bitmap;

public class Util {
    /* 이미지 파일을 resizing하는 메소드 */
    public static Bitmap resizingBitmap(Bitmap oBitmap){
        if (oBitmap == null)
            return null;
        int width = oBitmap.getWidth();
        int height = oBitmap.getHeight();
        int resizing_size = 120;
        Bitmap rBitmap = null;
        if (width > resizing_size) {
            int mWidth = width / 100;
            int fScale = resizing_size / mWidth;
            width *= (fScale / 100);
            height *= (fScale / 100);
        } else if (height > resizing_size) {
            int mHeight = height / 100;
            int fScale = resizing_size / mHeight;
            width *= (fScale / 100);
            height *= (fScale / 100);
        }
        rBitmap = Bitmap.createScaledBitmap(oBitmap, width, height, true);
        return rBitmap;
    }
}
