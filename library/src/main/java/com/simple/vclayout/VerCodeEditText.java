package com.simple.vclayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class VerCodeEditText extends VerCodeLayout {


    private int mCount;
    private int mMaxLength;
    private Drawable mNormalBackground;
    private Drawable mFocusedBackground;

    private int mWidth;
    private int mHeight;
    private float mTextSize;

    private int mMargin;
    private int mMarginLeft;
    private int mMarginTop;
    private int mMarginRight;
    private int mMarginBottom;


    public VerCodeEditText(Context context) {
        this(context, null);
    }

    public VerCodeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerCodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.VerCodeEditText);

        mCount = ta.getInt(R.styleable.VerCodeEditText_vcCount, 0);
        mMaxLength = ta.getInt(R.styleable.VerCodeEditText_vcMaxLength, 1);
//        mBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcBackground);
        mNormalBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcNormalBackground);
        mFocusedBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcFocusedBackground);

        mTextSize = ta.getDimension(R.styleable.VerCodeEditText_vcTextSize, 0f);
        mWidth = (int) ta.getDimension(R.styleable.VerCodeEditText_vcEtWidth, 0f);
        mHeight = (int) ta.getDimension(R.styleable.VerCodeEditText_vcEtHeight, 0f);

        mMargin = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMargin, 0);
        mMarginLeft = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginLeft, 0);
        mMarginTop = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginTop, 0);
        mMarginRight = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginRight, 0);
        mMarginBottom = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginBottom, 0);

        ta.recycle();

    }

    @Override
    protected void onFinishInflate() {
        createEditTexts(mCount, mMaxLength);
        super.onFinishInflate();
    }

    private void createEditTexts(int count, int maxLength) {
        for (int i = 0; i < count; i++) {
            EditText editText = new EditText(getContext());
            editText.setMaxLines(1);
            //
//            if (mTextSize != 0) {
//            editText.setTextSize(20);
//            }
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            editText.setGravity(Gravity.CENTER);
            //margin
            MarginLayoutParams params = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (mMargin != 0) {
                params.leftMargin = mMargin;
                params.topMargin = mMargin;
                params.rightMargin = mMargin;
                params.bottomMargin = mMargin;
            } else {
                params.leftMargin = mMarginLeft;
                params.topMargin = mMarginTop;
                params.rightMargin = mMarginRight;
                params.bottomMargin = mMarginBottom;
            }
            if (mWidth != 0) {
                params.width = mWidth;
            }
            if (mHeight != 0) {
                params.height = mHeight;
            }
            //background
            editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Drawable d = hasFocus ? mFocusedBackground : mNormalBackground;
                    if (d == null) return;
                    v.setBackgroundDrawable(d);
                }
            });
            if (mNormalBackground != null){
                editText.setBackgroundDrawable(mNormalBackground);
            }
            //padding
//            editText.setPadding();
            //addView
            this.addView(editText, i, params);
        }
    }
}
