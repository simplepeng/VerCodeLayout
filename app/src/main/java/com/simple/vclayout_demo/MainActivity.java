package com.simple.vclayout_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import com.simple.vclayout.VerCodeLayout;

public class MainActivity extends AppCompatActivity {

    private VerCodeLayout vc_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vc_layout = findViewById(R.id.vc_layout);
        vc_layout.setOnInputFinishListener(new VerCodeLayout.OnInputFinishListener() {
            @Override
            public void onInputFinish(Editable editable, String code) {
//                code = editable.toString();
                showToast(code);
            }
        });
    }

    private void showToast(CharSequence text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
