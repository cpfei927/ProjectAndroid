package com.cpfei.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import com.cpfei.project.R;

/**
 * 处理Bitmap
 */
public class BitmapUtils {

    /**
     * Multiply the specify color to the source image, and using alpha of the source.
     *
     * @param bmp The source image
     * @param r   Red component of the specify color
     * @param g   Green component of the specify color
     * @param b   Blue component of the specify color
     * @param a   Alpha of the specify color
     * @return A new image with specified color
     * @date 2015-5-15
     * @example 用例 ：Bitmap bmp = BitmapFactory.decodexxx(args...);
     * Bitmap bmpColored = BitmapUtils.getColoredBitmap(bmp, 211, 211, 211, 255);
     * bmp.recycle();
     * ImageView view = (ImageView) findViewById(R.id.iv_item);
     * view.setImageBitmap(bmpColored);
     */
    public static Bitmap getColoredBitmap(Bitmap bmp, int r, int g, int b, int a) {
        if (bmp == null) {
            return null;
        }
        Bitmap bmpColored = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpColored);
        canvas.drawARGB(a, r, g, b);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(Mode.MULTIPLY));
        canvas.drawBitmap(bmp, 0, 0, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmpColored;
    }

    /**
     * 用例：
     * Bitmap bmpColored = BitmapUtils.getColoredBitmap(this, R.drawable.a111,
     * 211, 211, 211, 255);
     * <p/>
     * ImageView view = (ImageView) findViewById(R.id.iv_item);
     * view.setImageBitmap(bmpColored);
     */
    public static Bitmap getColoredBitmap(Context context, int resId, int r,
                                          int g, int b, int a) {
        Bitmap bmp = BitmapFactory
                .decodeResource(context.getResources(), resId);
        Bitmap bmpColored = getColoredBitmap(bmp, r, g, b, a);
        bmp.recycle();
        return bmpColored;
    }

    /**
     * @param bitmap
     * @return
     * @Description 返回一个灰蒙蒙的图片
     * @date 2015-5-15
     */
    public static Bitmap filterBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint mPaint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 0, 0.82f, 0,
                0, 0, 0, 0.82f, 0,
                0, 0, 0, 0.82f, 0,
                0, 0, 0, 1.0f, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        return createBitmap;
    }

    public static Bitmap decodeBitmap(String pathName, Bitmap.Config bmpConfig) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = bmpConfig;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

    public static Bitmap decodeBitmap(String pathName, int reqWidth,
                                      int reqHeight) {
        return decodeBitmap(pathName, reqWidth, reqHeight,
                Bitmap.Config.RGB_565);
    }

    public static Bitmap decodeBitmap(String pathName, int reqWidth,
                                      int reqHeight, Bitmap.Config bmpConfig) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = bmpConfig;
        BitmapFactory.decodeFile(pathName, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        inSampleSize = inSampleSize < 1 ? 1 : inSampleSize;
        return inSampleSize;
    }


    public static Bitmap getNumberBitmap(Context context, int number) {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.map_icon).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        // draw text
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        RectF rect = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        float baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(String.valueOf(number), rect.centerX(), baseline, paint);
        return bitmap;
    }


}
