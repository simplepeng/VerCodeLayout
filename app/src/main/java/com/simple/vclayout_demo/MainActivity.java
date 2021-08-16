package com.simple.vclayout_demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.simple.vclayout.VerCodeEditText;
import com.simple.vclayout.VerCodeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerCodeEditText vcEt1 = findViewById(R.id.vcEt1);
        vcEt1.setOnCompleteListener(new VerCodeLayout.OnCompleteListener() {
            @Override
            public void onComplete(Editable editable, String code) {
                showToast(code);
            }
        });
    }

    public void clear(View view) {
        VerCodeEditText vcEt1 = findViewById(R.id.vcEt1);
        vcEt1.clear();
    }

    private void showToast(CharSequence text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
