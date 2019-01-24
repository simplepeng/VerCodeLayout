package com.simple.vclayout;

import android.content.Context;
import android.os.Build;
import android.text.InputFilter;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

public class Utils {

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void log(String msg) {
        Log.d(VerCodeLayout.TAG, msg);
    }


    public static int getMaxLength(EditText editText) {
        int maxLength = 0;
        InputFilter[] filters = editText.getFilters();

        if (filters == null || filters.length == 0) {
            return maxLength;
        }

        for (InputFilter filter : filters) {
            if (filter instanceof InputFilter.LengthFilter) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    InputFilter.LengthFilter lengthFilter = ((InputFilter.LengthFilter) filter);
                    maxLength = lengthFilter.getMax();
                } else {
                    //below 21
                    try {
                        Field field = filter.getClass().getDeclaredField("mMax");
                        field.setAccessible(true);

                        if (field.isAccessible()) {
                            maxLength = field.getInt(filter);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return maxLength;
    }

    public static void setTextCursorDrawable(EditText editText, int resId) {
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(editText, resId);
        } catch (Exception e) {

        }
    }

}
