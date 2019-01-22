package com.simple.vclayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class VerCodeEditText extends VerCodeLayout {

    private int mCount;
    private int mMaxLength;

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
        ta.recycle();

        createEditTexts(mCount, mMaxLength);

    }

    private List<EditText> createEditTexts(int count, int maxLength) {
        List<EditText> editTexts = new ArrayList<>();
        EditText editText;
        for (int i = 0; i < count; i++) {
            editText = new EditText(getContext());
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            editTexts.add(editText);
            addView(editText);
        }
        return editTexts;
    }
}
