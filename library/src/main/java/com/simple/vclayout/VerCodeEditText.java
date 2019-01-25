package com.simple.vclayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.InputFilter;
import android.text.InputType;
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
    private int mMinWidth;
    private int mMinHeight;
    /**
     * set
     */
    private float mTextSize;
    @ColorInt
    private int mTextColor;
    private int mTextCursorDrawable;
    private int mGravity;
    private int mInputType;
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
        //
        mCount = ta.getInt(R.styleable.VerCodeEditText_vcCount, 0);
        mMaxLength = ta.getInt(R.styleable.VerCodeEditText_vcMaxLength, 1);
        //
        mNormalBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcNormalBackground);
        mFocusedBackground = ta.getDrawable(R.styleable.VerCodeEditText_vcFocusedBackground);
        //
        mTextSize = ta.getDimensionPixelSize(R.styleable.VerCodeEditText_vcTextSize, 0);
        mTextColor = ta.getColor(R.styleable.VerCodeEditText_vcTextColor, Color.BLACK);
        mTextCursorDrawable = ta.getResourceId(R.styleable.VerCodeEditText_vcTextCursorDrawable, -1);
        mGravity = ta.getInt(R.styleable.VerCodeEditText_vcGravity, Gravity.CENTER);
        mInputType = ta.getInt(R.styleable.VerCodeEditText_vcInputType, InputType.TYPE_CLASS_NUMBER);
        //
        mWidth = (int) ta.getDimension(R.styleable.VerCodeEditText_vcWidth, 0f);
        mHeight = (int) ta.getDimension(R.styleable.VerCodeEditText_vcHeight, 0f);
        mMinWidth = ta.getDimensionPixelSize(R.styleable.VerCodeEditText_vcMinWidth, 0);
        mMinHeight = ta.getDimensionPixelSize(R.styleable.VerCodeEditText_vcMinHeight, 0);
        //
        mMargin = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMargin, 0);
        mMarginLeft = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginLeft, 0);
        mMarginTop = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginTop, 0);
        mMarginRight = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginRight, 0);
        mMarginBottom = (int) ta.getDimension(R.styleable.VerCodeEditText_vcMarginBottom, 0);
        //
        mPadding = (int) ta.getDimension(R.styleable.VerCodeEditText_vcPadding, -1);
        mPaddingLeft = (int) ta.getDimension(R.styleable.VerCodeEditText_vcPaddingLeft, -1);
        mPaddingTop = (int) ta.getDimension(R.styleable.VerCodeEditText_vcPaddingTop, -1);
        mPaddingRight = (int) ta.getDimension(R.styleable.VerCodeEditText_vcPaddingRight, -1);
        mPaddingBottom = (int) ta.getDimension(R.styleable.VerCodeEditText_vcPaddingBottom, -1);

        ta.recycle();

        createEditTexts();
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
            this.addView(editText, params);
        }
    }

    private void setDefault(EditText editText) {
        editText.setMaxLines(1);
        if (mTextSize != 0) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        editText.setTextColor(mTextColor);
        if (mTextCursorDrawable != -1) {
            Utils.setTextCursorDrawable(editText, mTextCursorDrawable);
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
        editText.setGravity(Gravity.CENTER);
        editText.setMinWidth(mMinWidth);
        editText.setMinHeight(mMinHeight);
        editText.setGravity(mGravity);
        editText.setInputType(mInputType);
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
        if (mPadding != -1) {
            editText.setPadding(mPadding, mPadding, mPadding, mPadding);
        } else {
            if (editText.getBackground() != null) {
                Drawable d = editText.getBackground();
                Rect r = new Rect();
                d.getPadding(r);
                int left = mPaddingLeft != -1 ? mPaddingLeft : r.left;
                int top = mPaddingTop != -1 ? mPaddingTop : r.top;
                int right = mPaddingRight != -1 ? mPaddingRight : r.right;
                int bottom = mPaddingBottom != -1 ? mPaddingBottom : r.bottom;
                editText.setPadding(left, top, right, bottom);
            } else {
                editText.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            }
        }
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
