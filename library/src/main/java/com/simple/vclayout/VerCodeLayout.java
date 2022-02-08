package com.simple.vclayout;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class VerCodeLayout extends LinearLayout {

    public static final String TAG = "VerCodeLayout";

    protected List<EditText> mEditTexts;
    private OnCompleteListener mOnCompleteListener;

    //
    protected boolean clickable = false;

    //
    protected boolean enterFocus = false;

    public VerCodeLayout(Context context) {
        this(context, null);
    }

    public VerCodeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerCodeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mEditTexts = new ArrayList<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childCount = getChildCount();
        EditText et_child;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof EditText) {
                et_child = ((EditText) child);
                setupEditText(et_child);
            }
        }
    }

    //
    private void setupEditText(EditText editText) {
        mEditTexts.add(editText);
        editText.setOnKeyListener(new InnerKeyListener(editText));
        editText.addTextChangedListener(new InnerTextWatcher(editText));
    }

    //按键监听
    class InnerKeyListener implements OnKeyListener {

        EditText innerEditText;
        int maxLength;

        public InnerKeyListener(EditText editText) {
            this.innerEditText = editText;
            this.maxLength = Utils.getMaxLength(editText);
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (innerEditText.getText().length() == maxLength
                    && keyCode != KeyEvent.KEYCODE_DEL
                    && keyCode != KeyEvent.KEYCODE_HOME
                    && keyCode != KeyEvent.KEYCODE_BACK) {
                focusNext(innerEditText);
            } else if (innerEditText.getText().length() == 0
                    && keyCode == KeyEvent.KEYCODE_DEL) {
                focusLast(innerEditText);
            }
            return false;
        }
    }

    //输入文本监听
    class InnerTextWatcher implements TextWatcher {

        EditText innerEditText;
        int maxLength;

        public InnerTextWatcher(EditText editText) {
            innerEditText = editText;
            this.maxLength = Utils.getMaxLength(editText);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            final int count = s.length();
            if (maxLength == 0) {
                throw new IllegalArgumentException(getResources().getString(R.string.exception_no_max_length));
            }
            if (count >= maxLength) {
                focusNext(innerEditText);
            }
        }
    }

    //移动焦点到下一个输入框
    protected void focusNext(EditText et) {
        final int index = mEditTexts.indexOf(et);
        if (index < mEditTexts.size() - 1) {
            EditText nextEt = mEditTexts.get(index + 1);
            nextEt.setEnabled(true);
            nextEt.requestFocus();
//            et.setEnabled(clickable);
        } else {
            if (mOnCompleteListener != null) {
                Editable editable = Editable.Factory.getInstance().newEditable("");
                for (EditText editText : mEditTexts) {
                    editable.append(editText.getText());
                }
                mOnCompleteListener.onComplete(editable, editable.toString());
            }
        }
    }

    //移动焦点到上一个输入框
    protected void focusLast(EditText et) {
        final int index = mEditTexts.indexOf(et);
        if (index != 0) {
            EditText lastEt = mEditTexts.get(index - 1);
            lastEt.setEnabled(true);
            lastEt.requestFocus();
//            et.setEnabled(clickable);
        }
    }

    //清除所有文本
    public void clear() {
        if (mEditTexts.isEmpty()) return;
        for (EditText editText : mEditTexts) {
            editText.setText("");
            editText.clearFocus();
        }
        EditText first = mEditTexts.get(0);
        if (first != null) {
            first.setEnabled(true);
            first.requestFocus();
        }
    }

    //所有的EditText
    public List<EditText> getEditTexts() {
        return mEditTexts;
    }

    //输入完成监听事件
    public void setOnCompleteListener(OnCompleteListener listener) {
        this.mOnCompleteListener = listener;
    }

    public interface OnCompleteListener {
        void onComplete(Editable editable, String code);
    }

}
