package com.chekaz.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;

import com.chekaz.game.Engine;

public class Pic {
	//Class for all bitmap manipulation
	
	public static Bitmap resizeBitmap(Engine engine, int id, int newSize, int maxSize){
		int size = 0;
		
		if(newSize > maxSize)
			size = maxSize;
		else
			size = newSize;
		
		return scaleBitmap(BitmapFactory.decodeResource(engine.getMain().getResources(), id), size, size);
	}
	
	
public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight)
{
	  Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Config.ARGB_8888);

	  float scaleX = newWidth / (float) bitmap.getWidth();
	  float scaleY = newHeight / (float) bitmap.getHeight();
	  float pivotX = 0;
	  float pivotY = 0;

	  Matrix scaleMatrix = new Matrix();
	  scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

	  Canvas canvas = new Canvas(scaledBitmap);
	  canvas.setMatrix(scaleMatrix);
	  canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

	  return scaledBitmap;
	}
	
public static int calculateInSampleSize(
        BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
public static Bitmap getBitmapFromResource(Resources res, int resId,
        int reqWidth, int reqHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);
}	

public static Bitmap getBitmapFromPath(String path,int reqWidth, int reqHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    
    BitmapFactory.decodeFile(path, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(path, options);
}

public static Bitmap rotateImage(Bitmap bitmap, String filePath)
{
    Bitmap resultBitmap = bitmap;

    try
    {
        ExifInterface exifInterface = new ExifInterface(filePath);
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        
        if(orientation==ExifInterface.ORIENTATION_ROTATE_90){
	        Matrix matrix = new Matrix();
	        matrix.preRotate(90);
	        // Rotate the bitmap
	        resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    	}  
        else if(orientation==ExifInterface.ORIENTATION_ROTATE_180){
	        Matrix matrix = new Matrix();
	        matrix.preRotate(180);
	        // Rotate the bitmap
	        resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    	}
        else if(orientation==ExifInterface.ORIENTATION_ROTATE_270){
	        Matrix matrix = new Matrix();
	        matrix.preRotate(270);
	        // Rotate the bitmap
	        resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    	}
        
    }
    catch (Exception exception)
    {
        
    }
    return resultBitmap;
}
	
}
