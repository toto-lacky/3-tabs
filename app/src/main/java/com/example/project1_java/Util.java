package com.example.project1_java;

import android.graphics.Bitmap;
import android.util.Log;

public class Util {
    /* Bitmap을 정사각형으로 자르는 메소드 */
    public static Bitmap squareBitmap(Bitmap oBitmap){
        if (oBitmap == null)
            return null;
        int width = oBitmap.getWidth();
        int height = oBitmap.getHeight();
        int resx = 0;
        int resy = 0;
        int reswidth = width;
        int resheight = height;

        if (width > height){
            reswidth = height;
            resx = (width - height) / 2;
        } else if (width < height) {
            resheight = width;
            resy = (height - width) / 2;
        }

        return Bitmap.createBitmap(oBitmap, resx, resy, reswidth, resheight);
    }

    /* Bitmap을 resizing하는 메소드 */
    public static Bitmap resizingBitmap(Bitmap oBitmap, int size){
        if (oBitmap == null)
            return null;
        int width = oBitmap.getWidth();
        int height = oBitmap.getHeight();
        Bitmap rBitmap;
/*
        Log.d("oBitmap","width: "+width+" height: "+height);

        if (width > size) {
            int mWidth = width / 100;
            int fScale = size / mWidth;
            width *= (fScale / 100);
            height *= (fScale / 100);
        } else if (height > size) {
            int mHeight = height / 100;
            int fScale = size / mHeight;
            width *= (fScale / 100);
            height *= (fScale / 100);
        }

        Log.d("rBitmap","width: "+width+" height: "+height);

        rBitmap = Bitmap.createScaledBitmap(oBitmap, width, height, true);*/
        rBitmap = Bitmap.createScaledBitmap(oBitmap,size,size,true);
        return rBitmap;
    }

    /* Bitmap을 xCount X yCount로 자르는 메소드 */
    public static Bitmap[][] splitBitmap(Bitmap bitmap, int xCount, int yCount) {
        // Allocate a two dimensional array to hold the individual images.
        Bitmap[][] bitmaps = new Bitmap[xCount][yCount];
        int width, height;
        // Divide the original bitmap width by the desired vertical column count
        width = bitmap.getWidth() / xCount;
        // Divide the original bitmap height by the desired horizontal row count
        height = bitmap.getHeight() / yCount;
        // Loop the array and create bitmaps for each coordinate
        for(int x = 0; x < xCount; ++x) {
            for(int y = 0; y < yCount; ++y) {
                // Create the sliced bitmap
                bitmaps[x][y] = Bitmap.createBitmap(bitmap, x * width, y * height, width, height);
            }
        }
        // Return the array
        return bitmaps;
    }
}
