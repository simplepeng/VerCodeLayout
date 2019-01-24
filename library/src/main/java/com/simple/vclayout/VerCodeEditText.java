package com.simple.vclayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class VerCodeEditText extends VerCodeLayout {

    private int mCount;
    private int mMaxLength;
    /**
     *
     */
    private Drawable mNormalBackground;
    private Drawable mFocusedBackground;
    /**
     *
     */
    private int mWidth;
    private int mHeight;
    /**
     *
     */
    private float mTextSize;
    @ColorInt
    private int mTextColor;
    private int mTextCursorDrawable;
    /**
     *
     */
    private int mMargin;
    private int mMarginLeft;
    private int mMarginTop;
    private int mMarginRight;
    private int mMarginBottom;
    /**
     *
     */
    private int mPadding;
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;


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

        mNormalBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcNormalBackground);
        mFocusedBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcFocusedBackground);

        mTextSize = ta.getDimensionPixelSize(R.styleable.VerCodeEditText_vcTextSize, 0);
        mTextColor = ta.getColor(R.styleable.VerCodeEditText_vcTextColor, Color.BLACK);
        mTextCursorDrawable = ta.getResourceId(R.styleable.VerCodeEditText_vcTextCursorDrawable, -1);

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
        createEditTexts();
        super.onFinishInflate();
    }

    private void createEditTexts() {
        if (mCount <= 0) return;

        EditText editText;
        for (int i = 0; i < mCount; i++) {
            editText = new EditText(getContext());
            //
            setDefault(editText);
            //margin
            MarginLayoutParams params = getMarginLayoutParams();
            //background
            setBackground(editText);
            //padding
            setPadding(editText);
            //addView
            this.addView(editText, i, params);
        }
    }

    private void setDefault(EditText editText) {
        editText.setMaxLines(1);
        if (mTextSize != 0) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        editText.setTextColor(mTextColor);
        Utils.setTextCursorDrawable(editText, mTextCursorDrawable);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
        editText.setGravity(Gravity.CENTER);
    }

    private void setBackground(EditText editText) {
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Drawable d = hasFocus ? mFocusedBackground : mNormalBackground;
                if (d == null) return;
                v.setBackgroundDrawable(d);
            }
        });
        if (mNormalBackground != null) {
            editText.setBackgroundDrawable(mNormalBackground);
        }
    }

    private void setPadding(EditText editText) {
        editText.setPadding(0, 0, 0, 0);
    }

    private MarginLayoutParams getMarginLayoutParams() {
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
        return params;
    }
}
