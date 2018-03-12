package com.offers.charles.offers.utils;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class DimenUtils {

    public static int getScreenWidth(WindowManager windowManager) {
        Display display = getDisplay(windowManager);
        Point size = getPoint(display);
        return size.x;
    }

    @NonNull
    public static Point getPoint(Display display) {
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static Display getDisplay(WindowManager windowManager) {
        return windowManager.getDefaultDisplay();
    }

    public static int getGridItemHeight(Context context, WindowManager windowManager, double imageRatio) {
        int width = (getScreenWidth(windowManager) - dpToPixel(context, 32)) / 2;
        return (int)(width * imageRatio);
    }

    public static int dpToPixel(Context context, int dp) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
