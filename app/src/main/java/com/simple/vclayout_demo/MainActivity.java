package com.simple.vclayout_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.simple.vclayout.VerCodeEditText;
import com.simple.vclayout.VerCodeLayout;

public class MainActivity extends AppCompatActivity {

    private VerCodeLayout vc_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vc_layout = findViewById(R.id.vc_layout);
        vc_layout.setOnCompleteListener(new VerCodeLayout.OnCompleteListener() {
            @Override
            public void onComplete(Editable editable, String code) {
//                code = editable.toString();
                showToast(code);
            }
        });

        VerCodeEditText vcEt1 = findViewById(R.id.vcEditText1);
        vcEt1.setOnCompleteListener(new VerCodeLayout.OnCompleteListener() {
            @Override
            public void onComplete(Editable editable, String code) {
                showToast(code);
            }
        });
    }

    public void clear(View view) {
        VerCodeEditText vcEt = findViewById(R.id.vcEditText1);
        vcEt.clear();
    }

    private void showToast(CharSequence text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
